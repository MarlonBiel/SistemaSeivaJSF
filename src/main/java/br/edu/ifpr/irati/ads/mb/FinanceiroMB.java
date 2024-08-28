package br.edu.ifpr.irati.ads.mb;

import br.edu.ifpr.irati.ads.dao.Dao;
import br.edu.ifpr.irati.ads.dao.GenericDAO;
import br.edu.ifpr.irati.ads.exception.PersistenceException;
import br.edu.ifpr.irati.ads.modelo.Transacao;
import br.edu.ifpr.irati.ads.modelo.Usuario;
import br.edu.ifpr.irati.ads.util.HibernateUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.hibernate.Session;

@ManagedBean
@ViewScoped
public class FinanceiroMB implements Serializable {

    private Transacao transacao = new Transacao();
    private List<Transacao> transacoes;
    private List<Transacao> transacoesFiltrada;
    private Dao<Transacao> transacaoDAO;
    private Date dataInicial;
    private Date dataFinal;
    private String errorMessage;
    private Locale locale = new Locale("pt", "BR");

    public FinanceiroMB() throws PersistenceException {
        transacao = new Transacao();
        atualizarTransacoes();
    }

    public Transacao getTransacao() {
        return transacao;
    }

    public void setTransacao(Transacao transacao) {
        this.transacao = transacao;
    }

    public void atualizarTransacoes() throws PersistenceException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        transacaoDAO = new GenericDAO<>(Transacao.class, session);
        transacoes = transacaoDAO.buscarTodos();
        session.close();

        if (dataFinal != null && dataInicial != null) {
            transacoesFiltrada = transacoes.stream().filter(transacao -> !transacao.getData().before(dataInicial) && !transacao.getData().after(dataFinal)).sorted((i1, i2) -> i1.getData().compareTo(i2.getData())).collect(Collectors.toList());

            if (transacoesFiltrada.isEmpty()) {
                setErrorMessage("Nenhuma transação encontrada neste periodo");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, getErrorMessage(),errorMessage));
            } else {
                setErrorMessage(null); // Clear the error message if items are found
            }

        } else {
            errorMessage = "Selecione os dois campos de data para a busca";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, getErrorMessage(), errorMessage));
            transacoesFiltrada = new ArrayList<>(transacoes);
        }

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

    public Date getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(Date dataInicial) {
        this.dataInicial = dataInicial;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

    public List<Transacao> getTransacoesFiltrada() {
        return transacoesFiltrada;
    }

    public void setTransacoesFiltrada(List<Transacao> transacoesFiltrada) {
        this.transacoesFiltrada = transacoesFiltrada;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Locale getLocale() {
        return locale;
    }

}
