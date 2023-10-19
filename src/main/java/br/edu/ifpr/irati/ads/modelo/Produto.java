package br.edu.ifpr.irati.ads.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

@Entity(name = "produto")
public class Produto implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq-curso")
    @SequenceGenerator(name = "seq-curso", 
            sequenceName = "CURSO_SEQ", allocationSize = 1, initialValue = 1)
    private int id;
    
    @Column(name = "produto", nullable = true, length = 80)
    private String produto;
    
    @Temporal(value = TemporalType.DATE)
    private Date data;
    
    @Column(name = "descricao", nullable = true, length = 100)
    private String descricao;
    
    @Column(name = "valor", nullable = true, length = 9)
    private String valor;
   

    public Produto() {
        id = 0;
        produto = "";
        data = new Date();
        descricao = "";
        valor = "";
        
    }

    public Produto(int id, String produto, Date data, String descricao, String valor) {
        this.id = id;
        this.produto = produto;
        this.data = data;
        this.descricao = descricao;
        this.valor = valor;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
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

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
    
}
