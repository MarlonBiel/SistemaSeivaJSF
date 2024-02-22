package br.edu.ifpr.irati.ads.mb;

import br.edu.ifpr.irati.ads.dao.Dao;
import br.edu.ifpr.irati.ads.dao.GenericDAO;
import br.edu.ifpr.irati.ads.exception.PersistenceException;
import br.edu.ifpr.irati.ads.modelo.Usuario;
import br.edu.ifpr.irati.ads.util.HibernateUtil;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.hibernate.Session;

@Named
@SessionScoped
public class LoginMB implements Serializable {

    private String email;
    private String senha;
    private Usuario usuario;
    private List<Usuario> usuarios;
    private Dao<Usuario> usuarioDAO;

    public String logar() throws PersistenceException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        usuarioDAO = new GenericDAO<>(Usuario.class, session);
        usuarios = usuarioDAO.buscarTodos();
        for (Usuario u : usuarios) {
            if (email.equals(u.getEmail()) && senha.equals(u.getSenha())) {
                usuario = u;
                return "/restricted/central.xhtml?faces-redirect=true";
            }
        }
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario e/ou senha incorreto", ""));
        return "";
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        setUsuario(null);
        return "/index.xhtml?faces-redirect=true";
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
