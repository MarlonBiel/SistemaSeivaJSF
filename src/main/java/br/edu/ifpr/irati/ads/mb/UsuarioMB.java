package br.edu.ifpr.irati.ads.mb;

import br.edu.ifpr.irati.ads.dao.Dao;
import br.edu.ifpr.irati.ads.dao.GenericDAO;
import br.edu.ifpr.irati.ads.exception.PersistenceException;
import br.edu.ifpr.irati.ads.modelo.Funcao;
import br.edu.ifpr.irati.ads.modelo.Usuario;
import br.edu.ifpr.irati.ads.util.HibernateUtil;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.hibernate.Session;

@ManagedBean
@SessionScoped
public class UsuarioMB implements Serializable {

    private Usuario usuario = new Usuario();
    private Usuario usuarioExistent = new Usuario();
    private List<Usuario> usuarios;
    private List<Usuario> usuariosCompleto;
    private Dao<Usuario> usuarioDAO;
    private boolean inserir;
    private Dao<Funcao> funcaoDAO;
    private List<Funcao> funcoes;

    public UsuarioMB() throws PersistenceException {
        try {
            usuario = new Usuario();
            Session session = HibernateUtil.getSessionFactory().openSession();
            usuarioDAO = new GenericDAO<>(Usuario.class, session);
            atualizarList();

            inserir = true;
            session.close();
        } catch (PersistenceException ex) {
            ex.printStackTrace();
        }
    }

    public void atualizarList() throws PersistenceException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        usuarioDAO = new GenericDAO<>(Usuario.class, session);
        usuariosCompleto = usuarioDAO.buscarTodos();
        usuarios = usuariosCompleto.stream().filter(usuario -> usuario.getDataExclusao() == null).collect(Collectors.toList());
        session.close();
    }

    public String salvar() {
        try {
            atualizarList();
            Session session = HibernateUtil.getSessionFactory().openSession();
            usuarioDAO = new GenericDAO<>(Usuario.class, session);          
            for(Usuario u : usuariosCompleto){
                if(u.getCpf().contains(usuario.getCpf())){
                    inserir = false;
                    usuario.setId(u.getId());
                    usuario.setDataCricao(u.getDataCriacao());
                    usuario.setDataExclusao(null);
                }
            }
            if (inserir) {   
                //executar o método inserir do DAO
                usuario.setDataCricao(new Date());
                usuarioDAO.salvar(usuario);
            } else {
                //executar o método alterar do DAO
                usuarioDAO.alterar(usuario);
            }
            inserir = true;
            usuario = new Usuario();
            atualizarList();

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
            usuario.setDataExclusao(new Date());
            usuarioDAO.alterar(usuario);
            //usuarioDAO.excluir(usuario);
            this.usuario = new Usuario();
            atualizarList();
            inserir = true;
            session.close();
        } catch (PersistenceException ex) {
            ex.printStackTrace();
        }
    }

    public String botaoAlterar(Usuario usuario) {
        System.out.println(usuario.getId());
        this.usuario = new Usuario(usuario.getId(), usuario.getNome(), usuario.getCpf(), usuario.getDataNascimento(), usuario.getEndereco(), usuario.getTelefone(), usuario.getEmail(), usuario.getSenha(), usuario.getMatricula(), usuario.getFuncao(), usuario.getDataCriacao(), usuario.getDataExclusao());
        inserir = false;
        return "/restricted/user/usuario_edit.xhtml?faces-redirect=true";
    }

    public String botaoAcessoCadastro() {
        this.usuario = new Usuario();
        return "/restricted/user/usuario_edit.xhtml?faces-redirect=true";
    }

    public String botaoVoltar(boolean flagTelaEdit) {
        this.usuario = new Usuario();
        if (flagTelaEdit) {
            return "/restricted/user/usuario.xhtml?faces-redirect=true";
        }
        return "/restricted/central.xhtml?faces-redirect=true";
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
