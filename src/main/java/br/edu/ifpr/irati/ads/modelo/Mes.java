
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
@Table(name="mes")
public class Mes implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq-mes")
    @SequenceGenerator(name = "seq-mes", 
    sequenceName = "MES_SEQ", allocationSize = 1, initialValue = 1)
    private int id;
    @Column(name="mes", nullable = false, length = 100)
    private String mes;

    public Mes(int id,String mes) {
        this.mes = mes;
        this.id=id;
    }

    public Mes() {
        this.mes = "";
        this.id=0;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        final Mes other = (Mes) obj;
        return this.id == other.id;
    }
     
}
