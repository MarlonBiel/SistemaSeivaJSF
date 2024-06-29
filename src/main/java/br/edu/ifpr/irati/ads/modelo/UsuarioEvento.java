
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
public class UsuarioEvento implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq-usuarioEvento")
    @SequenceGenerator(name = "seq-usuarioEvento", sequenceName = "USUARIOEVENTO_SEQ", allocationSize = 1, initialValue = 1)
    private int id;
    
    @ManyToOne
    @JoinColumn(name = "usuario", referencedColumnName = "id")
    private Usuario Usuario;
    @ManyToOne
    @JoinColumn(name = "evento", referencedColumnName = "id")
    private Evento evento;

    public UsuarioEvento() {
        this.id = 0;
        this.Usuario = new Usuario();
        this.evento = new Evento();
    }

    public UsuarioEvento(int id, Usuario Usuario, Evento evento) {
        this.id = id;
        this.Usuario = Usuario;
        this.evento = evento;
    }
    
    

    public Usuario getUsuario() {
        return Usuario;
    }

    public void setUsuario(Usuario Usuario) {
        this.Usuario = Usuario;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }
    
    
    
}
