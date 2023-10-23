/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifpr.irati.ads.mb;

import br.edu.ifpr.irati.ads.dao.Dao;
import br.edu.ifpr.irati.ads.dao.GenericDAO;
import br.edu.ifpr.irati.ads.exception.PersistenceException;
import br.edu.ifpr.irati.ads.modelo.Usuario;
import br.edu.ifpr.irati.ads.util.HibernateUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.hibernate.Session;

/**
 *
 * @author Caio
 */
@ManagedBean
@RequestScoped
public class UsuarioMB implements Serializable{
    private List<Usuario> usuarios;
    private Usuario usuario;
    private Session session;
    private Dao<Usuario> usuarioDAO;
    
    public UsuarioMB() {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            usuarioDAO = new GenericDAO<>(Usuario.class, session);
            usuarios = usuarioDAO.buscarTodos();
            usuario = new Usuario();
            session.close();
        } catch (PersistenceException ex) {
            ex.printStackTrace();
        }
    }
    public void limparTela(){
        this.setUsuario(new Usuario());
    }
    public void salvar(){
        try {      
            //usuario.setNome("caio");
            Session session = HibernateUtil.getSessionFactory().openSession();
            Dao<Usuario> usuarioDAO = new GenericDAO<>(Usuario.class, session);            
            usuarioDAO.alterar(usuario);
            session.close();
            usuario = new Usuario();
        } catch (PersistenceException ex) {
            ex.printStackTrace();
        }
    }    
    
    
    public void entrar(String login, String senha){
        boolean flag=consulta(login, senha);
        if(flag){
            System.out.println("entremo");
        }
        else{
            System.out.println("nao deu brik");
        }
        
    }
    protected boolean consulta(String login, String senha){
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            usuarioDAO = new GenericDAO<>(Usuario.class, session);
            usuarios=usuarioDAO.buscarTodos();
            for(Usuario u: usuarios){
                if(u.getNome().contains(login) & u.getSenha().contains(senha)){
                    return true;
                }
                else{
                    return false;
                }
            }
            session.close();
            this.limparTela();
        } catch (PersistenceException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    /**
     * @return the usuarios
     */
    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    /**
     * @param usuarios the usuarios to set
     */
    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    /**
     * @return the usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
}
