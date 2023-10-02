package br.edu.ifpr.irati.ads.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.SequenceGenerator;

@Entity(name = "curso")
public class Curso {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq-curso")
    @SequenceGenerator(name = "seq-curso", 
            sequenceName = "CURSO_SEQ", allocationSize = 1, initialValue = 1)
    private int id;
    
    @Column(name = "nome", nullable = true, length = 80)
    private String nome;
    
    @Column(name = "turno", nullable = true, length = 40)
    private String turno;

    public Curso() {
        id = 0;
        nome = "";
        turno = "";
    }

    public Curso(int id, String nome, String turno) {
        this.id = id;
        this.nome = nome;
        this.turno = turno;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }
    
}
