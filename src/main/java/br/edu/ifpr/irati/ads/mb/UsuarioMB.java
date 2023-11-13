/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifpr.irati.ads.mb;

import br.edu.ifpr.irati.ads.dao.Dao;
import br.edu.ifpr.irati.ads.dao.GenericDAO;
import br.edu.ifpr.irati.ads.exception.PersistenceException;
import br.edu.ifpr.irati.ads.modelo.Funcao;
import br.edu.ifpr.irati.ads.modelo.Usuario;
import br.edu.ifpr.irati.ads.util.HibernateUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.hibernate.Session;

/**
 *
 * @author Caio
 */
@ManagedBean
@SessionScoped
public class UsuarioMB implements Serializable {

    private Usuario usuario = new Usuario();
    private List<Usuario> usuarios = new ArrayList<Usuario>();
    private Dao<Usuario> usuarioDAO;
    private boolean inserir;

    public UsuarioMB() throws PersistenceException {
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

    public String conferirLogin(Usuario usuario) {
        if (usuario.getNome().contains("") || usuario.getSenha().contains("")) {
            return "-";
        } else {
            
                for (Usuario u : usuarios) {
                    if (u.getNome().contains(usuario.getNome())) {
                        if (u.getSenha().contains(usuario.getSenha())) {
                            return "central.xhtml";
                        }

                    }
                }
            }
        return "-";
    }

    public void limparTela() {
        setUsuario(new Usuario());
    }

    public void salvar() {
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
        } catch (PersistenceException ex) {
            ex.printStackTrace();
        }
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

    /*public void botaoExcluir(Usuario usuario) {
        for(Usuario u: usuarios){
            if(u.getId()==usuario.getId()){
                usuarios.remove(u);
            }
        }
    }*/
    public String botaoAlterar(Usuario usuario) {
        System.out.println(usuario.getId());
        this.usuario = new Usuario(usuario.getId(), usuario.getNome(), usuario.getCpf(), usuario.getDataNascimento(), usuario.getEndereco(), usuario.getTelefone(), usuario.getEmail(), usuario.getSenha(), usuario.getMatricula(), usuario.getFuncao());
        inserir = false;
        return "-";
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

}
