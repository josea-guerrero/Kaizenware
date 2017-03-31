package controlador;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import modelo.dao.MesDAO;
import modelo.dto.Asistencia;
import modelo.dto.Candidato;
import modelo.dto.Mes;
import modelo.dto.Pago;
import modelo.dto.Pais;
import modelo.servicios.ServicioAsistencias;
import modelo.servicios.ServicioCandidatos;
import modelo.servicios.ServicioPagos;

@WebServlet("/ControladorPago")
public class ControladorPago extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	ServicioPagos serviPagos;
	ServicioAsistencias serviAsis;
	ServicioCandidatos serviCandi;
	MesDAO mesDAO;
	
    public ControladorPago() {
        super();
        this.serviPagos = ServicioPagos.getInstancia();
        this.serviAsis = ServicioAsistencias.getInstancia();
        this.mesDAO = MesDAO.getInstancia();
        this.serviCandi = ServicioCandidatos.getInstancia();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Asistencia> asistenciasList = new ArrayList<Asistencia>();
		List<Object[]> pagos = serviPagos.getPagosPendientes();
		for (Object[] pago : pagos) {
			Integer nro = Integer.parseInt(pago[0].toString());
	        Asistencia asistencia = serviAsis.getAsistenciaPorId(nro); 
	        asistenciasList.add(asistencia);
	    }
		request.setAttribute("asistenciasList", asistenciasList);
		request.getRequestDispatcher("/Vista/pago.ftl").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("id_empleado").equals("") ||
				request.getParameter("id_mes").equals("") ||
				request.getParameter("descripcion").equals("")||
				request.getAttribute("monto").equals("") ||
				request.getParameter("fecha").equals("")) {
				response.getWriter().print("error:Debe indicar los valores requeridos");
			}			
			else {			
				Integer id_empleado = Integer.parseInt(request.getParameter("id_empleado"));
				Integer id_mes = Integer.parseInt(request.getParameter("id_mes"));
				Integer anio = Integer.parseInt(request.getParameter("anio"));
				Double monto = Double.parseDouble(request.getParameter("monto"));
				String descripcion = request.getParameter("descripcion");
				DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				Calendar fecha  = Calendar.getInstance();
				try {
					fecha.setTime(df.parse(request.getParameter("fecha")));
				} catch (ParseException e1) {
					System.out.println("error parseando fecha");
					e1.printStackTrace();
				}
				
				Mes mes = mesDAO.get(id_mes);
				Candidato empleado = serviCandi.getCandidatoPorId(id_empleado);
				Pago pago = new Pago();
				pago.setCandidato(empleado);
				pago.setMes(mes);
				pago.setFecha_emision(fecha);
				pago.setMonto(monto);
				pago.setDescripcion(descripcion);
	
				this.serviPagos.incluirPago(pago);
				try {
					ObjectMapper objectMapper = new ObjectMapper();				
					response.getWriter().print(objectMapper.writeValueAsString(pago));
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
			Pago pago = this.serviPagos.modificarPago(id);
			try {
				ObjectMapper objectMapper = new ObjectMapper();
				response.getWriter().print(objectMapper.writeValueAsString(pago));
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
		response.getWriter().print(this.serviPagos.eliminarPago(id));
	}

}
