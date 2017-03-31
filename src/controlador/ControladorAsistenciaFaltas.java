package controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.dto.Usuario;
import modelo.servicios.ServicioAsistencias;
import modelo.servicios.ServicioCandidatos;
import modelo.servicios.ServicioFaltas;

@WebServlet("/ControladorAsistenciaFaltas")
public class ControladorAsistenciaFaltas extends HttpServlet {
	private static final long serialVersionUID = 1L;

	ServicioAsistencias serviA;
	ServicioFaltas serviF;
    public ControladorAsistenciaFaltas() {
        super();
        this.serviA = ServicioAsistencias.getInstancia();
        this.serviF = ServicioFaltas.getInstancia();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          System.out.println("Hola");
		request.setAttribute("asistenciasList", this.serviA.getAsistencias());
		request.setAttribute("faltasList", this.serviF.getFaltas());
		request.getRequestDispatcher("/Vista/asistenciafaltas.ftl");
		
//		
//		HttpSession session = request.getSession(false); //debe ser false por la autenticacion
//		
//		if(session!= null)
//		{
//			request.getRequestDispatcher("/Vista/asistenciafaltas.ftl");
//		}else
//		{
//			System.out.print("Por favor login primero");
//			request.getRequestDispatcher("/Vista/login.ftl");
//		}	
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
