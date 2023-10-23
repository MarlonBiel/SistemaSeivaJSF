package br.edu.ifpr.irati.ads.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.SequenceGenerator;
import java.util.Date;

@Entity(name = "caixa")
public class Caixa {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq-caixa")
    @SequenceGenerator(name = "seq-caixa", 
            sequenceName = "CAIXA_SEQ", allocationSize = 1, initialValue = 1)
    private int id;
    
    @Column(name = "nome", nullable = true, length = 80)
    private String nome;
    
    @Column(name = "data", nullable = true, length = 40)
    private Date data;
    
    @Column(name = "descricao", nullable = true, length = 100)
    private String descricao;
    
    @Column(name = "tipo", nullable = true, length = 9)
    private String tipo;
    
    @Column(name = "formaPagamento", nullable = true, length = 14)
    private String formaPagamento;

    public Caixa() {
        id = 0;
        nome = "";
        data = new Date();
        descricao = "";
        tipo = "";
        formaPagamento = "";
        
    }

    public Caixa(int id, String nome, Date data, String descricao, String tipo, String formaPagamento) {
        this.id = id;
        this.nome = nome;
        this.data = data;
        this.descricao = descricao;
        this.tipo = tipo;
        this.formaPagamento = formaPagamento;
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

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descrição) {
        this.descricao = descrição;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }
    
    
}
