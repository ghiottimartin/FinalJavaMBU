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
import utils.ApplicationException;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			if (request.getParameter("seleccionar") != null) {
				int idUsuario = getIdUsuario(request);
				int idPersonaje = getIdPersonaje(request);

				CtrlTorneo ctrl = new CtrlTorneo();

				int idUsuarioPersonaje = ctrl.getIdUsuarioPersonaje(idUsuario, idPersonaje);

				if (idUsuarioPersonaje != 0) {
					Torneo t = this.mapTournamentFromForm(request, idUsuarioPersonaje);

					ctrl.create(t);
					request.getSession().setAttribute("torneo", t);
					response.sendRedirect("routes/IniciarCombate.jsp");

				} else {
					request.getSession().setAttribute("error", "No se creo el torneo");
					response.sendRedirect("/WebPage/routes/MensajeError.jsp");
				}
			}

			if (request.getParameter("volver") != null) {
				response.sendRedirect("routes/Menu.jsp");
			}
			if (request.getParameter("nuevo") != null) {
				response.sendRedirect("routes/TorneoPersonaje.jsp");
			}
			if (request.getParameter("cargar") != null) {
				response.sendRedirect("routes/CargaTorneo.jsp");
			}
			if (request.getParameter("iniciar") != null) {
				Torneo t = (Torneo) request.getSession().getAttribute("torneo");
				CtrlTorneo ctrlTorneo = new CtrlTorneo();
				CtrlCombate ctrlCombate = new CtrlCombate();
				Personaje p1 = ctrlTorneo.getpersonaje(t.getIdUsuarioPersonaje());
				Personaje p2 = ctrlTorneo.getEnemigo(t.getId());
				ctrlCombate.seteaPer(p1, p2);
				request.getSession().setAttribute("CtrlCombate", ctrlCombate);
				request.getSession().setAttribute("P1", p1);
				request.getSession().setAttribute("P2", p2);
				request.getSession().setAttribute("nombreTurno", p1.getNombre());
				List<Ataque> ataques = ctrlCombate.getAtaquesOfPersonajeByEnergia(ctrlCombate.getIdPersonajeTurno(1), 1);
				request.getSession().setAttribute("ataques", ataques);
				request.getSession().setAttribute("turno", 1);
				response.sendRedirect("routes/Combate.jsp");
			}
		} catch (Exception e) {
			request.getSession().setAttribute("error", e.getMessage());
			response.sendRedirect("/WebPage/routes/MensajeError.jsp");
		}

	}

	public Torneo mapTournamentFromForm(HttpServletRequest request, int idUsuarioPersonaje) {
		Torneo torneo = new Torneo();
		torneo.setIdUsuarioPersonaje(idUsuarioPersonaje);
		return torneo;
	}

	public int getIdUsuario(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Usuario u = (Usuario) session.getAttribute("usuario");
		int idUsuario = Integer.parseInt(String.valueOf(u.getId()));
		return idUsuario;
	}

	public int getIdPersonaje(HttpServletRequest request) throws ApplicationException {
		if(request.getParameter("idPersonaje") == null) {
			throw new ApplicationException(new Exception(),"Debe elegir un personaje, de no tener personajes cree uno.");
		}
		int idPersonaje = Integer.parseInt(request.getParameter("idPersonaje"));
		return idPersonaje;
	}

}
