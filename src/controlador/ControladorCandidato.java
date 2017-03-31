package controlador;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import modelo.dto.Candidato;
import modelo.dto.Pais;
import modelo.dto.Usuario;
import modelo.servicios.ServicioCandidatos;

@WebServlet("/ControladorCandidato")
public class ControladorCandidato extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	ServicioCandidatos serviCandidatos;
	
    public ControladorCandidato() {
        super();
        this.serviCandidatos = ServicioCandidatos.getInstancia();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("paisesList", this.serviCandidatos.getPaises());
		request.setAttribute("candidatosList", this.serviCandidatos.getCandidatos());
		request.getRequestDispatcher("/Vista/candidato.ftl").forward(request, response);
	
//		HttpSession session = request.getSession(false);
//		
//		if(session!= null)
//		{
//			request.setAttribute("paisesList", this.serviCandidatos.getPaises());
//			request.setAttribute("candidatosList", this.serviCandidatos.getCandidatos());
//			request.getRequestDispatcher("/Vista/candidato.ftl").forward(request, response);
//			
//		}else
//		{
//			System.out.print("Por favor login primero");
//			request.getRequestDispatcher("Login");
//		}
	}	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("nombres").equals("") ||
				request.getParameter("apellidos").equals("") ||
				request.getParameter("telefono").equals("") ||
				request.getParameter("correo").equals("") ||
				request.getParameter("id_pais").equals("") ||
				request.getParameter("ciudad").equals("") ||
				request.getParameter("sexo").equals("") ) {
				response.getWriter().print("error:Debe indicar los valores requeridos");
			}			
			else {			
				String nombres = request.getParameter("nombres");
				String apellidos = request.getParameter("apellidos");
				String telefono = request.getParameter("telefono");
				String correo = request.getParameter("correo");
				Integer id_pais = Integer.parseInt(request.getParameter("id_pais"));
				String ciudad = request.getParameter("ciudad");
				Character sexo = request.getParameter("sexo").charAt(0);
				Pais pais = this.serviCandidatos.getPaisPorId(new Integer(id_pais));
				Candidato candidato = new Candidato();
				candidato.setNombres(nombres);
				candidato.setApellidos(apellidos);
				candidato.setTelefono(telefono);
				candidato.setCorreo(correo);
				candidato.setPais(pais);
				candidato.setCiudad(ciudad);
				candidato.setSexo(sexo);
				candidato.setEstado(1);
				this.serviCandidatos.incluirCandidato(candidato);
				try {
					ObjectMapper objectMapper = new ObjectMapper();				
					response.getWriter().print(objectMapper.writeValueAsString(candidato));
				} 
				catch (Exception e) {
					response.getWriter().print("error:"+e.getMessage());
				}
			}
	}
	
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> parametros = Utils.getParameterMap(request);		
		if (parametros.get("nombres").equals("")||
			parametros.get("apellidos").equals("")||
			parametros.get("telefono").equals("")||
			parametros.get("correo").equals("")||
			parametros.get("id_pais").equals("")||
			parametros.get("ciudad").equals("")||
			parametros.get("sexo").equals("")) {
			response.getWriter().print("error:Debe indicar los valores requeridos");
		}			
		else {
			Integer id = Integer.parseInt(parametros.get("idString"));
			String nombres = parametros.get("nombres");
			System.out.println(nombres);
			String apellidos = parametros.get("apellidos");
			String telefono = parametros.get("telefono");
			String correo = parametros.get("correo");
			Integer id_pais = Integer.parseInt(parametros.get("id_pais"));
			String ciudad = parametros.get("ciudad");
			Character sexo = parametros.get("sexo").charAt(0);
			Candidato candidato = this.serviCandidatos.modificarCandidato(id, nombres, apellidos, telefono, correo, id_pais, ciudad, sexo);
			try {
				ObjectMapper objectMapper = new ObjectMapper();
				response.getWriter().print(objectMapper.writeValueAsString(candidato));
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
		response.getWriter().print(this.serviCandidatos.eliminarCandidato(id));
	}

}
