package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Torneo;
import entidades.Usuario;
import logic.CtrlTorneo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * Servlet implementation class Torneo
 */
@WebServlet("/Torneo")
public class ServletTorneo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletTorneo() {
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


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		if(request.getParameter("nuevo")!= null){
			CtrlTorneo ctrl = new CtrlTorneo();
			Torneo t = this.mapTournamentFromForm(request);

			try {
			ctrl.create(t);
			System.out.println("se creo el torneo");
									
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if(request.getParameter("volver")!= null){
			response.sendRedirect("routes/Menu.jsp");
		}
					
	}
	
	public Torneo mapTournamentFromForm(HttpServletRequest request) {
		Torneo torneo = new Torneo();
		
		torneo.setIdUsuarioPersonaje(1);
		
		return torneo;
	}

}
