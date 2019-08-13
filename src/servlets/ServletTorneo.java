package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidades.Ataque;
import entidades.Personaje;
import entidades.Torneo;
import entidades.Usuario;
import logic.CtrlCombate;
import logic.CtrlTorneo;

import java.util.List;
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
		if(request.getParameter("seleccionar")!= null){
			
			int idUsuario = getIdUsuario(request);
			int idPersonaje = getIdPersonaje(request);
			
			CtrlTorneo ctrl = new CtrlTorneo();
			
			int idUsuarioPersonaje = ctrl.getIdUsuarioPersonaje(idUsuario, idPersonaje);
			
			if(idUsuarioPersonaje != 0)
			{
			Torneo t = this.mapTournamentFromForm(request, idUsuarioPersonaje);

			try {
			ctrl.create(t);
			request.getSession().setAttribute("torneo", t);
			System.out.println("se creo el torneo");
			response.sendRedirect("routes/IniciarCombate.jsp");
									
			} catch (Exception e) {
				e.printStackTrace();
			}
			}
			else
			{
				System.out.println("NO se creo el torneo");
			}
		}
		
		if(request.getParameter("volver")!= null){
			response.sendRedirect("routes/Menu.jsp");
		}
		if(request.getParameter("nuevo")!= null){
			response.sendRedirect("routes/TorneoPersonaje.jsp");
		}
		if(request.getParameter("iniciar")!= null){
			Torneo t = (Torneo)request.getSession().getAttribute("torneo");
			CtrlTorneo ctrlTorneo = new CtrlTorneo();
			CtrlCombate ctrlCombate = new CtrlCombate();
			Personaje p1 = ctrlTorneo.getpersonaje(t.getIdUsuarioPersonaje());
			Personaje p2 = ctrlTorneo.getEnemigo(t.getId());
			ctrlCombate.seteaPer(p1, p2);
			request.getSession().setAttribute("CtrlCombate", ctrlCombate);
			request.getSession().setAttribute("P1", p1);
			request.getSession().setAttribute("P2", p2);
			request.getSession().setAttribute("nombreTurno", p1.getNombre());
			List<Ataque> ataques = ctrlCombate.getAtaquesOfPersonajeByEnergia(ctrlCombate.getIdPersonajeTurno(1),1);
			request.getSession().setAttribute("ataques", ataques);
			response.sendRedirect("routes/Combate.jsp");
		}
					
	}
	
	public Torneo mapTournamentFromForm(HttpServletRequest request, int idUsuarioPersonaje) {
		Torneo torneo = new Torneo();	
		System.out.println(idUsuarioPersonaje);
		torneo.setIdUsuarioPersonaje(idUsuarioPersonaje);		
		return torneo;
	}
	
	public int getIdUsuario(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Usuario u = (Usuario)session.getAttribute("usuario");  
		int idUsuario = Integer.parseInt(String.valueOf(u.getId()));
		System.out.println(idUsuario);
		return idUsuario;
	}
	
	public int getIdPersonaje(HttpServletRequest request) {
		int idPersonaje = Integer.parseInt(request.getParameter("idPersonaje"));
		System.out.println(idPersonaje);
		return idPersonaje;
	}

}
