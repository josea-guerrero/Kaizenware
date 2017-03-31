package modelo.servicios;

import java.io.Serializable;
import java.util.List;

import modelo.dao.AsistenciaDAO;
import modelo.dao.PagoDAO;
import modelo.dto.Asistencia;
import modelo.dto.Pago;

public class ServicioPagos {
	
	private PagoDAO pagoDAO;
	private AsistenciaDAO asistenciaDAO;
	
	private static ServicioPagos instancia;
	
	public static ServicioPagos getInstancia() {
		if (instancia == null) {
			instancia = new ServicioPagos();
		}
		return instancia;
	}
	
	private ServicioPagos() {
		super();
		this.pagoDAO = PagoDAO.getInstancia();
		this.asistenciaDAO = AsistenciaDAO.getInstancia();
	}
		
	public List<Object[]> getPagosPendientes() {
		return this.asistenciaDAO.AsistenciasNoPagas();
	}
	
	public Pago getPagoPorId(Serializable id) {
		if (id != null) {
			return this.pagoDAO.get(id);
		}
		return null;
	}
	
	public List<Pago> getPagos() {
		List<Pago> pagos = this.pagoDAO.queryAll(); 
		return pagos;
	}
	
	public Pago incluirPago(Pago pago) {
		this.pagoDAO.saveOrUpdate(pago);
		return pago;
	}

	public Pago modificarPago(Integer id) {
		Pago pago = this.getPagoPorId(id);
//		pago.setPais(pais);
//		pago.setContrasena(contrasena);
//		pago.setContrasena_confirma(contrasena_confirma);
//		pago.setFoto_perfil(foto_perfil);
//		pago.setNombre_pago(nombre_pago);
		this.pagoDAO.saveOrUpdate(pago);
		return this.getPagoPorId(id);
	}

	public String eliminarPago(Integer id) {
		Pago pago = this.getPagoPorId(id);
		if (pago != null) {
			this.pagoDAO.delete(pago);
			return "ok";
		}
		return "No se pudo eliminar el pago";
	}

}
