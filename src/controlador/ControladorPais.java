package controlador;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import modelo.dto.Pais;
import modelo.dto.Usuario;
import modelo.dto.Rol;

import modelo.servicios.ServicioPais;

@WebServlet("/ControladorPais")
public class ControladorPais extends HttpServlet {

	private ServicioPais servicioPais;
	
	public ControladorPais() {
		super();
		this.servicioPais = ServicioPais.getInstancia();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("paisList", this.servicioPais.getPais());		
		request.getRequestDispatcher("/Vista/Pais.ftl").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("pais").equals("")) {
			response.getWriter().print("error:Debe indicar los valores requeridos");
		}			
		else {			
			String nombre = request.getParameter("pais");
			Pais pais = new Pais();
			pais.setPais(nombre);
			this.servicioPais.incluirPais(pais);
			try {
				ObjectMapper objectMapper = new ObjectMapper();				
				response.getWriter().print(objectMapper.writeValueAsString(pais));
			} 
			catch (Exception e) {
				response.getWriter().print("error:"+e.getMessage());
			}
		}
	}

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> parametros = Utils.getParameterMap(request);		
		if (parametros.get("idString").equals("")||
				parametros.get("pais").equals("")) {
			response.getWriter().print("error:Debe indicar los valores requeridos");
		}			
		else {
			Integer id = Integer.parseInt(parametros.get("idString"));
			String nombre = parametros.get("pais");			
			Pais Pais = this.servicioPais.modificarPais(id, nombre);
			try {
				ObjectMapper objectMapper = new ObjectMapper();				
				response.getWriter().print(objectMapper.writeValueAsString(Pais));
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
		response.getWriter().print(this.servicioPais.eliminarPais(id));
	}
	
}
