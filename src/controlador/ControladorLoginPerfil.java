package controlador;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.dto.Usuario;

/**
 * Servlet implementation class ControladorLoginPerfil
 */
@WebServlet("/ControladorLoginPerfil")
public class ControladorLoginPerfil extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ControladorLoginPerfil() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
		HttpSession session = request.getSession(false); //debe ser false por la autenticacion
		
		if(session!= null)
		{
			Usuario user = (Usuario) session.getAttribute("user");
			System.out.print("Bienvenido" + user.getNombre_usuario());
		}else
		{
			System.out.print("Por favor login primero");
			RequestDispatcher rd= request.getRequestDispatcher("/Vista/login.ftl");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
	}

}
