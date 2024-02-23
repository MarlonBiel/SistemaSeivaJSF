package br.edu.ifpr.irati.ads.mb;

import br.edu.ifpr.irati.ads.dao.Dao;
import br.edu.ifpr.irati.ads.dao.GenericDAO;
import br.edu.ifpr.irati.ads.exception.PersistenceException;
import br.edu.ifpr.irati.ads.modelo.Funcao;
import br.edu.ifpr.irati.ads.modelo.Usuario;
import br.edu.ifpr.irati.ads.util.HibernateUtil;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import org.hibernate.Session;

@ManagedBean
@SessionScoped 
public class UsuarioMB implements Serializable{
    private Usuario usuario = new Usuario();
    private List<Usuario> usuarios;
    private Dao<Usuario> usuarioDAO;
    private boolean inserir;
    private Dao<Funcao> funcaoDAO;
    private List<Funcao> funcoes;

    public UsuarioMB() throws PersistenceException{
        try {
            usuario = new Usuario();
            Session session = HibernateUtil.getSessionFactory().openSession();
            usuarioDAO = new GenericDAO<>(Usuario.class, session);
            usuarios = usuarioDAO.buscarTodos();

            inserir = true;
            session.close();
            limparTela();
        } catch (PersistenceException ex) {
            ex.printStackTrace();
        }
    }
    public void limparTela(){
        setUsuario(new Usuario());
    }
    public String salvar() {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            usuarioDAO = new GenericDAO<>(Usuario.class, session);

            if (inserir) {
                //executar o método inserir do DAO
                usuarioDAO.salvar(usuario);
            } else {
                //executar o método alterar do DAO
                usuarioDAO.alterar(usuario);
            }
            inserir = true;
            usuario = new Usuario();
            usuarios = usuarioDAO.buscarTodos();
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
            this.usuario = new Usuario();
            usuarios = usuarioDAO.buscarTodos();
            inserir = true;
            session.close();
        } catch (PersistenceException ex) {
            ex.printStackTrace();
        }
    }
    
    public String botaoAlterar(Usuario usuario) {
        System.out.println(usuario.getId());
        this.usuario = new Usuario(usuario.getId(), usuario.getNome(), usuario.getCpf(), usuario.getDataNascimento(), usuario.getEndereco(), usuario.getTelefone(), usuario.getEmail(), usuario.getSenha(), usuario.getMatricula(), usuario.getFuncao());
        inserir = false;
        return "/restricted/user/usuario_edit.xhtml?faces-redirect=true";
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
    

    public List<Funcao> getFuncoes() throws PersistenceException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        funcaoDAO = new GenericDAO<>(Funcao.class, session);
        setFuncoes(funcaoDAO.buscarTodos());
        return funcoes;
    }

    public void setFuncoes(List<Funcao> funcoes) {
        this.funcoes = funcoes;
    }
}
