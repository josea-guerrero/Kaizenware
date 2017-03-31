package controlador;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.dto.Falta;
import modelo.dto.Usuario;
import modelo.servicios.ServicioCandidatos;
import modelo.servicios.ServicioUsuarios;

/**
 * Servlet implementation class ControladorMyProfile
 */
@WebServlet("/ControladorMyProfile")
public class ControladorMyProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     * 
     */
	
	private ServicioCandidatos serviProfile;
    public ControladorMyProfile() 
    {
        super();
        this.serviProfile = ServicioCandidatos.getInstancia();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	
	{
		HttpSession session = request.getSession(false);
		if(session!= null)
		{
			request.getRequestDispatcher("/Vista/myprofile.ftl").forward(request, response);
			//System.out.print("Bienvenido" + user.getNombre_usuario());
		}else
		{
			//System.out.print("Por favor login primero");
			request.getRequestDispatcher("/Vista/login.ftl").forward(request, response);
		}
			
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
	}

}
