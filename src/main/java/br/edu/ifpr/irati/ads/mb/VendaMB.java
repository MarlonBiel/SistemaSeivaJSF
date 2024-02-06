package br.edu.ifpr.irati.ads.mb;

import br.edu.ifpr.irati.ads.dao.Dao;
import br.edu.ifpr.irati.ads.dao.GenericDAO;
import br.edu.ifpr.irati.ads.exception.PersistenceException;
import br.edu.ifpr.irati.ads.modelo.Produto;
import br.edu.ifpr.irati.ads.modelo.ProdutoVenda;
import br.edu.ifpr.irati.ads.modelo.Venda;
import br.edu.ifpr.irati.ads.util.HibernateUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.hibernate.Session;

@ManagedBean
@ViewScoped 
public class VendaMB implements Serializable{
    private Venda venda = new Venda();
    private ProdutoVenda produtoVenda = new ProdutoVenda();
    private List<Venda> vendas;
    private Dao<Venda> vendaDAO;
    private boolean inserir;
    private Dao<Produto> produtoDAO;
    private List<Produto> produtos;
    private Dao<ProdutoVenda> produtoVendaDAO;
    private List<ProdutoVenda> produtosVendas;
    private int estoque;

    public VendaMB() throws PersistenceException{
        try {
            venda = new Venda();
            produtoVenda = new ProdutoVenda();
            produtosVendas = new ArrayList<>();
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
    public void limparTela(){
        produtoVenda = new ProdutoVenda();
    }
    
    
    public void realziarVenda() {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            vendaDAO = new GenericDAO<>(Venda.class, session);

            if (inserir) {
                //executar o método inserir do DAO
                vendaDAO.salvar(venda);
            } else {
                //executar o método alterar do DAO
                vendaDAO.alterar(venda);
            }
            inserir = true;
            venda = new Venda();
            session.close();
        } catch (PersistenceException ex) {
            ex.printStackTrace();
        }
    }
    
    public void adicionarProdutoNaVenda(){
        produtosVendas.add(produtoVenda);
    }
    
    
    public void updateQuantidadeEmEstoque(){
        this.setEstoque(produtoVenda.getProduto().getQuantEstoque());
    }
    
    public void botaoExcluir(ProdutoVenda produtoVenda) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            produtoVendaDAO = new GenericDAO<>(ProdutoVenda.class, session);
            produtoVendaDAO.excluir(produtoVenda);
            inserir = true;
            session.close();
        } catch (PersistenceException ex) {
            ex.printStackTrace();
        }
    }
    
    public String botaoAlterar(ProdutoVenda produtoVenda) {
        this.produtoVenda = new ProdutoVenda(produtoVenda.getId(), produtoVenda.getProduto(), produtoVenda.getQuantVenda());
        inserir = false;
        return "-";
    }
    
    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public List<Venda> getVendas() {
        return vendas;
    }

    public void setVendas(List<Venda> vendas) {
        this.vendas = vendas;
    }

    public List<Produto> getProdutos() throws PersistenceException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        produtoDAO = new GenericDAO<>(Produto.class, session);
        setProdutos(produtoDAO.buscarTodos());
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public List<ProdutoVenda> getProdutosVendas() {
        return produtosVendas;
    }

    public void setProdutosVendas(List<ProdutoVenda> produtosVendas) {
        this.produtosVendas = produtosVendas;
    }

    public ProdutoVenda getProdutoVenda() {
        return produtoVenda;
    }

    public void setProdutoVenda(ProdutoVenda produtoVenda) {
        this.produtoVenda = produtoVenda;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

}
