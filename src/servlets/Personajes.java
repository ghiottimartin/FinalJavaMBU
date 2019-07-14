package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidades.Personaje;
import entidades.Usuario;
import logic.ControladorABMCPersonaje;

/**
 * Servlet implementation class Personajes
 */
@WebServlet("/Personajes")
public class Personajes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Personajes() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("guardarPersonaje") != null){
			Personaje per = this.mapCharacterFromForm(request);
			ControladorABMCPersonaje ctrlPersonaje = new ControladorABMCPersonaje();
			HttpSession session = request.getSession();
			Usuario currentUser = (Usuario) session.getAttribute("usuario");
			try {
				 int id_personaje = ctrlPersonaje.agregarPersonaje(per);
				 ctrlPersonaje.insertarPersonajeUsuario(id_personaje, currentUser.getId());
				String selected_attacks[] = request.getParameterValues("selectedAttacks");
			     for(String id_attack :selected_attacks)
			     { 	 
			    	 ctrlPersonaje.insertarPersonajeAtaque(id_personaje, Integer.parseInt(id_attack));
			     }
			     response.sendRedirect("routes/Menu.jsp");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public Personaje mapCharacterFromForm(HttpServletRequest request){
		Personaje per = new Personaje();
		per.setNombre(request.getParameter("nombre"));
		per.setVida(Integer.parseInt(request.getParameter("vida")));
		per.setEnergia(Integer.parseInt(request.getParameter("energia")));
		per.setDefensa(Integer.parseInt(request.getParameter("defensa")));
		per.setEvasion(Integer.parseInt(request.getParameter("evasion")));
		
		return per;
	}

}
