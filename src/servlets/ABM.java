package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import entidades.*;
import logic.*;
import utils.ApplicationException;

/**
 * Servlet implementation class ABM
 */
@WebServlet("/ABM")
public class ABM extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ABM() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ControladorABMCPersonaje ctrl = new ControladorABMCPersonaje();
		if (request.getParameter("buscar")!=null) {
			String nom = request.getParameter("nombre1");
			
			Personaje p = new Personaje();
			try {
				p = ctrl.busca(nom);
			} catch (ApplicationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.getSession().setAttribute("per", p);
			request.getRequestDispatcher("WEB-INF/Abm.jsp").forward(request, response);
		}
		
		if (request.getParameter("agregar")!=null)
		{
			Personaje p = MapearDeFormulario(request);
			p.setPuntosTotales(200);
			
			try {
				ctrl.agregarPersonaje(p);
			} catch (ApplicationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.getRequestDispatcher("WEB-INF/Abm.jsp").forward(request, response);
		}
		
		if (request.getParameter("modificar")!=null)
		{
			Personaje p = MapearDeFormulario(request);
			ctrl.modificar(p);
			request.getRequestDispatcher("WEB-INF/Abm.jsp").forward(request, response);
		}
		
		if (request.getParameter("borrar")!=null)
		{
			Personaje p = MapearDeFormulario(request);
			ctrl.borrarPersonaje(p);
			request.getRequestDispatcher("WEB-INF/Abm.jsp").forward(request, response);
		}
	}

	public Personaje MapearDeFormulario(HttpServletRequest request) {
		Personaje p = new Personaje();
		p.setNombre(request.getParameter("nombre1"));
		p.setVida(Integer.parseInt(request.getParameter("vida1")));
		p.setEnergia(Integer.parseInt(request.getParameter("energia1")));
		p.setDefensa(Integer.parseInt(request.getParameter("defensa1")));
		p.setEvasion(Integer.parseInt(request.getParameter("evasion1")));
		return p;
	}

}
