package br.edu.ifpr.irati.ads.mb;

import br.edu.ifpr.irati.ads.dao.Dao;
import br.edu.ifpr.irati.ads.dao.GenericDAO;
import br.edu.ifpr.irati.ads.exception.PersistenceException;
import br.edu.ifpr.irati.ads.modelo.Transacao;
import br.edu.ifpr.irati.ads.modelo.Usuario;
import br.edu.ifpr.irati.ads.util.HibernateUtil;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.hibernate.Session;

@ManagedBean 
@ViewScoped
public class FinanceiroMB implements Serializable {

    private Transacao transacao = new Transacao();
    private List<Transacao> transacoes;
    private Dao<Transacao> transacaoDAO;

    public FinanceiroMB() throws PersistenceException {
        transacao = new Transacao();
        Session session = HibernateUtil.getSessionFactory().openSession();
        transacaoDAO = new GenericDAO<>(Transacao.class, session);
        transacoes = transacaoDAO.buscarTodos();
        session.close();
    }

    public Transacao getTransacao() {
        return transacao;
    }

    public void setTransacao(Transacao transacao) {
        this.transacao = transacao;
    }

    public List<Transacao> getTransacoes() throws PersistenceException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        transacaoDAO = new GenericDAO<>(Transacao.class, session);
        transacoes = transacaoDAO.buscarTodos();
        session.close();
        return transacoes;
    }

    public void setTransacoes(List<Transacao> transacoes) {
        this.transacoes = transacoes;
    }
    
    public String botaoVoltar() {
        return "/restricted/central.xhtml?faces-redirect=true";
    }

}
