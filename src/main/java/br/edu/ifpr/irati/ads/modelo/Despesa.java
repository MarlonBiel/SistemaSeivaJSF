
package br.edu.ifpr.irati.ads.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;


@Entity (name="despesa")
public class Despesa implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq-despesa")
    @SequenceGenerator(name = "seq-despesa", 
            sequenceName = "DESPESA_SEQ", allocationSize = 1, initialValue = 1)
    private int id;
    @Column(name="descriminacao", nullable = false, length = 100)
    private String descriminacao;
    @Temporal(value = TemporalType.DATE)
    private Date data;
    @Column(name = "valor")
    private double valor;
    @Column(name="formaPagamento", nullable = false, length = 100)
    private String formaPagamento;
    @Column(name="observacao", nullable = false, length = 100)
    private String observacao;
    @Column(name = "anexos", nullable = true)
    private String anexos; //se for arquivo de pdf garnde tem que ser byte

    public Despesa() {
        this.id = 0;
        this.descriminacao = "";
        this.data = new Date();
        this.valor = 0;
        this.formaPagamento = "";
        this.observacao = "";
        this.anexos = "";
    }

    public Despesa(int id, String descriminacao, Date data, double valor, String formaPagamento, String observacao, String Anexos) {
        this.id = id;
        this.descriminacao = descriminacao;
        this.data = data;
        this.valor = valor;
        this.formaPagamento = formaPagamento;
        this.observacao = observacao;
        this.anexos = Anexos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescriminacao() {
        return descriminacao;
    }

    public void setDescriminacao(String descriminacao) {
        this.descriminacao = descriminacao;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getAnexos() {
        return anexos;
    }


    public void setAnexos(String Anexos) {
        this.anexos = Anexos;
    }

    @Override
    public String toString() {
        return getAnexos();
    }
    
    
}
