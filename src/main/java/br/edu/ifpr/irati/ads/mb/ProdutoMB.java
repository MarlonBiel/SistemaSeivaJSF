package br.edu.ifpr.irati.ads.mb;

import br.edu.ifpr.irati.ads.dao.Dao;
import br.edu.ifpr.irati.ads.dao.GenericDAO;
import br.edu.ifpr.irati.ads.exception.PersistenceException;
import br.edu.ifpr.irati.ads.modelo.Produto;
import br.edu.ifpr.irati.ads.util.HibernateUtil;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.hibernate.Session;


@ManagedBean
@SessionScoped
public class ProdutoMB implements Serializable{
    private Produto produto = new Produto();
    private List<Produto> produtos;
    private Dao<Produto> produtoDAO;
    private boolean inserir;

    public ProdutoMB() throws PersistenceException{
        try {
            produto = new Produto();
            Session session = HibernateUtil.getSessionFactory().openSession();
            produtoDAO = new GenericDAO<>(Produto.class, session);
            produtos = produtoDAO.buscarTodos();

            inserir = true;
            session.close();
            limparTela();
        } catch (PersistenceException ex) {
            ex.printStackTrace();
        }
    }
    public void limparTela() {
        setProduto(new Produto());
    }
    public String salvar() {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            produtoDAO = new GenericDAO<>(Produto.class, session);

            if (inserir) {
                //executar o método inserir do DAO
                produtoDAO.salvar(produto);
            } else {
                //executar o método alterar do DAO
                produtoDAO.alterar(produto);
            }
            inserir = true;
            produto = new Produto();
            produtos = produtoDAO.buscarTodos();
            session.close();
            return "/restricted/stock/produto.xhtml?faces-redirect=true";
        } catch (PersistenceException ex) {
            ex.printStackTrace();
        }
        return "";
    }
    public void botaoExcluir(Produto produto) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            produtoDAO = new GenericDAO<>(Produto.class, session);
            produtoDAO.excluir(produto);
            this.produto = new Produto();
            produtos = produtoDAO.buscarTodos();
            inserir = true;
            session.close();
        } catch (PersistenceException ex) {
            ex.printStackTrace();
        }

    }
    public String botaoAlterar(Produto produto) {
        System.out.println(produto.getId());
        this.produto = new Produto(produto.getId(), produto.getNome(), produto.getQuantEstoque(), produto.getValor(), produto.getIan(), produto.getValorVenda());
        inserir = false;
        return "/restricted/stock/produto_edit.xhtml?faces-redirect=true";
    }
    
    public String botaoAcessoCadastro() {
        this.produto = new Produto();
        return "/restricted/stock/produto_edit.xhtml?faces-redirect=true";
    }

    public String botaoVoltar(boolean flagTelaEdit) {
        this.produto = new Produto();
        if(flagTelaEdit){
            return "/restricted/stock/produto.xhtml?faces-redirect=true";
        }
        return "/restricted/central.xhtml?faces-redirect=true";
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }
    
    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }
    
    
}
