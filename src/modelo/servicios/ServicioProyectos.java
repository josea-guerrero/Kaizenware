package modelo.servicios;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import modelo.dao.ClienteDAO;
import modelo.dao.PalabraClaveDAO;
import modelo.dao.ProyectoDAO;
import modelo.dto.Cliente;
import modelo.dto.PalabraClave;
import modelo.dto.Proyecto;

public class ServicioProyectos {
	
	private ProyectoDAO proyectoDAO;
	private ClienteDAO clienteDAO;
	private PalabraClaveDAO palabraClaveDAO;
	
	private static ServicioProyectos instancia;
	
	public static ServicioProyectos getInstancia() {
		if (instancia == null) {
			instancia = new ServicioProyectos();
		}
		return instancia;
	}
	
	private ServicioProyectos() {
		super();
		this.proyectoDAO = ProyectoDAO.getInstancia();
		this.clienteDAO = ClienteDAO.getInstancia();
		
	}
	
	public List<Proyecto> getProyectos() {
		List<Proyecto> proyectos = this.proyectoDAO.queryAll(); 
		return proyectos;
	}
	
	public List<Cliente> getClientes() {
		List<Cliente> clientes = this.clienteDAO.queryAll();
//		Collections.sort(clientes);
		return clientes;
	}
	
	
	public Proyecto getProyectoPorId(Serializable id) {
		if (id != null) {
			return this.proyectoDAO.get(id);
		}
		return null;
	}
	
	public Cliente getClientePorId(Serializable id) {
		if (id != null) { 
			return this.clienteDAO.get(id);
		}
		return null;
	}
	
	
	public Proyecto incluirProyecto(Proyecto proyecto) {
		this.proyectoDAO.saveOrUpdate(proyecto);
		return proyecto;
	}

	public Proyecto modificarProyecto(Integer id, String nombre, String descripcion,Integer idCliente) {
		Proyecto proyecto = this.getProyectoPorId(id);
		Cliente cliente = this.getClientePorId(idCliente);
		proyecto.setCliente(cliente);
		proyecto.setNombre(nombre);
		proyecto.setDescripcion(descripcion);
	
		this.proyectoDAO.saveOrUpdate(proyecto);
		return this.getProyectoPorId(id);
	}

	public String eliminarProyecto(Integer id) {
		Proyecto proyecto = this.getProyectoPorId(id);
		if (proyecto != null) {
			this.proyectoDAO.delete(proyecto);
			return "ok";
		}
		return "No se pudo eliminar el proyecto";
	}

}
