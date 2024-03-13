package br.edu.ifpr.irati.ads.mb;

import br.edu.ifpr.irati.ads.dao.Dao;
import br.edu.ifpr.irati.ads.dao.GenericDAO;
import br.edu.ifpr.irati.ads.exception.PersistenceException;
import br.edu.ifpr.irati.ads.modelo.Contribuicao;
import br.edu.ifpr.irati.ads.modelo.FormaPgto;
import br.edu.ifpr.irati.ads.modelo.Transacao;
import br.edu.ifpr.irati.ads.modelo.Usuario;
import br.edu.ifpr.irati.ads.util.HibernateUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.hibernate.Session;

@ManagedBean
@ViewScoped
public class ContribuicaoMB implements Serializable {

    private Contribuicao contribuicao = new Contribuicao();
    private List<Contribuicao> contribuicaos;
    private Dao<Contribuicao> contribuicaoDAO;
    private Dao<Usuario> usuarioDAO;
    private boolean inserir;
    private String usuario;

    private Dao<FormaPgto> formaPgtoDAO;
    private List<FormaPgto> formasPgto;

    private List<Usuario> listaUsuarios;
    private List<Usuario> listaUsuariosFiltro;
    
    
    private Transacao transacao = new Transacao();
    private List<Transacao> transacoes;
    private Dao<Transacao> transacaoDAO;

    public ContribuicaoMB() throws PersistenceException {
        this.listaUsuariosFiltro = new ArrayList<Usuario>();
        try {
            contribuicao = new Contribuicao();
            Session session = HibernateUtil.getSessionFactory().openSession();
            contribuicaoDAO = new GenericDAO<>(Contribuicao.class, session);
            usuarioDAO = new GenericDAO<>(Usuario.class, session);
            contribuicaos = contribuicaoDAO.buscarTodos();
            listaUsuarios = usuarioDAO.buscarTodos();
            usuario = "";
            inserir = true;
            session.close();
            limparTela();
        } catch (PersistenceException ex) {
            ex.printStackTrace();
        }
    }

    public void limparTela() {
        //setContribuicao(new Contribuicao());
        System.out.println(contribuicao.getTipo());
    }

    public void salvar() {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            contribuicaoDAO = new GenericDAO<>(Contribuicao.class, session);
            
            //cadastrarTransacao();
            if (inserir) {
                //executar o método inserir do DAO
                contribuicaoDAO.salvar(contribuicao);

                //finanaceiro.addInTransacao(contribuicao.getData(), contribuicao.getFormaContribuicao().getFormaPgto(),'C', contribuicao.getValor());
            } else {
                //executar o método alterar do DAO
                contribuicaoDAO.alterar(contribuicao);
            }
            inserir = true;
            //contribuicao = new Contribuicao();
            contribuicaos = contribuicaoDAO.buscarTodos();
            session.close();
        } catch (PersistenceException ex) {
            ex.printStackTrace();
        }
    }

    public void botaoExcluir(Contribuicao contribuicao) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            contribuicaoDAO = new GenericDAO<>(Contribuicao.class, session);
            contribuicaoDAO.excluir(contribuicao);
            this.contribuicao = new Contribuicao();
            contribuicaos = contribuicaoDAO.buscarTodos();
            inserir = true;
            session.close();
        } catch (PersistenceException ex) {
            ex.printStackTrace();
        }

    }

    public void botaoSelecionar(Usuario usuario) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        contribuicao.setUsuario(usuario);
        session.close();

    }

    public String botaoAlterar(Contribuicao contribuicao) {
        System.out.println(contribuicao.getId());
        this.contribuicao = new Contribuicao(contribuicao.getId(), contribuicao.getUsuario(), contribuicao.getValor(), contribuicao.getData(), contribuicao.getFormaContribuicao(), contribuicao.getTipo());
        inserir = false;
        return "-";
    }

    public void localizarUsuario(String nomeUsuario) {
        for (Usuario u : listaUsuarios) {
            if (u.getNome().contains(nomeUsuario)) {
                listaUsuariosFiltro.add(u);
            }
        }
    }

    public void cadastrarTransacao() throws PersistenceException {
        char tipo = 'C';
        transacao = new Transacao(0, contribuicao.getData(),contribuicao.getUsuario().getNome(), tipo,contribuicao.getValor());
        Session session = HibernateUtil.getSessionFactory().openSession();
        transacaoDAO = new GenericDAO<>(Transacao.class, session);
        transacaoDAO.salvar(transacao);
        session.close();
        transacao = new Transacao();
    }

    public void limparFiltroUsuario() throws PersistenceException {
        listaUsuariosFiltro.clear();
    }

    public Contribuicao getContribuicao() {
        return contribuicao;
    }

    public void setContribuicao(Contribuicao contribuicao) {
        this.contribuicao = contribuicao;
    }

    public List<Contribuicao> getContribuicaos() {
        return contribuicaos;
    }

    public void setContribuicaos(List<Contribuicao> contribuicaos) {
        this.contribuicaos = contribuicaos;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public Dao<Usuario> getUsuarioDAO() {
        return usuarioDAO;
    }

    public void setUsuarioDAO(Dao<Usuario> usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    public List<Usuario> getListaUsuariosFiltro() {
        return listaUsuariosFiltro;
    }

    public void setListaUsuariosFiltro(List<Usuario> listaUsuariosFiltro) {
        this.listaUsuariosFiltro = listaUsuariosFiltro;
    }

    public List<FormaPgto> getFormasPgto() throws PersistenceException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        formaPgtoDAO = new GenericDAO<>(FormaPgto.class, session);
        setFormasPgto(formaPgtoDAO.buscarTodos());
        return formasPgto;
    }

    public void setFormasPgto(List<FormaPgto> formasPgto) {
        this.formasPgto = formasPgto;
    }

}
