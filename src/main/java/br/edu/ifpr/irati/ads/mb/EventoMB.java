/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifpr.irati.ads.mb;

import br.edu.ifpr.irati.ads.dao.Dao;
import br.edu.ifpr.irati.ads.dao.GenericDAO;
import br.edu.ifpr.irati.ads.exception.PersistenceException;
import br.edu.ifpr.irati.ads.modelo.Evento;
import br.edu.ifpr.irati.ads.util.HibernateUtil;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.hibernate.Session;

/**
 *
 * @author Caio
 */
@ManagedBean
@SessionScoped
public class EventoMB implements Serializable{
    private Evento evento = new Evento();
    private List<Evento> eventos;
    private Dao<Evento> eventoDAO;
    private boolean inserir;

    public EventoMB() throws PersistenceException{
        try {
            evento = new Evento();
            Session session = HibernateUtil.getSessionFactory().openSession();
            eventoDAO = new GenericDAO<>(Evento.class, session);
            eventos = eventoDAO.buscarTodos();

            inserir = true;
            session.close();
            limparTela();
        } catch (PersistenceException ex) {
            ex.printStackTrace();
        }
    }
    public void limparTela() {
        setEvento(new Evento());
    }
    public void salvar() {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            eventoDAO = new GenericDAO<>(Evento.class, session);

            if (inserir) {
                //executar o método inserir do DAO
                eventoDAO.salvar(evento);
            } else {
                //executar o método alterar do DAO
                eventoDAO.alterar(evento);
            }
            inserir = true;
            evento = new Evento();
            eventos = eventoDAO.buscarTodos();
            session.close();
        } catch (PersistenceException ex) {
            ex.printStackTrace();
        }
    }
    public void botaoExcluir(Evento evento) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            eventoDAO = new GenericDAO<>(Evento.class, session);
            eventoDAO.excluir(evento);
            this.evento = new Evento();
            eventos = eventoDAO.buscarTodos();
            inserir = true;
            session.close();
        } catch (PersistenceException ex) {
            ex.printStackTrace();
        }

    }
    public String botaoAlterar(Evento evento) {
        System.out.println(evento.getId());
        this.evento = new Evento(evento.getId(), evento.getData(), evento.getDescricao(), evento.getQuantidadeFrequentantes(), evento.getUsuarios(), evento.getFreqTrabalhadores());
        inserir = false;
        return "-";
    }
    /**
     * @return the evento
     */
    public Evento getEvento() {
        return evento;
    }

    /**
     * @param evento the evento to set
     */
    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    /**
     * @return the eventos
     */
    public List<Evento> getEventos() {
        return eventos;
    }

    /**
     * @param eventos the eventos to set
     */
    public void setEventos(List<Evento> eventos) {
        this.eventos = eventos;
    }
    
    
}
