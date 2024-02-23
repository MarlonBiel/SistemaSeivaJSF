/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifpr.irati.ads.util;

import br.edu.ifpr.irati.ads.dao.Dao;
import br.edu.ifpr.irati.ads.dao.GenericDAO;
import br.edu.ifpr.irati.ads.exception.PersistenceException;
import br.edu.ifpr.irati.ads.modelo.FormaPgto;
import br.edu.ifpr.irati.ads.modelo.Funcao;
import br.edu.ifpr.irati.ads.modelo.Usuario;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;

public class PadraoBD {

    public static void main(String[] args) throws PersistenceException {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            criar(session);
            session.close();
        } catch (PersistenceException ex) {
            ex.printStackTrace();
        }
    }

    public static void criar(Session session) throws PersistenceException {
        
        Dao<Funcao> funcaoDAO = new GenericDAO<>(Funcao.class, session);
        Funcao f1 = new Funcao(0, "Administrador");
        Funcao f2 = new Funcao(0, "Secretário");
        Funcao f3 = new Funcao(0, "Acolhimento");
        Funcao f4 = new Funcao(0, "Organizador do salão");
        Funcao f5 = new Funcao(0, "Organizador de fichas");

        funcaoDAO.salvar(f1);
        funcaoDAO.salvar(f2);
        funcaoDAO.salvar(f3);
        funcaoDAO.salvar(f4);
        funcaoDAO.salvar(f5);

        List<Funcao> funcoes = new ArrayList<>();
        funcoes.add(f1);
        funcoes.add(f2);
        funcoes.add(f3);
        funcoes.add(f4);
        funcoes.add(f5);
        
        
        
        Dao<FormaPgto> formaPgtoDAO = new GenericDAO<>(FormaPgto.class, session);
        FormaPgto p1 = new FormaPgto(0, "Dinheiro");
        FormaPgto p2 = new FormaPgto(0, "PIX");
        FormaPgto p3 = new FormaPgto(0, "Cartão de debito");
        FormaPgto p4 = new FormaPgto(0, "Cartão de crédito");

        formaPgtoDAO.salvar(p1);
        formaPgtoDAO.salvar(p2);
        formaPgtoDAO.salvar(p3);
        formaPgtoDAO.salvar(p4);

        List<FormaPgto> formasPgtos = new ArrayList<>();
        formasPgtos.add(p1);
        formasPgtos.add(p2);
        formasPgtos.add(p3);
        formasPgtos.add(p4);
        
        
        Dao<Usuario> usuarioDAO = new GenericDAO<>(Usuario.class, session);
        Usuario a1 = new Usuario(0, "Marlon Ruan Da Costa Biel", "115.404.899-30", new Date(2002,05,15), "Rua Benedito de Morais, 885", "(42) 98435-8621", "marlonruan43@gmail.com", "Cme101513", 0, f1);
        
        usuarioDAO.salvar(a1);

        List<Usuario> usuarios =new ArrayList<>();
        usuarios.add(a1);

    }
}
