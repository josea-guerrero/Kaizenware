package modelo.servicios;

import java.util.List;

import modelo.dao.AsistenciaDAO;
import modelo.dao.CandidatoDAO;
import modelo.dao.FaltaDAO;
import modelo.dto.Asistencia;
import modelo.dto.Falta;


public class ServicioFaltas {
	
	private FaltaDAO faltaDAO;
	
	
private static ServicioFaltas instancia;
	
	public static ServicioFaltas getInstancia() {
		if (instancia == null) {
			instancia = new ServicioFaltas();
		}
		return instancia;
	}
	
	private ServicioFaltas() {
		super();
		this.faltaDAO = FaltaDAO.getInstancia();
		
	}
	
	
	public List<Falta> getFaltas() {
		List<Falta> faltas = this.faltaDAO.queryAll(); 
		return faltas;
	}

}
