package controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
