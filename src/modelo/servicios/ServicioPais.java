package modelo.servicios;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import modelo.dao.*;
import modelo.dto.*;

public class ServicioPais {

	private PaisDAO paisDAO;
	
	private static ServicioPais instancia;	
	public static ServicioPais getInstancia() {
		if (instancia == null) {
			instancia = new ServicioPais();
		}
		return instancia;
	}
	
	@SuppressWarnings("static-access")
	private ServicioPais() {
		super();
		this.paisDAO = paisDAO.getInstancia();
	}
	
	public List<Pais> getPais() {
		List<Pais> pais = this.paisDAO.queryAll(); 
		return pais;
	}
	
	
		
	public Pais getpaisPorId(Serializable id) {
		if (id != null) {
			return this.paisDAO.get(id);
		}
		return null;
	}

	
	public Pais incluirPais(Pais pais) {
		this.paisDAO.saveOrUpdate(pais);
		return pais;
	}

	public Pais modificarPais(Integer id, String nombre) {
		Pais pais = this.getpaisPorId(id);
		pais.setPais(nombre);
		this.paisDAO.saveOrUpdate(pais);
		return this.getpaisPorId(id);
	}

	public String eliminarPais(Integer id) {
		Pais pais = this.getpaisPorId(id);
		if (pais != null) {
			this.paisDAO.delete(pais);
			return "ok";
		}
		return "No se pudo eliminar el Pais";
	}

}
