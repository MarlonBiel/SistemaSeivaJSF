package br.edu.ifpr.irati.ads.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq-usuario")
    @SequenceGenerator(name = "seq-usuario",
            sequenceName = "USUARIO_SEQ", allocationSize = 1, initialValue = 1)
    private int id;
    @Column(name = "nome", nullable = false, length = 100)
    private String nome;
    @Column(name = "cpf", nullable = false, length = 100)
    private String cpf;
    @Temporal(value = TemporalType.DATE)
    private Date dataNascimento;
    @Column(name = "endereco", nullable = false, length = 100)
    private String endereco;
    @Column(name = "telefone", nullable = false, length = 100)
    private String telefone;
    @Column(name = "email", nullable = false, length = 100)
    private String email;
    @Column(name = "senha", nullable = false, length = 100)
    private String senha;
    @Column(name = "matricula")
    private int matricula;
    @ManyToOne
    private Funcao funcao;

    public Usuario() {
        this.id = 0;
        this.nome = "";
        this.cpf = "";
        this.dataNascimento = new Date();
        this.endereco = "";
        this.telefone = "";
        this.email = "";
        this.senha = "";
        this.matricula = 0;
        this.funcao = new Funcao();
    }

    public Usuario(int id, String nome, String cpf, Date dataNascimento, String endereco, String telefone, String email, String senha, int matricula, Funcao funcao) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
        this.senha = senha;
        this.matricula = matricula;
        this.funcao = funcao;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public Funcao getFuncao() {
        return funcao;
    }

    public void setFuncao(Funcao funcao) {
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
        final Usuario other = (Usuario) obj;
        return this.id == other.id;
    }

}
