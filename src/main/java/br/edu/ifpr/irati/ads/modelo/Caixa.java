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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq-curso")
    @SequenceGenerator(name = "seq-curso", 
            sequenceName = "CURSO_SEQ", allocationSize = 1, initialValue = 1)
    private int id;
    
    @Column(name = "nome", nullable = true, length = 80)
    private String nome;
    
    @Column(name = "data", nullable = true, length = 40)
    private Date data;
    
    @Column(name = "descrição", nullable = true, length = 100)
    private String descrição;
    
    @Column(name = "tipo", nullable = true, length = 9)
    private String tipo;
    
    @Column(name = "formaPagamento", nullable = true, length = 14)
    private String formaPagamento;

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

    public String getDescrição() {
        return descrição;
    }

    public void setDescrição(String descrição) {
        this.descrição = descrição;
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
