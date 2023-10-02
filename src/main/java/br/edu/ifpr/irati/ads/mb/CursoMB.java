package br.edu.ifpr.irati.ads.mb;

import br.edu.ifpr.irati.ads.dao.Dao;
import br.edu.ifpr.irati.ads.dao.GenericDAO;
import br.edu.ifpr.irati.ads.exception.PersistenceException;
import br.edu.ifpr.irati.ads.modelo.Curso;
import br.edu.ifpr.irati.ads.util.HibernateUtil;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.hibernate.Session;


@ManagedBean
@SessionScoped
public class CursoMB implements Serializable{
    
    private List<Curso> cursos;
    private Curso curso;
    private Session session;
    private Dao<Curso> cursoDAO;

    public CursoMB() {
        try {
            //poderia buscar no banco de dados
            session = HibernateUtil.getSessionFactory().openSession();
            cursoDAO = new GenericDAO<>(Curso.class, session);
            cursos = cursoDAO.buscarTodos();
            curso = new Curso();
            session.close();
        } catch (PersistenceException ex) {
            ex.printStackTrace();
        }
    }
    
    public void salvar(){
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            cursoDAO = new GenericDAO<>(Curso.class, session);
            this.cursos.add(curso);
            cursoDAO.salvar(curso);
            session.close();
            this.limparTela();
        } catch (PersistenceException ex) {
            ex.printStackTrace();
        }
    }
    
    public void limparTela(){
        this.curso = new Curso();
    }
        
    public List<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }
    
    
    
}
