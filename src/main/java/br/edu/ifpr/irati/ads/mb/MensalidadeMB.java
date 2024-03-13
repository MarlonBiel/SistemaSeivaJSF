package br.edu.ifpr.irati.ads.mb;

import br.edu.ifpr.irati.ads.dao.Dao;
import br.edu.ifpr.irati.ads.dao.GenericDAO;
import br.edu.ifpr.irati.ads.exception.PersistenceException;
import br.edu.ifpr.irati.ads.modelo.Mensalidade;
import br.edu.ifpr.irati.ads.modelo.Mes;
import br.edu.ifpr.irati.ads.modelo.Transacao;
import br.edu.ifpr.irati.ads.modelo.Usuario;
import br.edu.ifpr.irati.ads.util.HibernateUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.hibernate.Session;

@ManagedBean
@ViewScoped
public class MensalidadeMB implements Serializable {

    private Mensalidade mensalidade;
    private List<Mensalidade> mensalidades;
    private List<Usuario> usuarios;
    private Usuario usuario;
    private Dao<Usuario> usuarioDAO;
    private Dao<Mensalidade> mensalidadeDAO;
    private boolean inserir;
    private Mes mes;
    private List<Mes> meses;
    private Dao<Mes> mesDAO;
    private Transacao transacao = new Transacao();
    private List<Transacao> transacoes;
    private Dao<Transacao> transacaoDAO;
    private Date data;

    public MensalidadeMB() throws PersistenceException {
        try {
            mes = new Mes();
            usuario = new Usuario();
            mensalidade = new Mensalidade();
            Session session = HibernateUtil.getSessionFactory().openSession();
            mensalidadeDAO = new GenericDAO<>(Mensalidade.class, session);
            mensalidades = mensalidadeDAO.buscarTodos();
            mesDAO = new GenericDAO<>(Mes.class, session);
            meses = mesDAO.buscarTodos();
            usuarioDAO = new GenericDAO<>(Usuario.class, session);
            usuarios = usuarioDAO.buscarTodos();
            inserir = true;
            session.close();
            limparTela();
        } catch (PersistenceException ex) {
            ex.printStackTrace();
        }
    }

    public void salvar() {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            mensalidadeDAO = new GenericDAO<>(Mensalidade.class, session);
            cadastrarTransacao();
            if (inserir) {
                //executar o método inserir do DAO
                mensalidadeDAO.salvar(mensalidade);
                System.out.println(mensalidade.getUsuario().getNome());
            } else {
                //executar o método alterar do DAO
                mensalidadeDAO.alterar(mensalidade);
            }
            inserir = true;
            mensalidade = new Mensalidade();
            mensalidades = mensalidadeDAO.buscarTodos();
            session.close();
        } catch (PersistenceException ex) {
            ex.printStackTrace();
        }
    }

    public void limparTela() {
        setMensalidade(new Mensalidade());
    }

    public void botaoExcluir(Mensalidade mensalidade) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            mensalidadeDAO = new GenericDAO<>(Mensalidade.class, session);
            mensalidadeDAO.excluir(mensalidade);
            this.mensalidade = new Mensalidade();
            mensalidades = mensalidadeDAO.buscarTodos();
            inserir = true;
            session.close();
        } catch (PersistenceException ex) {
            ex.printStackTrace();
        }

    }

    public String botaoAlterar(Mensalidade mensalidade) {
        System.out.println(mensalidade.getId());
        this.mensalidade = new Mensalidade(mensalidade.getId(), mensalidade.getUsuario(), mensalidade.getMes(), mensalidade.getAno(), mensalidade.getValor());
        inserir = false;
        return "-";
    }

    public void cadastrarTransacao() throws PersistenceException {
        data = new Date();
        char tipo = 'C';
        transacao = new Transacao(0, data, mensalidade.getUsuario().getNome(), tipo, mensalidade.getValor());
        Session session = HibernateUtil.getSessionFactory().openSession();
        transacaoDAO = new GenericDAO<>(Transacao.class, session);
        transacaoDAO.salvar(transacao);
        session.close();
        transacao = new Transacao();
    }

    public Mensalidade getMensalidade() {
        return mensalidade;
    }

    public void setMensalidade(Mensalidade mensalidade) {
        this.mensalidade = mensalidade;
    }

    public List<Mensalidade> getMensalidades() {
        return mensalidades;
    }

    public void setMensalidades(List<Mensalidade> mensalidades) {
        this.mensalidades = mensalidades;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public List<Mes> getMeses() {
        return meses;
    }

    public void setMeses(List<Mes> meses) {
        this.meses = meses;
    }

    public Mes getMes() {
        return mes;
    }

    public void setMes(Mes mes) {
        this.mes = mes;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
