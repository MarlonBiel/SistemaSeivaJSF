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
public class VendaMB implements Serializable {

    private boolean inserir;
    private int estoque;
    private double valorTotal;

    private ProdutoVenda produtoVenda = new ProdutoVenda();
    private Dao<ProdutoVenda> produtoVendaDAO;
    private List<ProdutoVenda> produtosVendas;
    private List<ProdutoVenda> listaFiltrada = new ArrayList<>();
    private String nomeProduto = "";

    private Venda venda = new Venda();
    private List<Venda> vendas;
    private Dao<Venda> vendaDAO;

    private Produto produto = new Produto();
    private Dao<Produto> produtoDAO;
    private List<Produto> produtos;

    public VendaMB() throws PersistenceException {
        try {
            venda = new Venda();
            produtoVenda = new ProdutoVenda();
            produtosVendas = new ArrayList<>();
            atualizaLista();
            Session session = HibernateUtil.getSessionFactory().openSession();
            produtoDAO = new GenericDAO<>(Produto.class, session);
            produtos = produtoDAO.buscarTodos();

            inserir = true;
            session.close();
        } catch (PersistenceException ex) {
            ex.printStackTrace();
        }
    }

    public void atualizaLista() throws PersistenceException {
        listaFiltrada = new ArrayList<>();
        getListaProdutosVendasFiltrada();
    }
    
    public double valorTotalVenda() throws PersistenceException {
        listaFiltrada = new ArrayList<>();
        valorTotal = 0;
        getListaProdutosVendasFiltrada();
        for(ProdutoVenda pv : listaFiltrada){
            valorTotal = valorTotal + (pv.getProduto().getValorVenda() * pv.getQuantVenda());
        }
        return valorTotal;
    }

    public void realziarVenda() {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            vendaDAO = new GenericDAO<>(Venda.class, session);
            venda.setVendaAtual(getListaFiltrada());
            produtosVendas = new ArrayList<>();
            baixarEstoque();
            if (inserir) {
                //executar o método inserir do DAO
                vendaDAO.salvar(venda);
            } else {
                //executar o método alterar do DAO
                vendaDAO.alterar(venda);
            }
            atualizaLista();
            inserir = true;
            session.close();
        } catch (PersistenceException ex) {
            ex.printStackTrace();
        }
    }

    public void baixarEstoque() throws PersistenceException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        produtoDAO = new GenericDAO<>(Produto.class, session);
        produtos = produtoDAO.buscarTodos();
        for (ProdutoVenda pv : listaFiltrada) {
            pv.getProduto().setQuantEstoque(pv.getProduto().getQuantEstoque() - pv.getQuantVenda());
            produtoDAO.alterar(pv.getProduto());
        }

    }

    public void adicionarProdutoNaVenda() throws PersistenceException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        produtoVendaDAO = new GenericDAO<>(ProdutoVenda.class, session);
        produtoVendaDAO.salvar(produtoVenda);
        produtoVenda = new ProdutoVenda();
        atualizaLista();
    }

    public void botaoExcluir(ProdutoVenda produtoVenda) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            produtoVendaDAO = new GenericDAO<>(ProdutoVenda.class, session);
            produtoVendaDAO.excluir(produtoVenda);
            inserir = true;
            atualizaLista();
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

    public List<ProdutoVenda> getProdutosVendas() throws PersistenceException {
        return listaFiltrada;
    }
    
    public void getListaProdutosVendasFiltrada() throws PersistenceException{
        Session session = HibernateUtil.getSessionFactory().openSession();
        produtoVendaDAO = new GenericDAO<>(ProdutoVenda.class, session);
        produtosVendas = produtoVendaDAO.buscarTodos();
        for(ProdutoVenda pv : produtosVendas){
            if(pv.getVenda() == null){
                listaFiltrada.add(pv);
                nomeProduto = pv.getProduto().toString();
                System.out.println(pv.getProduto().getNome());
            }
        }
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

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public List<ProdutoVenda> getListaFiltrada() {
        return listaFiltrada;
    }

    public void setListaFiltrada(List<ProdutoVenda> listaFiltrada) {
        this.listaFiltrada = listaFiltrada;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

}
