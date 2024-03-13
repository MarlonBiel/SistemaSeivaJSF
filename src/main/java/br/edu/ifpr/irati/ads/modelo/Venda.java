
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
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Table(name="venda")
public class Venda implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq-venda")
    @SequenceGenerator(name = "seq-venda", 
            sequenceName = "VENDA_SEQ", allocationSize = 1, initialValue = 1)
    private int id;
    
    @Temporal(value = TemporalType.DATE)
    private Date data;
    
    @Column(name = "descricao", nullable = false)
    private String descricao;
    
    @OneToMany
    @JoinColumn(name = "Venda_id", referencedColumnName = "id")
    private List<ProdutoVenda> vendaAtual;
    @Column(name = "valorTotal", nullable = false)
    private double valorTotal;

    public Venda() {
        this.id = 0;
        this.vendaAtual = new ArrayList<>();
        this.valorTotal = 0;
    }

    public Venda(int id, List<ProdutoVenda> vendaAtual, double valorTotal) {
        this.id = id;
        this.vendaAtual = vendaAtual;
        this.valorTotal = valorTotal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<ProdutoVenda> getVendaAtual() {
        return vendaAtual;
    }

    public void setVendaAtual(List<ProdutoVenda> vendaAtual) {
        this.vendaAtual = vendaAtual;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
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
    
}
