package modelo.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;

import modelo.dao.utils.GenericDAO;
import modelo.dao.utils.Sesion;
import modelo.dto.Asistencia;
import modelo.dto.Falta;

public class FaltaDAO extends GenericDAO {

	private Sesion sesion;
	private static FaltaDAO instancia;	
	public static FaltaDAO getInstancia() {
		if (instancia == null) {
			instancia = new FaltaDAO();
		}
		return instancia;
	}
	
	private FaltaDAO() {
		super();
		this.sesion = Sesion.getInstancia();
	}
	
	public List<Falta> queryAll() {	
		return super.queryAll(Falta.class);
	}

	public Falta get(Serializable id) {
		Session session = this.sesion.openSession();  
        Falta falta = null;        
        try{
            falta = (Falta) session.get(Falta.class,  id);
            Hibernate.initialize(falta.getCandidato());
        } 
        catch (Exception e) {
        	System.out.println("Error obteniendo asistencia");
        	e.printStackTrace();        
        }  
        finally {  
            session.close();  
        }             
	    return falta;
	}

	public void save(Falta falta) {		
		super.save(falta);
	}

	public void update(Falta falta) {
		super.update(falta);
	}
	
	public void saveOrUpdate(Falta falta) {
	    super.saveOrUpdate(falta);
	}
	
	public void delete(Falta falta) {
		super.delete(falta);
	}
	
}

