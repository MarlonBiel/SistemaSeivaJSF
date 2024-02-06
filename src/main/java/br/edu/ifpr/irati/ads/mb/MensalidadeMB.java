/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifpr.irati.ads.mb;

import br.edu.ifpr.irati.ads.dao.Dao;
import br.edu.ifpr.irati.ads.modelo.Mensalidade;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Caio
 */
@ManagedBean
@ViewScoped
public class MensalidadeMB implements Serializable{
    private Mensalidade mensalidade = new Mensalidade();
    private List<Mensalidade> mensalidades;
    private Dao<Mensalidade> mensalidadeDAO;
    private boolean inserir;

    public String botaoAlterar(Mensalidade mensalidade) {
        System.out.println(mensalidade.getId());
        this.mensalidade = new Mensalidade(mensalidade.getId(), mensalidade.getUsuario(), mensalidade.getMes(), mensalidade.getAno(), mensalidade.getValor());
        inserir = false;
        return "-";
    }
    /**
     * @return the mensalidade
     */
    public Mensalidade getMensalidade() {
        return mensalidade;
    }

    /**
     * @param mensalidade the mensalidade to set
     */
    public void setMensalidade(Mensalidade mensalidade) {
        this.mensalidade = mensalidade;
    }

    /**
     * @return the mensalidades
     */
    public List<Mensalidade> getMensalidades() {
        return mensalidades;
    }

    /**
     * @param mensalidades the mensalidades to set
     */
    public void setMensalidades(List<Mensalidade> mensalidades) {
        this.mensalidades = mensalidades;
    }
    
    
    
}
