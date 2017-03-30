package modelo.servicios;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import modelo.dao.PaisDAO;
import modelo.dao.PalabraClaveDAO;
import modelo.dao.AsistenciaDAO;
import modelo.dao.CandidatoDAO;
import modelo.dto.Pais;
import modelo.dto.PalabraClave;
import modelo.dto.Asistencia;
import modelo.dto.Candidato;

public class ServicioAsistencias {
	
	private CandidatoDAO candidatoDAO;
	private AsistenciaDAO asistenciaDAO;
	
	private static ServicioAsistencias instancia;
	
	public static ServicioAsistencias getInstancia() {
		if (instancia == null) {
			instancia = new ServicioAsistencias();
		}
		return instancia;
	}
	
	private ServicioAsistencias() {
		super();
		this.candidatoDAO = CandidatoDAO.getInstancia();
		this.asistenciaDAO = AsistenciaDAO.getInstancia();
	}
	
	public List<Candidato> getEmpleadosSinAsistencia() {
		List<Candidato> empleados = this.candidatoDAO.EmpleadosSinAsistencia(); 
		return empleados;
	}
	
	
	public Asistencia getAsistenciaPorId(Serializable id) {
		if (id != null) {
			return this.asistenciaDAO.get(id);
		}
		return null;
	}
	
	public List<Asistencia> getAsistencias() {
		List<Asistencia> asistencias = this.asistenciaDAO.queryAll(); 
		return asistencias;
	}
	
	public Asistencia incluirAsistencia(Asistencia asistencia) {
		this.asistenciaDAO.saveOrUpdate(asistencia);
		return asistencia;
	}

	public Asistencia modificarAsistencia(Integer id, Integer idPais, String contrasena, String contrasena_confirma,
			String foto_perfil, String nombre_candidato) {
		Asistencia asistencia = this.getAsistenciaPorId(id);
//		candidato.setPais(pais);
//		candidato.setContrasena(contrasena);
//		candidato.setContrasena_confirma(contrasena_confirma);
//		candidato.setFoto_perfil(foto_perfil);
//		candidato.setNombre_candidato(nombre_candidato);
		this.candidatoDAO.saveOrUpdate(asistencia);
		return this.getAsistenciaPorId(id);
	}

	public String eliminarAsistencia(Integer id) {
		Asistencia asistencia = this.getAsistenciaPorId(id);
		if (asistencia != null) {
			this.asistenciaDAO.delete(asistencia);
			return "ok";
		}
		return "No se pudo eliminar la asistencia";
	}

}
