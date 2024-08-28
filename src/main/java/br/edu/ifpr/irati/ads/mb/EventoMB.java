package br.edu.ifpr.irati.ads.mb;

import br.edu.ifpr.irati.ads.dao.Dao;
import br.edu.ifpr.irati.ads.dao.GenericDAO;
import br.edu.ifpr.irati.ads.exception.PersistenceException;
import br.edu.ifpr.irati.ads.modelo.Contribuicao;
import br.edu.ifpr.irati.ads.modelo.Evento;
import br.edu.ifpr.irati.ads.modelo.Usuario;
import br.edu.ifpr.irati.ads.util.HibernateUtil;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ComponentSystemEvent;
import org.hibernate.Session;

@ManagedBean(name = "eventoMB")
@SessionScoped
public final class EventoMB implements Serializable {

    private boolean inserir;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    private Evento evento = new Evento();
    private Evento eventoSelecionado;
    private List<Evento> eventos;
    private Dao<Evento> eventoDAO;
    private Date data;
    private double quantidadeTotalContribuicao = 0;
    private int quantidadeTotalUsuarios = 0;

    private Usuario usuario = new Usuario();
    private List<Usuario> usuarios;
    private List<Usuario> usuariosPresentes;
    private Dao<Usuario> usuarioDAO;

    private Contribuicao contribuicao = new Contribuicao();
    private List<Contribuicao> contribuicoes;
    private Dao<Contribuicao> contribuicaoDAO;

    private List<Contribuicao> contribuicaoFiltro;
    private double totalFiltro;

    public EventoMB() throws PersistenceException {
        try {
            evento = new Evento();
            contribuicaoFiltro = new ArrayList<>();
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

    public String salvar() {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            eventoDAO = new GenericDAO<>(Evento.class, session);
            configItensSalvar();
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
            return "/restricted/meet/evento_list?faces-redirect=true";
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
        //this.setUsuario(new Usuario(usuario.getId(), usuario.getNome(), usuario.getCpf(), usuario.getDataNascimento(), usuario.getEndereco(), usuario.getTelefone(), usuario.getEmail(), usuario.getSenha(), usuario.getMatricula(), usuario.getFuncao()));
        inserir = false;
        return "/restricted/central.xhtml?faces-redirect=true";
    }

    public void buscarUsuarios() throws PersistenceException {
        usuario = new Usuario();
        Session session = HibernateUtil.getSessionFactory().openSession();
        usuarioDAO = new GenericDAO<>(Usuario.class, session);
        usuarios = usuarioDAO.buscarTodos().stream().filter(usuario -> usuario.getDataExclusao() == null).collect(Collectors.toList());
        session.close();
    }
    
    public void atualizarListaUsuarios(ComponentSystemEvent event) throws PersistenceException{
        buscarUsuarios();
    }

    public void buscarContribuicoes() throws PersistenceException {
        contribuicao = new Contribuicao();
        Session session = HibernateUtil.getSessionFactory().openSession();
        contribuicaoDAO = new GenericDAO<>(Contribuicao.class, session);
        contribuicoes = contribuicaoDAO.buscarTodos();
        session.close();
    }

    public double somaContribuicaoFiltro() {
        quantidadeTotalContribuicao = 0;
        for (Contribuicao c : evento.getContribuicoes()) {
            quantidadeTotalContribuicao = quantidadeTotalContribuicao + c.getValor();
        }
        evento.setTotalContribuicoes(quantidadeTotalContribuicao);
        quantidadeTotalContribuicao = 0;
        return evento.getTotalContribuicoes();
    }

    public void configItensSalvar() throws PersistenceException {
        //addUserToEvent();
        evento.setData(data);
        sdf.format(data);
        evento.setDescricao("Reunião dia " + sdf.format(data));
        evento.setUsuarios(usuariosPresentes);
        evento.setTotalFrequentes(evento.getQuantidadeFrequentantes() + evento.getQuantidadeVisitantes() + usuariosPresentes.size());
        quantidadeTotalContribuicao = 0;

    }

    public String botaoVoltar(boolean flagTelaEdit) {
        this.evento = new Evento();
        if (flagTelaEdit) {
            return "/restricted/finance/evento_list.xhtml?faces-redirect=true";
        }
        return "/restricted/finance/central.xhtml?faces-redirect=true";
    }

    public String botaoIniciarEvento() {
        this.evento = new Evento();
        return "/restricted/meet/evento.xhtml?faces-redirect=true";
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

    public Evento getEventoSelecionado() {
        return eventoSelecionado;
    }

    public void setEventoSelecionado(Evento eventoSelecionado) {
        this.eventoSelecionado = eventoSelecionado;
    }

}
