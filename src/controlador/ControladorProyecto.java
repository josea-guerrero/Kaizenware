package controlador;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import modelo.dto.Proyecto;
import modelo.dto.Cliente;
import modelo.servicios.ServicioProyectos;

@WebServlet("/ControladorProyecto")
public class ControladorProyecto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	ServicioProyectos serviProyectos;
	
    public ControladorProyecto() {
        super();
        this.serviProyectos = ServicioProyectos.getInstancia();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("clientesList", this.serviProyectos.getClientes());
		request.setAttribute("proyectosList", this.serviProyectos.getProyectos());
		request.getRequestDispatcher("/Vista/proyecto.ftl").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("nombre").equals("") ||
				request.getParameter("descripcion").equals("") ||
				request.getParameter("id_cliente").equals("") ) {
				response.getWriter().print("error:Debe indicar los valores requeridos");
			}			
			else {			
				String nombre = request.getParameter("nombre");
				String descripcion = request.getParameter("descripcion");
				Integer id_cliente = Integer.parseInt(request.getParameter("id_cliente"));
				Cliente cliente = this.serviProyectos.getClientePorId(new Integer(id_cliente));
				Proyecto proyecto = new Proyecto();
				proyecto.setNombre(nombre);
				proyecto.setDescripcion(descripcion);
				proyecto.setCliente(cliente);
				
				this.serviProyectos.incluirProyecto(proyecto);
				try {
					ObjectMapper objectMapper = new ObjectMapper();				
					response.getWriter().print(objectMapper.writeValueAsString(proyecto));
				} 
				catch (Exception e) {
					response.getWriter().print("error:"+e.getMessage());
				}
			}
	}
	
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> parametros = Utils.getParameterMap(request);		
		if (parametros.get("nombre").equals("")||
			parametros.get("descripcion").equals("")||
			parametros.get("id_cliente").equals("")) {
			response.getWriter().print("error:Debe indicar los valores requeridos");
		}			
		else {
			Integer id = Integer.parseInt(parametros.get("idString"));
			String nombre = parametros.get("nombre");
			System.out.println(nombre);
			String descripcion = parametros.get("descripcion");
			Integer id_cliente = Integer.parseInt(parametros.get("id_cliente"));
			
			Proyecto proyecto = this.serviProyectos.modificarProyecto(id, nombre, descripcion, id_cliente);
			try {
				ObjectMapper objectMapper = new ObjectMapper();
				response.getWriter().print(objectMapper.writeValueAsString(proyecto));
			} 
			catch (Exception e) {
				response.getWriter().print("error:"+e.getMessage());
			} 
		} 
	}

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> parametros = Utils.getParameterMap(request);
		Integer id = Integer.parseInt(parametros.get("id"));
		response.getWriter().print(this.serviProyectos.eliminarProyecto(id));
	}

}
