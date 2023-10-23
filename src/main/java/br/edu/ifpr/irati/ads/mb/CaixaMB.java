/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifpr.irati.ads.mb;

import br.edu.ifpr.irati.ads.dao.Dao;
import br.edu.ifpr.irati.ads.dao.GenericDAO;
import br.edu.ifpr.irati.ads.exception.PersistenceException;
import br.edu.ifpr.irati.ads.modelo.Caixa;
import br.edu.ifpr.irati.ads.util.HibernateUtil;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import org.hibernate.Session;

/**
 *
 * @author Caio
 */
@ManagedBean
public class CaixaMB implements Serializable{
    private List<Caixa> caixas;
    private Caixa caixa;
    private Session session;
    private Dao<Caixa> caixaDAO;
    public CaixaMB() {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            caixaDAO = new GenericDAO<>(Caixa.class, session);
            caixas = caixaDAO.buscarTodos();
            caixa = new Caixa();
            session.close();
        } catch (PersistenceException ex) {
            ex.printStackTrace();
        }
    }
    public void limparTela(){
        this.setCaixa(new Caixa());
    }
    public void salvar(){
        try {      
            //usuario.setNome("caio");
            Session session = HibernateUtil.getSessionFactory().openSession();
            Dao<Caixa> caixaDAO = new GenericDAO<>(Caixa.class, session);            
            caixaDAO.alterar(caixa);
            session.close();
            caixa = new Caixa();
        } catch (PersistenceException ex) {
            ex.printStackTrace();
        }
    }   
    /**
     * @return the caixas
     */
    public List<Caixa> getCaixas() {
        return caixas;
    }

    /**
     * @param caixas the caixas to set
     */
    public void setCaixas(List<Caixa> caixas) {
        this.caixas = caixas;
    }

    /**
     * @return the caixa
     */
    public Caixa getCaixa() {
        return caixa;
    }

    /**
     * @param caixa the caixa to set
     */
    public void setCaixa(Caixa caixa) {
        this.caixa = caixa;
    }
    
}
