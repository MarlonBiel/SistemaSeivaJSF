/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifpr.irati.ads.mb;

import br.edu.ifpr.irati.ads.dao.Dao;
import br.edu.ifpr.irati.ads.dao.GenericDAO;
import br.edu.ifpr.irati.ads.exception.PersistenceException;
import br.edu.ifpr.irati.ads.modelo.Mensalidade;
import br.edu.ifpr.irati.ads.modelo.Mes;
import br.edu.ifpr.irati.ads.modelo.Usuario;
import br.edu.ifpr.irati.ads.util.HibernateUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.hibernate.Session;

/**
 *
 * @author Caio
 */
@ManagedBean
@ViewScoped
public class MensalidadeMB implements Serializable{
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
    public MensalidadeMB() throws PersistenceException{
        try {
            mes = new Mes();
           usuario=new Usuario();
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

            if (inserir) {
                //executar o método inserir do DAO
                mensalidadeDAO.salvar(mensalidade);
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
    /**
     * @return the mensalidade
     */
    public Mensalidade getMensalidade() {
        return mensalidade;
    }

    /**
     * @param mensalidade the mensalidade to set
     */
    public void setMensalidade(Mensalidade mensalidade) {
        this.mensalidade = mensalidade;
    }
    
    /**
     * @return the mensalidades
     */
    public List<Mensalidade> getMensalidades() {
        return mensalidades;
    }

    /**
     * @param mensalidades the mensalidades to set
     */
    public void setMensalidades(List<Mensalidade> mensalidades) {
        this.mensalidades = mensalidades;
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
     * @return the meses
     */
    public List<Mes> getMeses() {
        return meses;
    }

    /**
     * @param meses the meses to set
     */
    public void setMeses(List<Mes> meses) {
        this.meses = meses;
    }

    /**
     * @return the mes
     */
    public Mes getMes() {
        return mes;
    }

    /**
     * @param mes the mes to set
     */
    public void setMes(Mes mes) {
        this.mes = mes;
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
