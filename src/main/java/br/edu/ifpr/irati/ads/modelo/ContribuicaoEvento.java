
package br.edu.ifpr.irati.ads.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="contribuicaoevento")
public class ContribuicaoEvento implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq-contribuicaoEvento")
    @SequenceGenerator(name = "seq-contribuicaoEvento", sequenceName = "CONTRIBUICAOEVENTO_SEQ", allocationSize = 1, initialValue = 1)
    private int id;
    
    @ManyToOne
    @JoinColumn(name = "contribuicao", referencedColumnName = "id")
    private Contribuicao contribuicao;
    @ManyToOne
    @JoinColumn(name = "evento", referencedColumnName = "id")
    private Evento evento;

    public ContribuicaoEvento() {
        this.id = 0;
        this.evento = new Evento();
        this.contribuicao = new Contribuicao();
    }

    public ContribuicaoEvento(int id, Contribuicao contribuicao, Evento evento) {
        this.id = id;
        this.evento = evento;
        this.contribuicao = contribuicao;
    }

    public Contribuicao getContribuicao() {
        return contribuicao;
    }

    public void setContribuicao(Contribuicao contribuicao) {
        this.contribuicao = contribuicao;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }
    
    
}
