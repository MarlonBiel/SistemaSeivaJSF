package br.edu.ifpr.irati.ads.mb;

import br.edu.ifpr.irati.ads.dao.Dao;
import br.edu.ifpr.irati.ads.dao.GenericDAO;
import br.edu.ifpr.irati.ads.exception.PersistenceException;
import br.edu.ifpr.irati.ads.modelo.Usuarios;
import br.edu.ifpr.irati.ads.util.HibernateUtil;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.hibernate.Session;


@ManagedBean
@SessionScoped
public class CursoMB implements Serializable{
    
    private List<Usuarios> cursos;
    private Usuarios curso;
    private Session session;
    private Dao<Usuarios> cursoDAO;

    public CursoMB() {
        try {
            //poderia buscar no banco de dados
            session = HibernateUtil.getSessionFactory().openSession();
            cursoDAO = new GenericDAO<>(Usuarios.class, session);
            cursos = cursoDAO.buscarTodos();
            curso = new Usuarios();
            session.close();
        } catch (PersistenceException ex) {
            ex.printStackTrace();
        }
    }
    
    public void salvar(){
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            cursoDAO = new GenericDAO<>(Usuarios.class, session);
            this.cursos.add(curso);
            cursoDAO.salvar(curso);
            session.close();
            this.limparTela();
        } catch (PersistenceException ex) {
            ex.printStackTrace();
        }
    }
    
    public void limparTela(){
        this.curso = new Usuarios();
    }
        
    public List<Usuarios> getCursos() {
        return cursos;
    }

    public void setCursos(List<Usuarios> cursos) {
        this.cursos = cursos;
    }

    public Usuarios getCurso() {
        return curso;
    }

    public void setCurso(Usuarios curso) {
        this.curso = curso;
    }
    
    
    
}
