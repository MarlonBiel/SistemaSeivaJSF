
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
import java.io.Serializable;

@Entity
@Table(name="produtovenda")
public class ProdutoVenda implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq-vendaProduto")
    @SequenceGenerator(name = "seq-vendaProduto", 
            sequenceName = "VENDAPRODUTO_SEQ", allocationSize = 1, initialValue = 1)
    private int id;
    
    @OneToOne
    @JoinColumn(name = "produto", referencedColumnName = "id")
    private Produto produto;
    @Column(name = "quantVenda")
    private int quantVenda;

    public ProdutoVenda() {
        this.id = 0;
        this.produto = new Produto();
        this.quantVenda = 0;
    }

    public ProdutoVenda(int id, Produto produto, int quantVenda) {
        this.id = id;
        this.produto = produto;
        this.quantVenda = quantVenda;
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
    
    
}
