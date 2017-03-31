package controlador;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;



import modelo.dto.PalabraClave;
import modelo.servicios.ServicioPalabraClave;

@WebServlet("/ControladorPalabraClave")
public class ControladorPalabraClave extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	ServicioPalabraClave serviciopalabraclave;

    public ControladorPalabraClave() {
        super();
        this.serviciopalabraclave = ServicioPalabraClave.getInstancia();
    }

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("palabraclaveList", this.serviciopalabraclave.getPalabraClave());
		request.getRequestDispatcher("/Vista/PalabraClave.ftl").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("palabra").equals("")) {
			response.getWriter().print("error:Debe indicar los valores requeridos");
			}			
			else {	
				String palabra = request.getParameter("palabra");
				PalabraClave palabrac = new PalabraClave();
				palabrac.setPalabra(palabra);
				this.serviciopalabraclave.incluirPalabraClave(palabrac);
				try {
					ObjectMapper objectMapper = new ObjectMapper();				
					response.getWriter().print(objectMapper.writeValueAsString(palabrac));
				} 
				catch (Exception e) {
					response.getWriter().print("error:"+e.getMessage());
				}
			}
		}
	
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> parametros = Utils.getParameterMap(request);		
		if (parametros.get("palabra").equals("")) {
			response.getWriter().print("error:Debe indicar los valores requeridos");
		}			
		else {
			Integer id = Integer.parseInt(parametros.get("idString"));
			String palabra = parametros.get("palabra");
			PalabraClave palabrac = this.serviciopalabraclave.modificarPalabraClave(id, palabra);
			try {
				ObjectMapper objectMapper = new ObjectMapper();				
				response.getWriter().print(objectMapper.writeValueAsString(palabrac));
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
		response.getWriter().print(this.serviciopalabraclave.eliminarPalabraClave(id));
	}
	
}


