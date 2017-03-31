package modelo.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import modelo.dao.utils.GenericDAO;
import modelo.dao.utils.Sesion;
import modelo.dto.Asistencia;
import modelo.dto.Candidato;

public class AsistenciaDAO extends GenericDAO {
	
	private Sesion sesion;
	private static AsistenciaDAO instancia;
	
	public static AsistenciaDAO getInstancia() {
		if (instancia == null) {
			instancia = new AsistenciaDAO();
		}
		return instancia;
	}
	
	private AsistenciaDAO() {
		super();
		this.sesion = Sesion.getInstancia();
	}
	
	public List<Asistencia> queryAll() {	
		return super.queryAll(Asistencia.class);
	}
	
	public List<Object[]> AsistenciasNoPagas() {
		this.sesion = Sesion.getInstancia();
		Session session = this.sesion.openSession();
		List<Object[]> asistencias = null;
		try {
			SQLQuery q = session.createSQLQuery("SELECT * FROM asistencia asis WHERE asis.id NOT IN (SELECT a.id FROM asistencia a,"
					+ " (SELECT p.*, c.id_candidato as candidato FROM pago p INNER JOIN cargo c ON p.id_cargo = c.id) pagos"
					+ " WHERE a.id_candidato = pagos.candidato AND a.mes = pagos.mes)");
			asistencias = q.list();
		}
		catch (Exception e) {  
        	e.printStackTrace();
        	System.out.println("Error con el createSQLQuery");
        }
        finally {  
            session.close();  
        }             
	    return asistencias;
	}

	public Asistencia get(Serializable id) {
		Session session = this.sesion.openSession();  
        Asistencia asistencia = null;        
        try{
            asistencia = (Asistencia) session.get(Asistencia.class,  id);
            Hibernate.initialize(asistencia.getCandidato());
        } 
        catch (Exception e) { 
        	System.out.println("Error obteniendo asistencia");
        	e.printStackTrace();        
        }  
        finally {  
            session.close();  
        }             
	    return asistencia;
	}

	public void save(Asistencia asistencia) {		
		super.save(asistencia);
	}

	public void update(Asistencia asistencia) {
		super.update(asistencia);
	}
	
	public void saveOrUpdate(Asistencia asistencia) {
	    super.saveOrUpdate(asistencia);
	}
	
	public void delete(Asistencia asistencia) {
		super.delete(asistencia);
	}
	
}

