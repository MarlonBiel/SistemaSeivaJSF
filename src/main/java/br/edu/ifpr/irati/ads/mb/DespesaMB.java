
package br.edu.ifpr.irati.ads.mb;

import br.edu.ifpr.irati.ads.dao.Dao;
import br.edu.ifpr.irati.ads.dao.GenericDAO;
import br.edu.ifpr.irati.ads.exception.PersistenceException;
import br.edu.ifpr.irati.ads.modelo.Despesa;
import br.edu.ifpr.irati.ads.modelo.Produto;
import br.edu.ifpr.irati.ads.util.HibernateUtil;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import org.hibernate.Session;
import javax.servlet.http.Part;
import org.primefaces.event.FileUploadEvent;

@ManagedBean
@SessionScoped
public class DespesaMB implements Serializable {

    private Despesa despesa = new Despesa();
    private List<Despesa> despesas;
    private Dao<Despesa> despesaDAO;
    private boolean inserir;
    private Part anexo;
    private String pathArquivos = "";

    public DespesaMB() throws PersistenceException {
        try {
            despesa = new Despesa();
            Session session = HibernateUtil.getSessionFactory().openSession();
            despesaDAO = new GenericDAO<>(Despesa.class, session);
            despesas = despesaDAO.buscarTodos();
            inserir = true;
            FacesContext facesContext = FacesContext.getCurrentInstance();
            ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
            String pathRel = servletContext.getRealPath(".");
            pathRel = pathRel.substring(0, pathRel.length());
            pathRel += "D:/NetBeans/arquivos";
            pathArquivos = pathRel;
            session.close();
        } catch (PersistenceException ex) {
            ex.printStackTrace();
        }
    }

    public void limparTela() {
        setDespesa(new Despesa());
    }

    public void salvar(FileUploadEvent event) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            despesaDAO = new GenericDAO<>(Despesa.class, session);
            this.despesa.setAnexos(event.getFile().getContent());
            if (inserir) {
                //executar o método inserir do DAO
                despesaDAO.salvar(despesa);
            } else {
                //executar o método alterar do DAO
                despesaDAO.alterar(despesa);
            }
            inserir =true;
            despesa = new Despesa();
            despesas = despesaDAO.buscarTodos();
            session.close();
        } catch (PersistenceException ex) {
            ex.printStackTrace();
        }
    }

    public void botaoExcluir(Despesa despesa) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            despesaDAO = new GenericDAO<>(Despesa.class, session);
            despesaDAO.excluir(despesa);
            this.despesa = new Despesa();
            despesas = despesaDAO.buscarTodos();
            inserir = true;
            session.close();
        } catch (PersistenceException ex) {
            ex.printStackTrace();
        }

    }
    public String botaoAlterar(Despesa despesa) {
        System.out.println(despesa.getId());
        this.despesa = new Despesa(despesa.getId(), despesa.getDescriminacao(), despesa.getData(), despesa.getValor(), despesa.getFormaPagamento(), despesa.getObservacao(), despesa.getAnexos());
        inserir = false;
        return "-";
    }
    
    public void upload(String novoNome) {
        String nomeArquivoSaida = pathArquivos + novoNome;
        try (InputStream is = anexo.getInputStream(); OutputStream out = new FileOutputStream(nomeArquivoSaida)) {
            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = is.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Despesa getDespesa() {
        return despesa;
    }

    public void setDespesa(Despesa despesa) {
        this.despesa = despesa;
    }

    public List<Despesa> getDespesas() {
        return despesas;
    }

    public void setDespesas(List<Despesa> despesas) {
        this.despesas = despesas;
    }

    public Part getAnexo() {
        return anexo;
    }

    public void setAnexo(Part anexo) {
        this.anexo = anexo;
    }

   public String getImagemProduto(Produto produto) {
        return "D:/NetBeans/arquivos" + despesa.getAnexos();
    }
 
    

}
