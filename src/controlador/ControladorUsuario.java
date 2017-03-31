package controlador;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import modelo.dto.Candidato;
import modelo.dto.Rol;
import modelo.dto.Usuario;
import modelo.servicios.ServicioUsuarios;

@WebServlet("/ControladorUsuario")
public class ControladorUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ServicioUsuarios serviUsuario;

    public ControladorUsuario() {
    	super();
    	this.serviUsuario = ServicioUsuarios.getInstancia();
    	 }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.setAttribute("usuariosList", this.serviUsuario.getUsuarios());
		request.setAttribute("empleadosList", this.serviUsuario.getEmpleadosSinUsuario());
		request.setAttribute("rolList", this.serviUsuario.getRoles());		
		request.getRequestDispatcher("/Vista/gestionUsuario.ftl").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		if (request.getParameter("nombre").equals("")||
				request.getParameter("contrasena").equals("")||
				request.getParameter("candidato").equals("")||
				request.getParameter("rol").equals("")) 
		        {
				response.getWriter().print("error:Debe indicar los valores requeridos");
			}			
			else {			
				String nombre = request.getParameter("nombre");
				String contrasena = request.getParameter("contrasena");
				Integer idCandidato = Integer.parseInt(request.getParameter("candidato"));
				Integer idRol = Integer.parseInt(request.getParameter("rol"));
			
				Candidato candidato = this.serviUsuario.getCandidatoPorId(new Integer(idCandidato));
				Rol rol = this.serviUsuario.getRolPorId(new Integer(idRol));
				
				Usuario usuario = new Usuario();
				usuario.setNombre_usuario(nombre);
				usuario.setContrasena(contrasena);
				usuario.setCandidato(candidato);
				usuario.setRol(rol);
	
				this.serviUsuario.incluirUsuario(usuario);
				try {
					ObjectMapper objectMapper = new ObjectMapper();				
					response.getWriter().print(objectMapper.writeValueAsString(usuario));
				} 
				catch (Exception e) {
					response.getWriter().print("error:"+e.getMessage());
				}
			}
		
	}

	
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> parametros = Utils.getParameterMap(request);		
		if (request.getParameter("nombre").equals("")||
				request.getParameter("contrasena").equals("")||
				request.getParameter("candidato").equals("")||
				request.getParameter("rol").equals("")) 
		        {
				response.getWriter().print("error:Debe indicar los valores requeridos");
		}			
		else {
			Integer id = Integer.parseInt(parametros.get("idString"));
			String nombre = parametros.get("nombre");
			String contrasena = parametros.get("contrasena");
			Integer candidato = Integer.parseInt(parametros.get("candidato"));
			Integer rol = Integer.parseInt(parametros.get("rol"));
			
			Usuario usuario = new Usuario();
					
			this.serviUsuario.modificarUsuario(id, contrasena, candidato, rol, null, null, nombre);
			try {
				ObjectMapper objectMapper = new ObjectMapper();				
				response.getWriter().print(objectMapper.writeValueAsString(usuario));
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
		response.getWriter().print(this.serviUsuario.eliminarUsuario(id));
	}
	
	
}
