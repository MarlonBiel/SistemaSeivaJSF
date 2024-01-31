
package br.edu.ifpr.irati.ads.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="funcao")
public class Funcao implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq-funcoes")
    @SequenceGenerator(name = "seq-funcoes", 
            sequenceName = "FUNCOES_SEQ", allocationSize = 1, initialValue = 1)
    private int id;
    
    @Column(name = "funcao")
    private String funcao;

    public Funcao() {
        this.id = 0;
        this.funcao = "";
    }

    public Funcao(int id, String funcao) {
        this.id = id;
        this.funcao = funcao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Funcao other = (Funcao) obj;
        return this.id == other.id;
    }

   
    
    
}
