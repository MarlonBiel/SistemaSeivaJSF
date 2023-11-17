/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
import javax.faces.bean.SessionScoped;
import org.hibernate.Session;

/**
 *
 * @author Caio
 */
@ManagedBean
@SessionScoped
public class VendaMB implements Serializable{
    private Venda venda = new Venda();
    private List<Venda> vendas = new ArrayList<Venda>();
    private Dao<Venda> vendaDAO;
    private boolean inserir;
    private String pesquisaProduto;
    private Produto produto=new Produto();
    private List<Produto> produtos = new ArrayList<Produto>();
    private Dao<Produto> produtoDAO;
    private List<ProdutoVenda> listaProdutoVenda = new ArrayList<ProdutoVenda>();
    private Dao<ProdutoVenda> produtoVendaDAO;
    

    public VendaMB()  throws PersistenceException {
        try {
            venda = new Venda();
            Session session = HibernateUtil.getSessionFactory().openSession();
            vendaDAO = new GenericDAO<>(Venda.class, session);
            vendas = vendaDAO.buscarTodos();

            produtoDAO = new GenericDAO<>(Produto.class, session);
            produtos = produtoDAO.buscarTodos();
            
            produtoVendaDAO = new GenericDAO<>(ProdutoVenda.class, session);
            listaProdutoVenda = produtoVendaDAO.buscarTodos();
            inserir = true;
            session.close();
            limparTela();
        } catch (PersistenceException ex) {
            ex.printStackTrace();
        }
    }
    public void localizarProduto(String nomeProduto){
        for(Produto p:getProdutos()){
            if(p.getNome().contains(nomeProduto)){
                getProdutos().add(p);
            }
        }
    }
    
    public void limparTela() {
        setVenda(new Venda());
    }
    
    /**
     * @return the venda
     */
    public Venda getVenda() {
        return venda;
    }

    /**
     * @param venda the venda to set
     */
    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    /**
     * @return the vendas
     */
    public List<Venda> getVendas() {
        return vendas;
    }

    /**
     * @param vendas the vendas to set
     */
    public void setVendas(List<Venda> vendas) {
        this.vendas = vendas;
    }

    /**
     * @return the listaProdutoVenda
     */
    public List<ProdutoVenda> getListaProdutoVenda() {
        return listaProdutoVenda;
    }

    /**
     * @param listaProdutoVenda the listaProdutoVenda to set
     */
    public void setListaProdutoVenda(List<ProdutoVenda> listaProdutoVenda) {
        this.listaProdutoVenda = listaProdutoVenda;
    }

    /**
     * @return the produto
     */
    public Produto getProduto() {
        return produto;
    }

    /**
     * @param produto the produto to set
     */
    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    /**
     * @return the pesquisaProduto
     */
    public String getPesquisaProduto() {
        return pesquisaProduto;
    }

    /**
     * @param pesquisaProduto the pesquisaProduto to set
     */
    public void setPesquisaProduto(String pesquisaProduto) {
        this.pesquisaProduto = pesquisaProduto;
    }

    /**
     * @return the produtos
     */
    public List<Produto> getProdutos() {
        return produtos;
    }

    /**
     * @param produtos the produtos to set
     */
    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    /**
     * @return the listaProdutoVendaFiltro
     */
    
    
    
}
