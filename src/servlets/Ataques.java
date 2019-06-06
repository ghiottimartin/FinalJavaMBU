package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Ataque;
import logic.ControladorABMAtaque;
import utils.ApplicationException;

/**
 * Servlet implementation class Ataques
 */
@WebServlet("/Ataques")
public class Ataques extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ControladorABMAtaque ctrlAtaque = new ControladorABMAtaque();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Ataques() {
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
		doPost(request, response);
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			if (request.getParameter("crearAtaque") != null) {
				Ataque ataque = new Ataque(String.valueOf(request.getParameter("nombre_ataque")),
						Integer.parseInt(request.getParameter("energia_requerida")));
				ctrlAtaque.add(ataque);	
				response.sendRedirect("/WebPage/routes/Ataques.jsp");
			}
			if(request.getParameter("volver") != null) {
				response.sendRedirect("/WebPage/routes/Ataques.jsp");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
