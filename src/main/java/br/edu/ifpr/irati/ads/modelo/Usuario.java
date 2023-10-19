package br.edu.ifpr.irati.ads.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

@Entity(name = "curso")
public class Usuario implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq-curso")
    @SequenceGenerator(name = "seq-curso", 
            sequenceName = "CURSO_SEQ", allocationSize = 1, initialValue = 1)
    private int id;
    
    @Column(name = "nome", nullable = true, length = 80)
    private String nome;
    
    @Temporal(value = TemporalType.DATE)
    private Date dataNascimento;
    
    @Column(name = "endereco", nullable = true, length = 100)
    private String endereco;
    
    @Column(name = "cep", nullable = true, length = 9)
    private String cep;
    
    @Column(name = "telefone", nullable = true, length = 14)
    private String telefone;
    
    @Column(name = "email", nullable = true, length = 40)
    private String email;
    
    @Column(name = "cpf", nullable = true, length = 14)
    private String cpf;
    
    @Column(name = "senha", nullable = true, length = 255)
    private String senha;
    
    @Column(name = "funcao", nullable = true, length = 20)
    private String funcao;

    public Usuario() {
        id = 0;
        nome = "";
        dataNascimento = new Date();
        endereco = "";
        cep = "";
        email = "";
        cpf = "";
        senha = "";
        funcao = "";
    }

    public Usuario(int id, String nome, Date dataNascimento, String endereco, String cep, String email, String cpf, String senha, String funcao) {
        this.id = id;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.endereco = endereco;
        this.cep = cep;
        this.email = email;
        this.cpf = cpf;
        this.senha = senha;
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

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }
    
}
