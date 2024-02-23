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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.io.Serializable;


@Entity
@Table(name="mensalidade")
public class Mensalidade implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq-mensalidade")
    @SequenceGenerator(name = "seq-mensalidade", 
    sequenceName = "MENSALIDADE_SEQ", allocationSize = 1, initialValue = 1)
    private int id;
    @OneToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id", nullable = false)
    private Usuario usuario;
    @Column(name="mes", nullable = false, length = 100)
    private String mes;
    @Column(name="ano", nullable = false)
    private int ano;
    @Column(name = "valor")
    private double valor;

    public Mensalidade() {
        this.id = 0;
        this.usuario = new Usuario();
        this.mes = "";
        this.ano = 2024;
        this.valor = 0.0;
    }

    public Mensalidade(int id, Usuario usuario, String mes, int ano, double valor) {
        this.id = id;
        this.usuario = usuario;
        this.mes = mes;
        this.ano = ano;
        this.valor = valor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
    
}
