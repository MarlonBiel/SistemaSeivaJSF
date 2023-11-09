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
import br.edu.ifpr.irati.ads.modelo.Produto;
import br.edu.ifpr.irati.ads.modelo.Usuario;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Caio
 */
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
        Dao<Usuario> usuarioDAO = new GenericDAO<>(Usuario.class, session);
        Usuario a1 = new Usuario(0, "caio", 0, null, "rua", "123", "caio", "123", 0, Funcao.VAZIO);
        Usuario a2 = new Usuario(1, "caio", 0, null, "rua", "123", "caio", "123", 0, Funcao.VAZIO);
        Usuario a3 = new Usuario(2, "caio", 0, null, "rua", "123", "caio", "123", 0, Funcao.VAZIO);
        
        usuarioDAO.salvar(a1);
        usuarioDAO.salvar(a2);
        usuarioDAO.salvar(a3);
        
        List<Usuario> usuarios =new ArrayList<>();
        usuarios.add(a1);
        usuarios.add(a2);
        usuarios.add(a3);
        
        
        Dao<Despesa> despesaDAO = new GenericDAO<>(Despesa.class, session);
        Despesa d1 = new Despesa(0, "teste", null, 0, "", "", "");
        despesaDAO.salvar(d1);
        
        Dao<Produto> produtoDAO = new GenericDAO<>(Produto.class, session);
        Produto p1 = new Produto(0, "", 0, 0, 0, 0);
        produtoDAO.salvar(p1);
        
        Dao<Contribuicao> contribuicaoDAO = new GenericDAO<>(Contribuicao.class, session);
        Contribuicao c1 = new Contribuicao(0, a1, 0, null, "", "");
        contribuicaoDAO.salvar(c1);
        
        //Dao<Evento> eventoDAO = new GenericDAO<>(Evento.class, session);
        //Evento e1 = new Evento(0, null, "", 0, usuarios, a1);
        //eventoDAO.salvar(e1);
    }
}
