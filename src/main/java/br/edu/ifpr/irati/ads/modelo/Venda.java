/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifpr.irati.ads.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Caio
 */
@Entity (name="venda")
public class Venda implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq-venda")
    @SequenceGenerator(name = "seq-venda", 
            sequenceName = "VENDA_SEQ", allocationSize = 1, initialValue = 1)
    private int id;
    
    private ProdutoVenda vendaProduto;
    private List<ProdutoVenda> vendaAtual;

    public Venda() {
        this.id = 0;
        this.vendaProduto = new ProdutoVenda();
        this.vendaAtual = new ArrayList<>();
    }

    public Venda(int id, ProdutoVenda vendaProduto, List<ProdutoVenda> vendaAtual) {
        this.id = id;
        this.vendaProduto = vendaProduto;
        this.vendaAtual = vendaAtual;
    }

    
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the vendaProduto
     */
    public ProdutoVenda getVendaProduto() {
        return vendaProduto;
    }

    /**
     * @param vendaProduto the vendaProduto to set
     */
    public void setVendaProduto(ProdutoVenda vendaProduto) {
        this.vendaProduto = vendaProduto;
    }

    /**
     * @return the vendaAtual
     */
    public List<ProdutoVenda> getVendaAtual() {
        return vendaAtual;
    }

    /**
     * @param vendaAtual the vendaAtual to set
     */
    public void setVendaAtual(List<ProdutoVenda> vendaAtual) {
        this.vendaAtual = vendaAtual;
    }
    
}
