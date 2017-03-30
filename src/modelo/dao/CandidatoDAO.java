package modelo.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import modelo.dao.utils.GenericDAO;
import modelo.dao.utils.Sesion;
import modelo.dto.Candidato;

public class CandidatoDAO extends GenericDAO {

	private Sesion sesion;
	private static CandidatoDAO instancia;	
	public static CandidatoDAO getInstancia() {
		if (instancia == null) {
			instancia = new CandidatoDAO();
		}
		return instancia;
	}
	
	private CandidatoDAO() {
		super();
		this.sesion = Sesion.getInstancia();
	}
	
	public List<Candidato> queryAll() {	
		return super.queryAll(Candidato.class);
	}
	
	public List<Candidato>  EmpleadosSinUsuario() {
	
		this.sesion = Sesion.getInstancia();
		Session session = this.sesion.openSession();
		List<Candidato> empleados = null;
		try {
			empleados = session.getNamedQuery("findEmpleadoSinUsuario").list();
		}
		catch (Exception e) {  
        	e.printStackTrace();
        	System.out.println("Error con el NamedQuery");
        }  
        finally {  
            session.close();  
        }             
	    return empleados;

	}
	
	public List<Candidato>  EmpleadosSinAsistencia() {
		
		this.sesion = Sesion.getInstancia();
		Session session = this.sesion.openSession();
		List<Candidato> empleados = null;
		try {
			empleados = session.getNamedQuery("empleadosSinAsistenciaActual").list();
		}
		catch (Exception e) {  
        	e.printStackTrace();
        	System.out.println("Error con el NamedQuery");
        }  
        finally {  
            session.close();  
        }             
	    return empleados;
	}
	
	public Candidato get(Serializable id) {
		return (Candidato)super.get(Candidato.class, id);
	}

	public void save(Candidato candidato) {		
		super.save(candidato);
	}

	public void update(Candidato candidato) {
		super.update(candidato);
	}
	
	public void saveOrUpdate(Candidato candidato) {
	    super.saveOrUpdate(candidato);
	}
	
	public void delete(Candidato candidato) {
		super.delete(candidato);
	}
	
}

