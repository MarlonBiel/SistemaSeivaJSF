
package br.edu.ifpr.irati.ads.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="produto")
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
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }


    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuantEstoque() {
        return quantEstoque;
    }

    public void setQuantEstoque(int quantEstoque) {
        this.quantEstoque = quantEstoque;
    }

    /**
     * @return the valor
     */
    public double getValor() {
        return valor;
    }


    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getIan() {
        return ian;
    }

    public void setIan(int ian) {
        this.ian = ian;
    }

    public double getValorVenda() {
        return valorVenda;
    }


    public void setValorVenda(double valorVenda) {
        this.valorVenda = valorVenda;
    }
    
    
}
