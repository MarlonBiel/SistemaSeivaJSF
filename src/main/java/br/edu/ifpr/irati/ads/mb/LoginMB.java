
package br.edu.ifpr.irati.ads.mb;

import br.edu.ifpr.irati.ads.dao.Dao;
import br.edu.ifpr.irati.ads.dao.GenericDAO;
import br.edu.ifpr.irati.ads.exception.PersistenceException;
import br.edu.ifpr.irati.ads.modelo.Usuario;
import br.edu.ifpr.irati.ads.util.HibernateUtil;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
    
import org.hibernate.Session;

@SessionScoped
@ManagedBean
public class LoginMB implements Serializable{
    
    private Usuario usuario = new Usuario();
    private List<Usuario> usuarios;
    private Dao<Usuario> usuarioDAO;
    private String email;
    private String senha;
    
    public String login() throws PersistenceException{
        try {
            usuario = new Usuario();
            Session session = HibernateUtil.getSessionFactory().openSession();
            usuarioDAO = new GenericDAO<>(Usuario.class, session);
            usuarios = usuarioDAO.buscarTodos();
            
            for(Usuario u : usuarios){
                if(email.equalsIgnoreCase(u.getEmail())){
                    if(senha.equals(u.getSenha())){
                        usuario = u;
                        return "restricted/central.xhtml?faces-redirect=true";
                    }else{
                        FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Senha incorreta!","Senha Incorreta!"));
                        break;
                    }
                }else{
                    FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Email incorreta!","Email Incorreta!"));
                    break;
                }
            }
            
        } catch (PersistenceException ex) {
            ex.printStackTrace();
        }
        return "";
    }
    
    public String logout(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/index.xhtml?faces-redirect=true";
    }
    
    
    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }
    
}
