/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifpr.irati.ads.mb;

import br.edu.ifpr.irati.ads.dao.Dao;
import br.edu.ifpr.irati.ads.dao.GenericDAO;
import br.edu.ifpr.irati.ads.exception.PersistenceException;
import br.edu.ifpr.irati.ads.modelo.Estoque;
import br.edu.ifpr.irati.ads.modelo.Usuario;
import br.edu.ifpr.irati.ads.util.HibernateUtil;
import java.io.Serializable;
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
public class EstoqueMB implements Serializable{
    private List<Estoque> estoques;
    private Estoque estoque;
    private Session session;
    private Dao<Estoque> estoqueDAO;
    public EstoqueMB() {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            estoqueDAO = new GenericDAO<>(Estoque.class, session);
            estoques = estoqueDAO.buscarTodos();
            estoque = new Estoque();
            session.close();
        } catch (PersistenceException ex) {
            ex.printStackTrace();
        }
    }
    public void limparTela(){
        this.setEstoque(new Estoque());
    }
    public void salvar(){
        try {      
            //usuario.setNome("caio");
            Session session = HibernateUtil.getSessionFactory().openSession();
            Dao<Estoque> estoqueDAO = new GenericDAO<>(Estoque.class, session);            
            estoqueDAO.alterar(estoque);
            session.close();
            estoque = new Estoque();
        } catch (PersistenceException ex) {
            ex.printStackTrace();
        }
    }   
    /**
     * @return the estoques
     */
    public List<Estoque> getEstoques() {
        return estoques;
    }

    /**
     * @param estoques the estoques to set
     */
    public void setEstoques(List<Estoque> estoques) {
        this.estoques = estoques;
    }

    /**
     * @return the estoque
     */
    public Estoque getEstoque() {
        return estoque;
    }

    /**
     * @param estoque the estoque to set
     */
    public void setEstoque(Estoque estoque) {
        this.estoque = estoque;
    }
    
}
