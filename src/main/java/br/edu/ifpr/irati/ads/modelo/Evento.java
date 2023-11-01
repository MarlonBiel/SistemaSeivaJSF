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
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Caio
 */
@Entity (name="evento")
public class Evento implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq-evento")
    @SequenceGenerator(name = "seq-evento", 
            sequenceName = "EVENTO_SEQ", allocationSize = 1, initialValue = 1)
    private int id;
    @Temporal(value = TemporalType.DATE)
    private Date data;
    @Column(name="descricao", nullable = false, length = 100)
    private String descricao;
    @Column(name="quantidadeFrequentantes", nullable = false)
    private int quantidadeFrequentantes;
    @OneToMany
    @JoinColumn(name = "usuario_id", referencedColumnName = "id", nullable = false)
    private List<Usuario> usuarios;
    @OneToOne
    @JoinColumn(name = "freqTrabalhadores_id", referencedColumnName = "id", nullable = false)
    private Usuario freqTrabalhadores;

    public Evento() {
        this.id = 0;
        this.data = new Date();
        this.descricao = "";
        this.quantidadeFrequentantes = 0;
        this.usuarios = new ArrayList<>();
        this.freqTrabalhadores = new Usuario();
    }

    public Evento(int id, Date data, String descricao, int quantidadeFrequentantes, List<Usuario> usuarios, Usuario freqTrabalhadores) {
        this.id = id;
        this.data = data;
        this.descricao = descricao;
        this.quantidadeFrequentantes = quantidadeFrequentantes;
        this.usuarios = usuarios;
        this.freqTrabalhadores = freqTrabalhadores;
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
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return the quantidadeFrequentantes
     */
    public int getQuantidadeFrequentantes() {
        return quantidadeFrequentantes;
    }

    /**
     * @param quantidadeFrequentantes the quantidadeFrequentantes to set
     */
    public void setQuantidadeFrequentantes(int quantidadeFrequentantes) {
        this.quantidadeFrequentantes = quantidadeFrequentantes;
    }

    /**
     * @return the usuarios
     */
    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    /**
     * @param usuarios the usuarios to set
     */
    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    /**
     * @return the freqTrabalhadores
     */
    public Usuario getFreqTrabalhadores() {
        return freqTrabalhadores;
    }

    /**
     * @param freqTrabalhadores the freqTrabalhadores to set
     */
    public void setFreqTrabalhadores(Usuario freqTrabalhadores) {
        this.freqTrabalhadores = freqTrabalhadores;
    }
    
    
}
