
package br.edu.ifpr.irati.ads.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name="transacao")
public class Transacao implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq-transacao")
    @SequenceGenerator(name = "seq-transacao", 
            sequenceName = "TRANSACAO_SEQ", allocationSize = 1, initialValue = 1)
    private int id;
    @Temporal(value = TemporalType.DATE)
    private Date data;
    @Column(name="descricao", nullable = false, length = 100)
    private String descricao;
    @Column(name="tipo", nullable = false)
    private char Tipo;
    @Column(name="valor", nullable = false)
    private double valor;

    public Transacao() {
        this.id=0;
        this.data = new Date();
        this.descricao = "";
        this.Tipo = 'C';
        this.valor = 0;
    }

    public Transacao(int id, Date data, String descricao, char Tipo, double valor) {
        this.id = id;
        this.data = data;
        this.descricao = descricao;
        this.Tipo = Tipo;
        this.valor = valor;
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

    public char getTipo() {
        return Tipo;
    }

    public void setTipo(char Tipo) {
        this.Tipo = Tipo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
    
    
    
}
