
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
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

@Entity 
@Table(name="contribuicao")
public class Contribuicao implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq-contribuicao")
    @SequenceGenerator(name = "seq-contribuicao", 
            sequenceName = "CONTRIBUICAO_SEQ", allocationSize = 1, initialValue = 1)
    private int id;
    @OneToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id", nullable = false)
    private Usuario usuario;
    @Column(name = "valor")
    private double valor;
    @Temporal(value = TemporalType.DATE)
    private Date data;
    @OneToOne
    private FormaPgto formaContribuicao;
    @Column(name="tipo", nullable = false, length = 100)
    private String tipo;

    public Contribuicao() {
        this.id = 0;
        this.usuario = new Usuario();
        this.valor = 0;
        this.data = new Date();
        this.formaContribuicao = new FormaPgto();
        this.tipo = "";
    }

    public Contribuicao(int id, Usuario usuario, double valor, Date data, FormaPgto formaContribuicao, String tipo) {
        this.id = id;
        this.usuario = usuario;
        this.valor = valor;
        this.data = data;
        this.formaContribuicao = formaContribuicao;
        this.tipo = tipo;
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

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
    
    public FormaPgto getFormaContribuicao() {
        return formaContribuicao;
    }

    public void setFormaContribuicao(FormaPgto formaContribuicao) {
        this.formaContribuicao = formaContribuicao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
}
