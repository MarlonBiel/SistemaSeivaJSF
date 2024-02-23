
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
@Table(name="produtovenda")
public class ProdutoVenda implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq-vendaProduto")
    @SequenceGenerator(name = "seq-vendaProduto", sequenceName = "VENDAPRODUTO_SEQ", allocationSize = 1, initialValue = 1)
    private int id;
    
    @OneToOne
    @JoinColumn(name = "produto", referencedColumnName = "id")
    private Produto produto;
    @Column(name = "quantVenda")
    private int quantVenda;
    @ManyToOne
    private Venda venda;
    @Column(name = "valorTotalProduto")
    private double valorTotalProduto;

    public ProdutoVenda() {
        this.id = 0;
        this.produto = new Produto();
        this.quantVenda = 0;
        this.valorTotalProduto = 0;
    }

    public ProdutoVenda(int id, Produto produto, int quantVenda, double valorTotalProduto) {
        this.id = id;
        this.produto = produto;
        this.quantVenda = quantVenda;
        this.valorTotalProduto = valorTotalProduto;
    }


    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantVenda() {
        return quantVenda;
    }

    public void setQuantVenda(int quantVenda) {
        this.quantVenda = quantVenda;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public double getValorTotalProduto() {
        return valorTotalProduto;
    }

    public void setValorTotalProduto(double valorTotalProduto) {
        this.valorTotalProduto = valorTotalProduto;
    }
    
    
}
