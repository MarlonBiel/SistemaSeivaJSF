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
        Dao<Usuario> usuarioDAO = new GenericDAO<>(Usuario.class, session);
        Usuario a1 = new Usuario(0, "caio", "", null, "rua", "123", "caio", "123", 0, null);
        Usuario a2 = new Usuario(0, "caio", "", null, "rua", "123", "caio", "123", 0, null);
        Usuario a3 = new Usuario(0, "caio", "", null, "rua", "123", "caio", "123", 0, null);
        
        usuarioDAO.salvar(a1);
        usuarioDAO.salvar(a2);
        usuarioDAO.salvar(a3);
        
        List<Usuario> usuarios =new ArrayList<>();
        usuarios.add(a1);
        usuarios.add(a2);
        usuarios.add(a3);
        
        
        Dao<Despesa> despesaDAO = new GenericDAO<>(Despesa.class, session);
        //IOUtils.toString(fileUploadEvent.getFile().getInputstream(), StandardCharsets.UTF_8); - Converte o arquivo PDF para String
        //Despesa d1 = new Despesa(0, "teste", new Date(), 100, "Boleto", "Compra de livros", "");
        //despesaDAO.salvar(d1);
        
        Dao<Produto> produtoDAO = new GenericDAO<>(Produto.class, session);
        Produto p1 = new Produto(0, "Livro1", 101, 51, 11, 51);
        Produto p2 = new Produto(0, "Livro2", 102, 52, 12, 52);
        Produto p3 = new Produto(0, "Livro3", 103, 53, 13, 53);
        produtoDAO.salvar(p1);
        produtoDAO.salvar(p2);
        produtoDAO.salvar(p3);
        

        
        usuarios=usuarioDAO.buscarTodos();
        Dao<Evento> eventoDAO = new GenericDAO<>(Evento.class, session);
        Evento e1 = new Evento(0, new Date(), "Palestra", 0, usuarios, a1);
        eventoDAO.salvar(e1);
        
        Produto produto1 = produtoDAO.buscarPorId(1);
        Produto produto2 = produtoDAO.buscarPorId(2);
        Produto produto3 = produtoDAO.buscarPorId(3);
        Dao<ProdutoVenda> produtoVendaDAO = new GenericDAO<>(ProdutoVenda.class, session);
        ProdutoVenda pv1 = new ProdutoVenda(0, produto1, 2);
        ProdutoVenda pv2 = new ProdutoVenda(0, produto2, 3);
        ProdutoVenda pv3 = new ProdutoVenda(0, produto3, 4);
        produtoVendaDAO.salvar(pv1);
        produtoVendaDAO.salvar(pv2);
        produtoVendaDAO.salvar(pv3);
        
        List<ProdutoVenda> produtos = produtoVendaDAO.buscarTodos();
        Dao<Venda> vendaDAO = new GenericDAO<>(Venda.class, session);
        Venda v1 = new Venda(0, produtos);
        vendaDAO.salvar(v1);
    }
}
