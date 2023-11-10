
package br.edu.ifpr.irati.ads.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity (name="venda")
public class Venda implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq-venda")
    @SequenceGenerator(name = "seq-venda", 
            sequenceName = "VENDA_SEQ", allocationSize = 1, initialValue = 1)
    private int id;
    
    @OneToMany
    @JoinColumn(name = "Venda_id", referencedColumnName = "id")
    private List<ProdutoVenda> vendaAtual;

    public Venda() {
        this.id = 0;
        this.vendaAtual = new ArrayList<>();
    }

    public Venda(int id, List<ProdutoVenda> vendaAtual) {
        this.id = id;
        this.vendaAtual = vendaAtual;
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
    
}
