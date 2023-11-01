/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifpr.irati.ads.modelo;

import java.util.Date;

/**
 *
 * @author Caio
 */
public class Contribuicao {
    private int id;
    private Usuario usuario;
    private double valor;
    private Date data;
    private String formaContribuicao;
    private String tipo;

    public Contribuicao() {
        this.id = 0;
        this.usuario = new Usuario();
        this.valor = 0;
        this.data = new Date();
        this.formaContribuicao = "";
        this.tipo = "";
    }

    public Contribuicao(int id, Usuario usuario, double valor, Date data, String formaContribuicao, String tipo) {
        this.id = id;
        this.usuario = usuario;
        this.valor = valor;
        this.data = data;
        this.formaContribuicao = formaContribuicao;
        this.tipo = tipo;
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
     * @return the usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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
     * @return the formaContribuicao
     */
    public String getFormaContribuicao() {
        return formaContribuicao;
    }

    /**
     * @param formaContribuicao the formaContribuicao to set
     */
    public void setFormaContribuicao(String formaContribuicao) {
        this.formaContribuicao = formaContribuicao;
    }

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
}
