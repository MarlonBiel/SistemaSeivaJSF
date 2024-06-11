package br.edu.ifpr.irati.ads.mb;

import br.edu.ifpr.irati.ads.dao.Dao;
import br.edu.ifpr.irati.ads.dao.GenericDAO;
import br.edu.ifpr.irati.ads.exception.PersistenceException;
import br.edu.ifpr.irati.ads.modelo.Contribuicao;
import br.edu.ifpr.irati.ads.modelo.ContribuicaoEvento;
import br.edu.ifpr.irati.ads.modelo.Evento;
import br.edu.ifpr.irati.ads.modelo.Usuario;
import br.edu.ifpr.irati.ads.modelo.UsuarioEvento;
import br.edu.ifpr.irati.ads.util.HibernateUtil;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import org.hibernate.Session;

@ManagedBean
@SessionScoped
public final class EventoMB implements Serializable {

    private boolean inserir;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    private ContribuicaoEvento contribuicaoEvento = new ContribuicaoEvento();
    private List<ContribuicaoEvento> contribuicoesEvento;
    private Dao<ContribuicaoEvento> contribuicaoEventoDAO;
    private List<ContribuicaoEvento> contribuicoesEventoFilter;
    
    private UsuarioEvento usuarioEvento = new UsuarioEvento();
    private List<UsuarioEvento> usuariosEvento;
    private Dao<UsuarioEvento> usuarioEventoDAO;
    private List<UsuarioEvento> usuariosEventoFilter;

    private Evento evento = new Evento();
    private List<Evento> eventos;
    private Dao<Evento> eventoDAO;
    private Date data;
    private int quantidadeTotal;

    private Usuario usuario = new Usuario();
    private List<Usuario> usuarios;
    private List<Usuario> usuariosPresentes;
    private Dao<Usuario> usuarioDAO;
    private String nomeUsuariosPresentes = "";

    private Contribuicao contribuicao = new Contribuicao();
    private List<Contribuicao> contribuicoes;
    private Dao<Contribuicao> contribuicaoDAO;
    private double totalContribuicoes;

    @ManagedProperty(value = "#{ContribuicaoMB.contribuicaoFiltro}")
    private List<Contribuicao> contribuicaoFiltro;
    private double totalFiltro;

    public EventoMB() throws PersistenceException {
        try {
            evento = new Evento();
            contribuicaoEvento = new ContribuicaoEvento();
            contribuicoesEvento = new ArrayList<>();
            usuarioEvento = new UsuarioEvento();
            usuariosEvento = new ArrayList<>();
            
            Session session = HibernateUtil.getSessionFactory().openSession();
            eventoDAO = new GenericDAO<>(Evento.class, session);
            eventos = eventoDAO.buscarTodos();
            session.close();
            buscarUsuarios();
            buscarContribuicoes();
            data = new Date();
            inserir = true;
            limparTela();
        } catch (PersistenceException ex) {
            ex.printStackTrace();
        }
    }

    public void limparTela() {
        setUsuario(new Usuario());
    }

