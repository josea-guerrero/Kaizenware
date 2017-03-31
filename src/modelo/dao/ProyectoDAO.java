package modelo.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import modelo.dao.utils.GenericDAO;
import modelo.dao.utils.Sesion;
import modelo.dto.Proyecto;

public class ProyectoDAO extends GenericDAO {

	private Sesion sesion;
	private static ProyectoDAO instancia;	
	public static ProyectoDAO getInstancia() {
		if (instancia == null) {
			instancia = new ProyectoDAO();
		}
		return instancia;
	}
	
	private ProyectoDAO() {
		super();
		this.sesion = Sesion.getInstancia();
	}
	
	public List<Proyecto> queryAll() {	
		return super.queryAll(Proyecto.class);
	}
	
	public Proyecto get(Serializable id) {
		return (Proyecto)super.get(Proyecto.class, id);
	}

	public void save(Proyecto proyecto) {		
		super.save(proyecto);
	}

	public void update(Proyecto proyecto) {
		super.update(proyecto);
	}
	
	public void saveOrUpdate(Proyecto proyecto) {
	    super.saveOrUpdate(proyecto);
	}
	
	public void delete(Proyecto proyecto) {
		super.delete(proyecto);
	}
	
}

