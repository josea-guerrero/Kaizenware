package modelo.dao;

import java.io.Serializable;
import java.util.List;

import modelo.dao.utils.GenericDAO;
import modelo.dto.Pago;

public class PagoDAO extends GenericDAO {

	private static PagoDAO instancia;	
	public static PagoDAO getInstancia() {
		if (instancia == null) {
			instancia = new PagoDAO();
		}
		return instancia;
	}
	
	private PagoDAO() {
		super();
	}
	
	public List<Pago> queryAll() {	
		return super.queryAll(Pago.class);
	}

	public Pago get(Serializable id) {
		return (Pago)super.get(Pago.class, id);
	}

	public void save(Pago pago) {		
		super.save(pago);
	}

	public void update(Pago pago) {
		super.update(pago);
	}
	
	public void saveOrUpdate(Pago pago) {
	    super.saveOrUpdate(pago);
	}
	
	public void delete(Pago pago) {
		super.delete(pago);
	}
	
}

