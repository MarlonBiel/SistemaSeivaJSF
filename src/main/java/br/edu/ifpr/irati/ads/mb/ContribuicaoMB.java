/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifpr.irati.ads.mb;

import br.edu.ifpr.irati.ads.dao.Dao;
import br.edu.ifpr.irati.ads.dao.GenericDAO;
import br.edu.ifpr.irati.ads.exception.PersistenceException;
import br.edu.ifpr.irati.ads.modelo.Contribuicao;
import br.edu.ifpr.irati.ads.modelo.Usuario;
import br.edu.ifpr.irati.ads.util.HibernateUtil;
import java.io.Serializable;
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
public class ContribuicaoMB implements Serializable{
    private Contribuicao contribuicao = new Contribuicao();
    private List<Contribuicao> contribuicaos;
    private Dao<Contribuicao> contribuicaoDAO;
    private Dao<Usuario> usuarioDAO;
    private boolean inserir;
    private String usuario;
    
    private List<Usuario> listaUsuarios;
    private List<Usuario> listaUsuariosFiltro;
    public ContribuicaoMB() throws PersistenceException{
        try {
            contribuicao = new Contribuicao();
            Session session = HibernateUtil.getSessionFactory().openSession();
            contribuicaoDAO = new GenericDAO<>(Contribuicao.class, session);
            usuarioDAO = new GenericDAO<>(Usuario.class, session);
            contribuicaos = contribuicaoDAO.buscarTodos();
            listaUsuarios = usuarioDAO.buscarTodos();
            usuario="";
            inserir = true;
            session.close();
            limparTela();
        } catch (PersistenceException ex) {
            ex.printStackTrace();
        }
    }
    
    public void limparTela() {
        setContribuicao(new Contribuicao());
    }
    public void salvar() {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            contribuicaoDAO = new GenericDAO<>(Contribuicao.class, session);

            if (inserir) {
                //executar o método inserir do DAO
                contribuicaoDAO.salvar(contribuicao);
            } else {
                //executar o método alterar do DAO
                contribuicaoDAO.alterar(contribuicao);
            }
            inserir = true;
            contribuicao = new Contribuicao();
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
    public String botaoAlterar(Contribuicao contribuicao) {
        System.out.println(contribuicao.getId());
        this.contribuicao = new Contribuicao(contribuicao.getId(), contribuicao.getUsuario(), contribuicao.getValor(), contribuicao.getData(), contribuicao.getFormaContribuicao(), contribuicao.getTipo());
        inserir = false;
        return "-";
    }
    public void localizarUsuario(String nomeUsuario){
        for(Usuario u:listaUsuarios){
            if(u.getNome().contains(nomeUsuario)){
                listaUsuariosFiltro.add(u);
            }
        }
    }
    public void limparFiltroUsuario() throws PersistenceException{
        contribuicaos = contribuicaoDAO.buscarTodos();
    }
    /**
     * @return the contribuicao
     */
    public Contribuicao getContribuicao() {
        return contribuicao;
    }
    
    /**
     * @param contribuicao the contribuicao to set
     */
    public void setContribuicao(Contribuicao contribuicao) {
        this.contribuicao = contribuicao;
    }

    /**
     * @return the contribuicaos
     */
    public List<Contribuicao> getContribuicaos() {
        return contribuicaos;
    }

    /**
     * @param contribuicaos the contribuicaos to set
     */
    public void setContribuicaos(List<Contribuicao> contribuicaos) {
        this.contribuicaos = contribuicaos;
    }

    /**
     * @return the usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the listaUsuarios
     */
    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    /**
     * @param listaUsuarios the listaUsuarios to set
     */
    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    /**
     * @return the usuarioDAO
     */
    public Dao<Usuario> getUsuarioDAO() {
        return usuarioDAO;
    }

    /**
     * @param usuarioDAO the usuarioDAO to set
     */
    public void setUsuarioDAO(Dao<Usuario> usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    /**
     * @return the listaUsuariosFiltro
     */
    public List<Usuario> getListaUsuariosFiltro() {
        return listaUsuariosFiltro;
    }

    /**
     * @param listaUsuariosFiltro the listaUsuariosFiltro to set
     */
    public void setListaUsuariosFiltro(List<Usuario> listaUsuariosFiltro) {
        this.listaUsuariosFiltro = listaUsuariosFiltro;
    }
    
    
}
