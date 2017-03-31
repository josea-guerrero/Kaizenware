package modelo.servicios;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import modelo.dao.PaisDAO;

import modelo.dao.ClienteDAO;
import modelo.dto.Pais;

import modelo.dto.Cliente;

public class ServicioClientes {
	
	private ClienteDAO clienteDAO;
	private PaisDAO paisDAO;
	
	
	private static ServicioClientes instancia;
	
	public static ServicioClientes getInstancia() {
		if (instancia == null) {
			instancia = new ServicioClientes();
		}
		return instancia;
	}
	
	private ServicioClientes() {
		super();
		this.clienteDAO = ClienteDAO.getInstancia();
		this.paisDAO = PaisDAO.getInstancia();
		
	}
	
	public List<Cliente> getClientes() {
		List<Cliente> clientes = this.clienteDAO.queryAll(); 
		return clientes;
	}
	
	public List<Pais> getPaises() {
		List<Pais> paises = this.paisDAO.queryAll();
//		Collections.sort(paises);
		return paises;
	}
	
	
	
	public Cliente getClientePorId(Serializable id) {
		if (id != null) {
			return this.clienteDAO.get(id);
		}
		return null;
	}
	
	public Pais getPaisPorId(Serializable id) {
		if (id != null) { 
			return this.paisDAO.get(id);
		}
		return null;
	}
	
	
	public Cliente incluirCliente(Cliente cliente) {
		this.clienteDAO.saveOrUpdate(cliente);
		return cliente;
	}

	public Cliente modificarCliente(Integer id, String nombre, String nom_contacto, String tlf_contacto, String correo,String correo_contacto, Integer idPais) {
		Cliente cliente = this.getClientePorId(id);
		Pais pais = this.getPaisPorId(idPais);
		cliente.setPais(pais);
		cliente.setNombre(nombre);
		cliente.setNom_contacto(nom_contacto);
		cliente.setTlf_contacto(tlf_contacto);
		cliente.setCorreo(correo);
		cliente.setCorreo_contacto(correo_contacto);
		
		this.clienteDAO.saveOrUpdate(cliente);
		return this.getClientePorId(id);
	}

	public String eliminarCliente(Integer id) {
		Cliente cliente = this.getClientePorId(id);
		if (cliente != null) {
			this.clienteDAO.delete(cliente);
			return "ok";
		}
		return "No se pudo eliminar el cliente";
	}

}
