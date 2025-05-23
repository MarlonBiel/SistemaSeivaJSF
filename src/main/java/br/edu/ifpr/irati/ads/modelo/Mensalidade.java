
package br.edu.ifpr.irati.ads.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
    @ManyToOne
    private Mes mes;
    @Column(name="ano", nullable = false)
    private int ano;
    @Column(name = "valor")
    private double valor;

    public Mensalidade() {
        this.id = 0;
        this.usuario = new Usuario();
        this.mes = new Mes();
        this.ano = 2024;
        this.valor = 0.0;
    }

    public Mensalidade(int id, Usuario usuario, Mes mes, int ano, double valor) {
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

    public Mes getMes() {
        return mes;
    }

    public void setMes(Mes mes) {
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
