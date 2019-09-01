package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Personaje;
import entidades.Usuario;
import logic.ControladorABMCPersonaje;
import utils.ApplicationException;

/**
 * Servlet implementation class Win
 */
@WebServlet("/Win")
public class Win extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Win() {
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

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			ControladorABMCPersonaje ctrlPersonaje = new ControladorABMCPersonaje();
			if (request.getParameter("next_combate") != null) {
				String mensaje = "Resumen de la pelea:\n\n";
				request.getRequestDispatcher("routes/IniciarCombate.jsp").forward(request, response);
			}

			if (request.getParameter("volver") != null) {
				request.getRequestDispatcher("routes/Menu.jsp").forward(request, response);
			}

			if (request.getParameter("guardar") != null) {
				request.getSession().setAttribute("playing", 1);
				request.getRequestDispatcher("routes/GuardarPartida.jsp").forward(request, response);
			}
			if (request.getParameter("editarAtaques") != null) {
				Personaje currentPersonaje = (Personaje) request.getSession().getAttribute("Personaje");
				;
				request.getSession().setAttribute("currentPersonaje", currentPersonaje);
				response.sendRedirect("routes/EditarAtaquesPersonaje.jsp");
			}
			if (request.getParameter("cancelar") != null) {
				request.getRequestDispatcher("routes/WinCombat.jsp").forward(request, response);
			}
			if (request.getParameter("guardarPersonaje") != null) {
				// Personaje per = this.mapCharacterFromForm(request);
				Personaje per = (Personaje) request.getSession().getAttribute("Personaje");

				int id_personaje = per.getId();
				String selected_attacks[] = request.getParameterValues("selectedAttacks");
				for (String id_attack : selected_attacks) {
					ctrlPersonaje.insertarPersonajeAtaque(id_personaje, Integer.parseInt(id_attack));
				}
				request.getRequestDispatcher("routes/WinCombat.jsp").forward(request, response);
			}
		} catch (ApplicationException e) {
			request.getSession().setAttribute("error", e.getMessage());
			response.sendRedirect("/WebPage/routes/MensajeError.jsp");
		}

	}

}
