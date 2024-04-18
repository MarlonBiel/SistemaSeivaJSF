
package br.edu.ifpr.irati.ads.modelo;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Table(name="evento")
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
    @Column(name="quantidadeVisitantes", nullable = false)
    private int quantidadeVisitantes;
    @OneToMany(cascade = CascadeType.ALL) //onetomany
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private List<Usuario> usuarios;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "freqTrabalhadores_id", referencedColumnName = "id")
    private List<Usuario> freqTrabalhadores;

    public Evento() {
        super();
        this.id = 0;
        this.data = new Date();
        this.descricao = "";
        this.quantidadeFrequentantes = 0;
        this.usuarios = new ArrayList<>();
        this.freqTrabalhadores = new ArrayList<>();
    }

    public Evento(int id, Date data, String descricao, int quantidadeFrequentantes, List<Usuario> usuarios, List<Usuario> freqTrabalhadores) {
        this.id = id;
        this.data = data;
        this.descricao = descricao;
        this.quantidadeFrequentantes = quantidadeFrequentantes;
        this.usuarios = usuarios;
        this.freqTrabalhadores = freqTrabalhadores;
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

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public List<Usuario> getFreqTrabalhadores() {
        return freqTrabalhadores;
    }

    public void setFreqTrabalhadores(List<Usuario> freqTrabalhadores) {
        this.freqTrabalhadores = freqTrabalhadores;
    }

    public int getQuantidadeVisitantes() {
        return quantidadeVisitantes;
    }

    public void setQuantidadeVisitantes(int quantidadeVisitantes) {
        this.quantidadeVisitantes = quantidadeVisitantes;
    }
    
    
}
