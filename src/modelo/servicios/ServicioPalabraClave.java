package modelo.servicios;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import org.hibernate.mapping.Collection;

import modelo.dao.PalabraClaveDAO;
import modelo.dto.PalabraClave;


public class ServicioPalabraClave {
	
	private PalabraClaveDAO palabraDAO;
	
	private static ServicioPalabraClave instancia;	
	public static ServicioPalabraClave getInstancia() {
		if (instancia == null) {
			instancia = new ServicioPalabraClave();
		}
		return instancia;
	}
	
	private ServicioPalabraClave() {
		super();
		this.palabraDAO = PalabraClaveDAO.getInstancia();
	}
	
	public List<PalabraClave> getPalabraClave() {
		List<PalabraClave> palabra = this.palabraDAO.queryAll(); 
		return palabra;
	}
		
	public PalabraClave getPalabraClavePorId(Serializable id) {
		if (id != null) {
			return this.palabraDAO.get(id);
		}
		return null;
	}

	public PalabraClave incluirPalabraClave(PalabraClave palabra) {
		this.palabraDAO.saveOrUpdate(palabra);
		return palabra;
	}

	public PalabraClave modificarPalabraClave(Integer id, String palabra) {
		PalabraClave palabraclave = this.getPalabraClavePorId(id);
		palabraclave.setPalabra(palabra);
		this.palabraDAO.saveOrUpdate(palabraclave);
		return this.getPalabraClavePorId(id);
	}

	public String eliminarPalabraClave(Integer id) {
		PalabraClave palabra = this.getPalabraClavePorId(id);
		if (palabra != null) {
			this.palabraDAO.delete(palabra);
			return "ok";
		}
		return "No se pudo eliminar la palabra clave";
	}

}
