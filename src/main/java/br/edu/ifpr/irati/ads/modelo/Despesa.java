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
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Caio
 */
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
    @Column(name = "imagem", nullable = true, length = 250)
    private String anexos; //alterar

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
     * @return the descriminacao
     */
    public String getDescriminacao() {
        return descriminacao;
    }

    /**
     * @param descriminacao the descriminacao to set
     */
    public void setDescriminacao(String descriminacao) {
        this.descriminacao = descriminacao;
    }

    /**
     * @return the data
     */
    public Date getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(Date data) {
        this.data = data;
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
     * @return the formaPagamento
     */
    public String getFormaPagamento() {
        return formaPagamento;
    }

    /**
     * @param formaPagamento the formaPagamento to set
     */
    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    /**
     * @return the observacao
     */
    public String getObservacao() {
        return observacao;
    }

    /**
     * @param observacao the observacao to set
     */
    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    /**
     * @return the Anexos
     */
    public String getAnexos() {
        return anexos;
    }

    /**
     * @param Anexos the Anexos to set
     */
    public void setAnexos(String Anexos) {
        this.anexos = Anexos;
    }
    
    
}
