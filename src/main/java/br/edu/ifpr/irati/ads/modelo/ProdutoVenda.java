/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifpr.irati.ads.modelo;

/**
 *
 * @author Caio
 */
public class ProdutoVenda {
    private Produto produto;
    private int quantVenda;

    public ProdutoVenda() {
        this.produto = new Produto();
        this.quantVenda = 0;
    }

    public ProdutoVenda(Produto produto, int quantVenda) {
        this.produto = produto;
        this.quantVenda = quantVenda;
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
     * @return the quantVenda
     */
    public int getQuantVenda() {
        return quantVenda;
    }

    /**
     * @param quantVenda the quantVenda to set
     */
    public void setQuantVenda(int quantVenda) {
        this.quantVenda = quantVenda;
    }
    
    
}
