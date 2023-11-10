/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifpr.irati.ads.mb;

import br.edu.ifpr.irati.ads.dao.Dao;
import br.edu.ifpr.irati.ads.dao.GenericDAO;
import br.edu.ifpr.irati.ads.exception.PersistenceException;
import br.edu.ifpr.irati.ads.modelo.Despesa;
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
public class DespesaMB implements Serializable {

    private Despesa despesa = new Despesa();
    private List<Despesa> despesas;
    private Dao<Despesa> despesaDAO;
    private boolean inserir;

    public DespesaMB() throws PersistenceException {
        try {
            despesa = new Despesa();
            Session session = HibernateUtil.getSessionFactory().openSession();
            despesaDAO = new GenericDAO<>(Despesa.class, session);
            despesas = despesaDAO.buscarTodos();

            inserir = true;
            session.close();
            limparTela();
        } catch (PersistenceException ex) {
            ex.printStackTrace();
        }
    }

    public void limparTela() {
        setDespesa(new Despesa());
    }

    public void salvar() {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            despesaDAO = new GenericDAO<>(Usuario.class, session);

            if (inserir) {
                //executar o método inserir do DAO
                despesaDAO.salvar(despesa);
            } else {
                //executar o método alterar do DAO
                despesaDAO.alterar(despesa);
            }
            inserir = true;
            despesa = new Despesa();
            despesas = despesaDAO.buscarTodos();
            session.close();
        } catch (PersistenceException ex) {
            ex.printStackTrace();
        }
    }

    public void botaoExcluir(Despesa despesa) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            despesaDAO = new GenericDAO<>(Despesa.class, session);
            despesaDAO.excluir(despesa);
            this.despesa = new Despesa();
            despesas = despesaDAO.buscarTodos();
            inserir = true;
            session.close();
        } catch (PersistenceException ex) {
            ex.printStackTrace();
        }

    }
    public String botaoAlterar(Despesa despesa) {
        System.out.println(despesa.getId());
        this.despesa = new Despesa(despesa.getId(), despesa.getDescriminacao(), despesa.getData(), despesa.getValor(), despesa.getFormaPagamento(), despesa.getObservacao(), despesa.getAnexos());
        inserir = false;
        return "-";
    }
    /**
     * @return the despesa
     */
    public Despesa getDespesa() {
        return despesa;
    }

    /**
     * @param despesa the despesa to set
     */
    public void setDespesa(Despesa despesa) {
        this.despesa = despesa;
    }

    /**
     * @return the despesas
     */
    public List<Despesa> getDespesas() {
        return despesas;
    }

    /**
     * @param despesas the despesas to set
     */
    public void setDespesas(List<Despesa> despesas) {
        this.despesas = despesas;
    }

}
