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
@Table(name = "formaDePagamento")
public class FormaPgto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq-formaPagmaneto")
    @SequenceGenerator(name = "seq-formaPagamento",
            sequenceName = "FORMAPAGAMENTO_SEQ", allocationSize = 1, initialValue = 1)
    private int id;

    @Column(name = "formaDePagamento")
    private String formaPgto;

    public FormaPgto() {
        this.id = 0;
        this.formaPgto = "";
    }

    public FormaPgto(int id, String formaPgto) {
        this.id = id;
        this.formaPgto = formaPgto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFormaPgto() {
        return formaPgto;
    }

    public void setFormaPgto(String formaPgto) {
        this.formaPgto = formaPgto;
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
        final FormaPgto other = (FormaPgto) obj;
        return this.getId() == other.getId();
    }

}