    public String salvar(List<Contribuicao> contribuicoes) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            eventoDAO = new GenericDAO<>(Evento.class, session);
            configItensSalvar(contribuicoes);
            evento.setContribuicoesEvento(contribuicoesEvento);
            evento.setUsuariosEvento(usuariosEvento);
            if (inserir) {
                //executar o método inserir do DAO
                eventoDAO.salvar(evento);
            } else {
                //executar o método alterar do DAO
                eventoDAO.alterar(evento);
            }
            inserir = true;
            evento = new Evento();
            contribuicaoFiltro = new ArrayList<>();
            eventos = eventoDAO.buscarTodos();
            session.close();
            return "/restricted/central?faces-redirect=true";
        } catch (PersistenceException ex) {
            ex.printStackTrace();
        }
        return "";
    }

    public void botaoExcluir(Usuario usuario) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            usuarioDAO = new GenericDAO<>(Usuario.class, session);
            usuarioDAO.excluir(usuario);
            this.setUsuario(new Usuario());
            setUsuarios(usuarioDAO.buscarTodos());
            inserir = true;
            session.close();
        } catch (PersistenceException ex) {
            ex.printStackTrace();
        }
    }

    public String botaoAlterar(Usuario usuario) {
        System.out.println(usuario.getId());
        this.setUsuario(new Usuario(usuario.getId(), usuario.getNome(), usuario.getCpf(), usuario.getDataNascimento(), usuario.getEndereco(), usuario.getTelefone(), usuario.getEmail(), usuario.getSenha(), usuario.getMatricula(), usuario.getFuncao()));
        inserir = false;
        return "/restricted/central.xhtml?faces-redirect=true";
    }

    public void buscarUsuarios() throws PersistenceException {
        usuario = new Usuario();
        Session session = HibernateUtil.getSessionFactory().openSession();
        usuarioDAO = new GenericDAO<>(Usuario.class, session);
        usuarios = usuarioDAO.buscarTodos();
        session.close();
    }

    public void buscarContribuicoes() throws PersistenceException {
        contribuicao = new Contribuicao();
        Session session = HibernateUtil.getSessionFactory().openSession();
        contribuicaoDAO = new GenericDAO<>(Contribuicao.class, session);
        contribuicoes = contribuicaoDAO.buscarTodos();
        session.close();
    }

    public void configItensSalvar(List<Contribuicao> contribuicoes) throws PersistenceException {
        contribuicaoEvento =  new ContribuicaoEvento();
        for (Contribuicao c : contribuicoes) {
            contribuicaoEvento.setContribuicao(c);
            //contribuicoesEvento.add(contribuicaoEvento);
            Session session = HibernateUtil.getSessionFactory().openSession();
            contribuicaoEventoDAO = new GenericDAO<>(ContribuicaoEvento.class, session);
            contribuicaoEventoDAO.salvar(contribuicaoEvento);
            contribuicaoEvento = new ContribuicaoEvento();
        }

        for (Usuario u : usuariosPresentes) {
            //Session session = HibernateUtil.getSessionFactory().openSession();
            //usuarioEventoDAO = new GenericDAO<>(UsuarioEvento.class, session);
            usuarioEvento.setUsuario(u);
            //usuariosEvento.add(usuarioEvento);
            usuarioEventoDAO.salvar(usuarioEvento);
            usuarioEvento = new UsuarioEvento();
            //nomeUsuariosPresentes =nomeUsuariosPresentes + u.getNome()+ "; ";
        }
        
        

        quantidadeTotal = evento.getQuantidadeFrequentantes() + evento.getQuantidadeVisitantes() + usuariosPresentes.size();
        evento.setData(data);
        sdf.format(data);
        evento.setDescricao("Reunião dia " + sdf.format(data));

    }

    public void listasFiltradas() throws PersistenceException{
        Session session = HibernateUtil.getSessionFactory().openSession();
        contribuicaoEventoDAO = new GenericDAO<>(ContribuicaoEvento.class, session);
        contribuicoesEvento = contribuicaoEventoDAO.buscarTodos();
        for(ContribuicaoEvento ce: contribuicoesEvento){
            if(ce.getEvento() == null){
                contribuicoesEventoFilter.add(ce);
            }
        }
        
        usuarioEventoDAO = new GenericDAO<>(UsuarioEvento.class, session);
        usuariosEvento = usuarioEventoDAO.buscarTodos();
        for(UsuarioEvento ue: usuariosEvento){
            if(ue.getEvento() == null){
                usuariosEventoFilter.add(ue);
            }
        }
    }
    
    public void setSelectedItems(List<Usuario> selectedUsuario) {
        this.usuariosPresentes = selectedUsuario;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public List<Evento> getEventos() {
        return eventos;
    }

    public void setEventos(List<Evento> eventos) {
        this.eventos = eventos;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public Contribuicao getContribuicao() {
        return contribuicao;
    }

    public void setContribuicao(Contribuicao contribuicao) {
        this.contribuicao = contribuicao;
    }

    public List<Contribuicao> getContribuicoes() {
        return contribuicoes;
    }

    public void setContribuicoes(List<Contribuicao> contribuicoes) {
        this.contribuicoes = contribuicoes;
    }

    public List<Contribuicao> getContribuicaoFiltro() {
        return contribuicaoFiltro;
    }

    public void setContribuicaoFiltro(List<Contribuicao> contribuicaoFiltro) {
        this.contribuicaoFiltro = contribuicaoFiltro;
    }

    public double getTotalFiltro() {
        return totalFiltro;
    }

    public void setTotalFiltro(double totalFiltro) {
        this.totalFiltro = totalFiltro;
    }

    public List<Usuario> getUsuariosPresentes() {
        return usuariosPresentes;
    }

    public void setUsuariosPresentes(List<Usuario> usuariosPresentes) {
        this.usuariosPresentes = usuariosPresentes;
    }

    public ContribuicaoEvento getContribuicaoEvento() {
        return contribuicaoEvento;
    }

    public void setContribuicaoEvento(ContribuicaoEvento contribuicaoEvento) {
        this.contribuicaoEvento = contribuicaoEvento;
    }

    public List<ContribuicaoEvento> getContribuicoesEvento() {
        return contribuicoesEvento;
    }

    public void setContribuicoesEvento(List<ContribuicaoEvento> contribuicoesEvento) {
        this.contribuicoesEvento = contribuicoesEvento;
    }

    public UsuarioEvento getUsuarioEvento() {
        return usuarioEvento;
    }

    public void setUsuarioEvento(UsuarioEvento usuarioEvento) {
        this.usuarioEvento = usuarioEvento;
    }

    public List<UsuarioEvento> getUsuariosEvento() {
        return usuariosEvento;
    }

    public void setUsuariosEvento(List<UsuarioEvento> usuariosEvento) {
        this.usuariosEvento = usuariosEvento;
    }

    public List<ContribuicaoEvento> getContribuicoesEventoFilter() {
        return contribuicoesEventoFilter;
    }

    public void setContribuicoesEventoFilter(List<ContribuicaoEvento> contribuicoesEventoFilter) {
        this.contribuicoesEventoFilter = contribuicoesEventoFilter;
    }

    public List<UsuarioEvento> getUsuariosEventoFilter() {
        return usuariosEventoFilter;
    }

    public void setUsuariosEventoFilter(List<UsuarioEvento> usuariosEventoFilter) {
        this.usuariosEventoFilter = usuariosEventoFilter;
    }

}
