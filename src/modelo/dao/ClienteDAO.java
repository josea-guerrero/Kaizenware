package modelo.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import modelo.dao.utils.GenericDAO;
import modelo.dao.utils.Sesion;
import modelo.dto.Cliente;

public class ClienteDAO extends GenericDAO {

	private Sesion sesion;
	private static ClienteDAO instancia;	
	public static ClienteDAO getInstancia() {
		if (instancia == null) {
			instancia = new ClienteDAO();
		}
		return instancia;
	}
	
	private ClienteDAO() {
		super();
		this.sesion = Sesion.getInstancia();
	}
	
	public List<Cliente> queryAll() {	
		return super.queryAll(Cliente.class);
	}
	
	
	
	public Cliente get(Serializable id) {
		return (Cliente)super.get(Cliente.class, id);
	}

	public void save(Cliente cliente) {		
		super.save(cliente);
	}

	public void update(Cliente cliente) {
		super.update(cliente);
	}
	
	public void saveOrUpdate(Cliente cliente) {
	    super.saveOrUpdate(cliente);
	}
	
	public void delete(Cliente cliente) {
		super.delete(cliente);
	}
	
}