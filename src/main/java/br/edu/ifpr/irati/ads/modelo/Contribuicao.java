
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
    @Column(name="formaContribuicao", nullable = false, length = 100)
    private String formaContribuicao;
    @Column(name="tipo", nullable = false, length = 100)
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
