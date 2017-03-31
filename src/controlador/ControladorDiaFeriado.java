package controlador;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import modelo.dto.DiaFeriado;
import modelo.dto.Mes;
import modelo.servicios.ServicioDiaFeriado;

@WebServlet("/ControladorDiaFeriado")
public class ControladorDiaFeriado extends HttpServlet {

	private static final long serialVersionUID = 1L;
	ServicioDiaFeriado servicioDiaFeriado;
	
	public ControladorDiaFeriado() {
		super();
		this.servicioDiaFeriado = ServicioDiaFeriado.getInstancia();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("mesList", this.servicioDiaFeriado.getMeses());
		request.setAttribute("diaferiadoList", this.servicioDiaFeriado.getDiaFeriados());		
		request.getRequestDispatcher("/Vista/DiaFeriado.ftl").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("dia").equals("")||
			request.getParameter("mes").equals("")||
			request.getParameter("nombre").equals("")) {
			response.getWriter().print("error:Debe indicar los valores requeridos");
		}			
		else {			
			Integer dia = Integer.parseInt(request.getParameter("dia"));
			Integer idmes = Integer.parseInt(request.getParameter("mes"));
			String nombre = request.getParameter("nombre");
			Mes mes = this.servicioDiaFeriado.getMesPorId(new Integer(idmes));
			DiaFeriado diaferiado = new DiaFeriado();
			diaferiado.setDia(dia);
			diaferiado.setMes(mes);
			diaferiado.setNombre(nombre);
			this.servicioDiaFeriado.incluirDiaFeriado(diaferiado);
			try {
				ObjectMapper objectMapper = new ObjectMapper();				
				response.getWriter().print(objectMapper.writeValueAsString(diaferiado));
			} 
			catch (Exception e) {
				response.getWriter().print("error:"+e.getMessage());
			}
		}
	}

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> parametros = Utils.getParameterMap(request);		
		if (parametros.get("dia").equals("")||
			parametros.get("mes").equals("")||
			parametros.get("nombre").equals("")) {
			response.getWriter().print("error:Debe indicar los valores requeridos");
		}			
		else {
			Integer id = Integer.parseInt(parametros.get("idString"));
			Integer dia = Integer.parseInt(parametros.get("dia"));
			Integer idmes = Integer.parseInt(parametros.get("mes"));
			String nombre = parametros.get("nombre");			
			DiaFeriado diaferiado = this.servicioDiaFeriado.modificarDiaFeriado(id,dia, idmes, nombre);
			try {
				ObjectMapper objectMapper = new ObjectMapper();				
				response.getWriter().print(objectMapper.writeValueAsString(diaferiado));
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
		response.getWriter().print(this.servicioDiaFeriado.eliminarDiaFeriado(id));
	}
	
}
