/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifpr.irati.ads.util;

import br.edu.ifpr.irati.ads.dao.Dao;
import br.edu.ifpr.irati.ads.dao.GenericDAO;
import br.edu.ifpr.irati.ads.exception.PersistenceException;
import br.edu.ifpr.irati.ads.modelo.Contribuicao;
import br.edu.ifpr.irati.ads.modelo.Despesa;
import br.edu.ifpr.irati.ads.modelo.Evento;
import br.edu.ifpr.irati.ads.modelo.Funcao;
import br.edu.ifpr.irati.ads.modelo.Mensalidade;
import br.edu.ifpr.irati.ads.modelo.Mes;
import br.edu.ifpr.irati.ads.modelo.Produto;
import br.edu.ifpr.irati.ads.modelo.ProdutoVenda;
import br.edu.ifpr.irati.ads.modelo.Usuario;
import br.edu.ifpr.irati.ads.modelo.Venda;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;


public class TestesBD {
    public static void main(String[] args) throws PersistenceException {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            criar(session);
            session.close();
        } catch (PersistenceException ex){
            ex.printStackTrace();
        }
    }
    public static void criar(Session session) throws PersistenceException {
        Dao<Funcao> funcaoDAO = new GenericDAO<>(Funcao.class, session);
        Funcao f1= new Funcao(0, "Financeiro");
        Funcao f2= new Funcao(0, "Organizador");
        Funcao f3= new Funcao(0, "Cordenador");
        Funcao f4= new Funcao(0, "Vazio");
        
        funcaoDAO.salvar(f1);
        funcaoDAO.salvar(f2);
        funcaoDAO.salvar(f3);
        funcaoDAO.salvar(f4);
        
        Dao<Usuario> usuarioDAO = new GenericDAO<>(Usuario.class, session);
        Usuario a1 = new Usuario(0, "Admin", "000.000.000-00", null, "", "(00)00000-0000", "admin@seiva.com", "123", 0, f4);
        usuarioDAO.salvar(a1);
        
        Dao<Produto> produtoDAO = new GenericDAO<>(Produto.class, session);
        Produto p1 = new Produto(0, "Livro1", 101, 51, 11, 51);
        Produto p2 = new Produto(0, "Livro2", 102, 52, 12, 52);
        Produto p3 = new Produto(0, "Livro3", 103, 53, 13, 53);
        produtoDAO.salvar(p1);
        produtoDAO.salvar(p2);
        produtoDAO.salvar(p3);
        
        Dao<Mes> mesDAO = new GenericDAO<>(Mes.class, session);
        Mes m1 = new Mes(0,"JANEIRO");
        Mes m2 = new Mes(0,"FEVEREIRO");
        Mes m3 = new Mes(0,"MARCO");
        Mes m4 = new Mes(0,"ABRIL");
        Mes m5 = new Mes(0,"MAIO");
        Mes m6 = new Mes(0,"JUNHO");
        Mes m7 = new Mes(0,"JULHO");
        Mes m8 = new Mes(0,"AGOSTO");
        Mes m9 = new Mes(0,"SETEMBRO");
        Mes m10 = new Mes(0,"OUTUBRO");
        Mes m11 = new Mes(0,"NOVEMBRO");
        Mes m12 = new Mes(0,"DEZEMBRO");
        
        mesDAO.salvar(m1);
        mesDAO.salvar(m2);
        mesDAO.salvar(m3);
        mesDAO.salvar(m4);
        mesDAO.salvar(m5);
        mesDAO.salvar(m6);
        mesDAO.salvar(m7);
        mesDAO.salvar(m8);
        mesDAO.salvar(m9);
        mesDAO.salvar(m10);
        mesDAO.salvar(m11);
        mesDAO.salvar(m12);
    }
}
