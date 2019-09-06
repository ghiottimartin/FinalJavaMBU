package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidades.Partida;
import entidades.Torneo;
import entidades.Usuario;
import logic.CtrlPartidas;
import logic.CtrlTorneo;
import utils.ApplicationException;

/**
 * Servlet implementation class Partidas
 */
@WebServlet("/Partidas")
public class Partidas extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Partidas() {
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			if (request.getParameter("volver") != null) {
				int playing = 0;
				if (request.getSession().getAttribute("playing") != null)
					playing = (int) request.getSession().getAttribute("playing");
				request.getSession().setAttribute("saved_game", null);
				request.getSession().setAttribute("playing", 0);
				if (playing == 1)
					request.getRequestDispatcher("routes/WinCombat.jsp").forward(request, response);
				else
					request.getRequestDispatcher("routes/Menu.jsp").forward(request, response);
			}

			if (request.getParameter("guardar") != null) {
				request.getSession().setAttribute("saved_game", 0);
				
				Torneo t = (Torneo) request.getSession().getAttribute("torneo");
				CtrlPartidas partidas = new CtrlPartidas();
				partidas.guardarPartida(t,request.getParameter("descripcion"),this.getIdUsuario(request));
				
				request.getSession().setAttribute("saved_game", 1);
				request.getRequestDispatcher("routes/GuardarPartida.jsp").forward(request, response);
			}

			if (request.getParameter("cargar") != null) {
				// Do something...
				CtrlPartidas partidas = new CtrlPartidas();
				int id_partida = Integer.parseInt(request.getParameter("selectedPartida"));
				// int idUsuario = getIdUsuario(request);
				int idUsuarioPersonaje = partidas.getIdUsuarioPersonajeFromTorneo(id_partida);
				int id_torneo = partidas.getIdTorneo(id_partida);

				if (idUsuarioPersonaje != 0) {
					Torneo t = new Torneo();
					t.setIdUsuarioPersonaje(idUsuarioPersonaje);
					t.setId(id_torneo);
					// ctrl.create(t);
					request.getSession().setAttribute("torneo", t);
					response.sendRedirect("routes/IniciarCombate.jsp");

				} else {
					request.getSession().setAttribute("error", "No se cargó el torneo");
					response.sendRedirect("/WebPage/routes/MensajeError.jsp");
				}
			}
		} catch (ApplicationException e) {
			request.getSession().setAttribute("error", e.getMessage());
			response.sendRedirect("/WebPage/routes/MensajeError.jsp");
		}
	}

	public int getIdUsuario(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Usuario u = (Usuario) session.getAttribute("usuario");
		int idUsuario = Integer.parseInt(String.valueOf(u.getId()));
		return idUsuario;
	}

	public int getIdPersonaje(HttpServletRequest request) {
		int idPersonaje = Integer.parseInt(request.getParameter("idPersonaje"));
		return idPersonaje;
	}

}
