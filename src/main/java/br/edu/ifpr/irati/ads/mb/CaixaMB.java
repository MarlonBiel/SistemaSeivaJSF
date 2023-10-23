/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifpr.irati.ads.mb;

import br.edu.ifpr.irati.ads.dao.Dao;
import br.edu.ifpr.irati.ads.modelo.Caixa;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import org.hibernate.Session;

/**
 *
 * @author Caio
 */
@ManagedBean
public class CaixaMB implements Serializable{
    private List<Caixa> caixas;
    private Caixa caixa;
    private Session session;
    private Dao<Caixa> caixaDAO;
}
