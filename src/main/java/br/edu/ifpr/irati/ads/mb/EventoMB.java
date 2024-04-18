package br.edu.ifpr.irati.ads.mb;

import br.edu.ifpr.irati.ads.dao.Dao;
import br.edu.ifpr.irati.ads.dao.GenericDAO;
import br.edu.ifpr.irati.ads.exception.PersistenceException;
import br.edu.ifpr.irati.ads.modelo.Contribuicao;
import br.edu.ifpr.irati.ads.modelo.Evento;
import br.edu.ifpr.irati.ads.modelo.Usuario;
import br.edu.ifpr.irati.ads.util.HibernateUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import org.hibernate.Session;

@ManagedBean
@SessionScoped
public final class EventoMB implements Serializable {

    private boolean inserir;

    private Evento evento = new Evento();
    private List<Evento> eventos;
    private Dao<Evento> eventoDAO;

    private Usuario usuario = new Usuario();
    private List<Usuario> usuarios;
    private List<Usuario> usuariosPresentes;
    private Dao<Usuario> usuarioDAO;

    private Contribuicao contribuicao = new Contribuicao();
    private List<Contribuicao> contribuicoes;
    private Dao<Contribuicao> contribuicaoDAO;

    @ManagedProperty(value="#{ContribuicaoMB.contribuicaoFiltro}")
    private List<Contribuicao> contribuicaoFiltro;
    private double totalFiltro;

    public EventoMB() throws PersistenceException {
        try {
            evento = new Evento();
            Session session = HibernateUtil.getSessionFactory().openSession();
            eventoDAO = new GenericDAO<>(Evento.class, session);
            eventos = eventoDAO.buscarTodos();
            session.close();
            buscarUsuarios();
            buscarContribuicoes();

            inserir = true;
            limparTela();
        } catch (PersistenceException ex) {
            ex.printStackTrace();
        }
    }

    public void limparTela() {
        setUsuario(new Usuario());
    }

    public String salvar() {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            eventoDAO = new GenericDAO<>(Evento.class, session);

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
            return "/restricted/user/usuario.xhtml?faces-redirect=true";
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
    
    public void testarSalvar(List<Contribuicao> contribuicoes){
        for(Contribuicao c: contribuicoes){
            System.out.println(c.getValor());
        }
        System.out.println(evento.getQuantidadeFrequentantes());
        System.out.println(evento.getQuantidadeVisitantes());
        for(Usuario u: evento.getFreqTrabalhadores()){
            System.out.println(u.getNome() );
        }
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

}
