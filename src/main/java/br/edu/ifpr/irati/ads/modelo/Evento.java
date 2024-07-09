package br.edu.ifpr.irati.ads.modelo;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "evento")
public class Evento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq-evento")
    @SequenceGenerator(name = "seq-evento",
            sequenceName = "EVENTO_SEQ", allocationSize = 1, initialValue = 1)
    private int id;
    @Temporal(value = TemporalType.DATE)
    private Date data;
    @Column(name = "descricao", nullable = false, length = 100)
    private String descricao;
    @Column(name = "quantidadeFrequentantes", nullable = false)
    private int quantidadeFrequentantes;
    @Column(name = "quantidadeVisitantes", nullable = false)
    private int quantidadeVisitantes;

    @ManyToMany
    @JoinTable(
            name = "UsuarioEvento",
            joinColumns = @JoinColumn(name = "evento_id"),
            inverseJoinColumns = @JoinColumn(name = "usuario_id")
    )
    private List<Usuario> usuarios;
    @Column(name = "totalFrequentes", nullable = false)
    private int totalFrequentes;

    @ManyToMany
    @JoinTable(
            name = "ContribuicaoEvento",
            joinColumns = @JoinColumn(name = "evento_id"),
            inverseJoinColumns = @JoinColumn(name = "contribuicao_id")
    )
    private List<Contribuicao> contribuicoes;
    @Column(name = "totalContribuicao", nullable = false)
    private double totalContribuicoes;
    
    public Evento() {
        this.id = 0;
        this.data = new Date();
        this.descricao = "";
        this.quantidadeFrequentantes = 0;
        this.quantidadeVisitantes = 0;
        this.usuarios = new ArrayList<>();
        this.contribuicoes = new ArrayList<>();

    }

    public Evento(int id, Date data, String descricao, int quantidadeFrequentantes, int quantidadeVisitantes, List<Usuario> usuarios, List<Contribuicao> contribuicoes) {
        this.id = id;
        this.data = data;
        this.descricao = descricao;
        this.quantidadeFrequentantes = quantidadeFrequentantes;
        this.quantidadeVisitantes = quantidadeVisitantes;
        this.usuarios = usuarios;
        this.contribuicoes = contribuicoes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getQuantidadeFrequentantes() {
        return quantidadeFrequentantes;
    }

    public void setQuantidadeFrequentantes(int quantidadeFrequentantes) {
        this.quantidadeFrequentantes = quantidadeFrequentantes;
    }

    public int getQuantidadeVisitantes() {
        return quantidadeVisitantes;
    }

    public void setQuantidadeVisitantes(int quantidadeVisitantes) {
        this.quantidadeVisitantes = quantidadeVisitantes;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public List<Contribuicao> getContribuicoes() {
        return contribuicoes;
    }

    public void setContribuicoes(List<Contribuicao> contribuicoes) {
        this.contribuicoes = contribuicoes;
    }

    public int getTotalFrequentes() {
        return totalFrequentes;
    }

    public void setTotalFrequentes(int totalFrequentes) {
        this.totalFrequentes = totalFrequentes;
    }

    public double getTotalContribuicoes() {
        return totalContribuicoes;
    }

    public void setTotalContribuicoes(double totalContribuicoes) {
        this.totalContribuicoes = totalContribuicoes;
    }
}
