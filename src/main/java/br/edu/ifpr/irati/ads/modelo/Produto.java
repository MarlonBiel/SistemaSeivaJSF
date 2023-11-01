/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifpr.irati.ads.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import java.io.Serializable;

/**
 *
 * @author Caio
 */
@Entity (name="produto")
public class Produto implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq-produto")
    @SequenceGenerator(name = "seq-produto", 
            sequenceName = "PRODUTO_SEQ", allocationSize = 1, initialValue = 1)
    private int id;
    @Column(name="nome", nullable = false, length = 100)
    private String nome;
    @Column(name = "quantEstoque")
    private int quantEstoque;
    @Column(name = "valor")
    private double valor;
    @Column(name = "ian")
    private int ian;
    @Column(name = "valorVenda")
    private double valorVenda;

    public Produto() {
        this.id = 0;
        this.nome = "";
        this.quantEstoque = 0;
        this.valor = 0;
        this.ian = 0;
        this.valorVenda = 0;
    }

    public Produto(int id, String nome, int quantEstoque, double valor, int ian, double valorVenda) {
        this.id = id;
        this.nome = nome;
        this.quantEstoque = quantEstoque;
        this.valor = valor;
        this.ian = ian;
        this.valorVenda = valorVenda;
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
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the quantEstoque
     */
    public int getQuantEstoque() {
        return quantEstoque;
    }

    /**
     * @param quantEstoque the quantEstoque to set
     */
    public void setQuantEstoque(int quantEstoque) {
        this.quantEstoque = quantEstoque;
    }

    /**
     * @return the valor
     */
    public double getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(double valor) {
        this.valor = valor;
    }

    /**
     * @return the ian
     */
    public int getIan() {
        return ian;
    }

    /**
     * @param ian the ian to set
     */
    public void setIan(int ian) {
        this.ian = ian;
    }

    /**
     * @return the valorVenda
     */
    public double getValorVenda() {
        return valorVenda;
    }

    /**
     * @param valorVenda the valorVenda to set
     */
    public void setValorVenda(double valorVenda) {
        this.valorVenda = valorVenda;
    }
    
    
}
