
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
    
    @OneToMany
    @JoinColumn(name = "Usuario_id", referencedColumnName = "id")
    private List<UsuarioEvento> usuariosEvento;
    
    @OneToMany
    @JoinColumn(name = "Contribuicao_id", referencedColumnName = "id")
    private List<ContribuicaoEvento> contribuicoesEvento;

    public Evento() {
        super();
        this.id = 0;
        this.data = new Date();
        this.descricao = "";
        this.quantidadeFrequentantes = 0;
        this.usuariosEvento = new ArrayList<>();
        this.contribuicoesEvento = new ArrayList<>();
    }

    public Evento(int id, Date data, String descricao, int quantidadeFrequentantes, List<Usuario> usuarios, List<UsuarioEvento> usuariosEvento, List<ContribuicaoEvento> contribuicoesEvento) {
        this.id = id;
        this.data = data;
        this.descricao = descricao;
        this.quantidadeFrequentantes = quantidadeFrequentantes;
        this.usuariosEvento = usuariosEvento;
        this.contribuicoesEvento = contribuicoesEvento;
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

    public List<UsuarioEvento> getUsuariosEvento() {
        return usuariosEvento;
    }

    public void setUsuariosEvento(List<UsuarioEvento> usuariosEvento) {
        this.usuariosEvento = usuariosEvento;
    }

    public List<ContribuicaoEvento> getContribuicoesEvento() {
        return contribuicoesEvento;
    }

    public void setContribuicoesEvento(List<ContribuicaoEvento> contribuicoesEvento) {
        this.contribuicoesEvento = contribuicoesEvento;
    }
    
    
}
