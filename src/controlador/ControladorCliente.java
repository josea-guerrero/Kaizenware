package controlador;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import modelo.dto.Cliente;
import modelo.dto.Pais;
import modelo.servicios.ServicioClientes;

@WebServlet("/ControladorCliente")
public class ControladorCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	ServicioClientes serviClientes;
	
    public ControladorCliente() {
        super();
        this.serviClientes = ServicioClientes.getInstancia();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("paisesList", this.serviClientes.getPaises());
		request.setAttribute("clientesList", this.serviClientes.getClientes());
		request.getRequestDispatcher("/Vista/cliente.ftl").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (    request.getParameter("nombre").equals("") ||
				request.getParameter("nom_contacto").equals("") ||
				request.getParameter("tlf_contacto").equals("") ||
				request.getParameter("correo").equals("") ||
				request.getParameter("correo_contacto").equals("") ||
				request.getParameter("id_pais").equals("")) {
				response.getWriter().print("error:Debe indicar los valores requeridos");
			}			
			else {			
				String nombre = request.getParameter("nombre");
				String nom_contacto = request.getParameter("nom_contacto");
				String tlf_contacto = request.getParameter("tlf_contacto");
				String correo = request.getParameter("correo");
				String correo_contacto = request.getParameter("correo_contacto");
				Integer id_pais = Integer.parseInt(request.getParameter("id_pais"));
				Pais pais = this.serviClientes.getPaisPorId(new Integer(id_pais));
				Cliente cliente = new Cliente();
				cliente.setNombre(nombre);
				cliente.setNom_contacto(nom_contacto);
				cliente.setTlf_contacto(tlf_contacto);
				cliente.setCorreo(correo);
				cliente.setCorreo_contacto(correo_contacto);
				cliente.setPais(pais);
				
				this.serviClientes.incluirCliente(cliente);
				try {
					ObjectMapper objectMapper = new ObjectMapper();				
					response.getWriter().print(objectMapper.writeValueAsString(cliente));
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
			parametros.get("nom_contacto").equals("")||
			parametros.get("tlf_contacto").equals("")||
			parametros.get("correo").equals("")||
			parametros.get("correo_contacto").equals("")||
			parametros.get("id_pais").equals("")) {
			response.getWriter().print("error:Debe indicar los valores requeridos");
		}			
		else {
			Integer id = Integer.parseInt(parametros.get("idString"));
			String nombre = parametros.get("nombre");
			System.out.println(nombre);
			String nom_contacto = parametros.get("nom_contacto");
			String tlf_contacto = parametros.get("tlf_contacto");
			String correo = parametros.get("correo");
			String correo_contacto = parametros.get("correo_contacto");
			Integer id_pais = Integer.parseInt(parametros.get("id_pais"));
			
			Cliente cliente = this.serviClientes.modificarCliente(id, nombre, nom_contacto, tlf_contacto, correo,correo_contacto, id_pais);
			try {
				ObjectMapper objectMapper = new ObjectMapper();
				response.getWriter().print(objectMapper.writeValueAsString(cliente));
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
		response.getWriter().print(this.serviClientes.eliminarCliente(id));
	}

}
