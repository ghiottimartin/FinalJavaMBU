package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.CtrlCombate;
import utils.ApplicationException;

/**
 * Servlet implementation class Combate
 */
@WebServlet("/War")
public class War extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private int turno=1;
    private CtrlCombate controlador ;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public War() {
        super();

        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		controlador = (CtrlCombate)request.getSession().getAttribute("CtrlCombate");
		PrintWriter out = response.getWriter(); 
		if(request.getParameter("atacar")!= null){
			try {
				if(controlador.ataque(Integer.parseInt(request.getParameter("energiaUsar")), turno))
				{
					request.getSession().setAttribute("nombreTurno", controlador.getPerTurno()); 					
					request.getSession().setAttribute("msg", "personaje: "+ String.valueOf(controlador.getPerTurno()));
					request.getRequestDispatcher("WEB-INF/Winner.jsp").forward(request, response);
				}else{
					if(controlador.isEvadido())
					{
						request.setAttribute("evadido", "El ataque fue evadido");
						controlador.setEvadido(false);
					}
					request.getSession().setAttribute("nombreTurno", controlador.getPerTurno());
					this.cambiaTurno();
					request.getRequestDispatcher("WEB-INF/Combate.jsp").forward(request, response);
				}
			} catch (NumberFormatException e) {
				out.println("<script type=\"text/javascript\">alert('Error en la energia');</script>");
			} catch (ApplicationException e) {
				// TODO Auto-generated catch block
				out.println("<script type=\"text/javascript\">alert('Error en la energia');</script>");
			}
		}
		
		if(request.getParameter("defender")!= null) {
			
			controlador.defensa(turno);			
			request.getSession().setAttribute("nombreTurno", controlador.getPerTurno());
			this.cambiaTurno();
			request.getRequestDispatcher("WEB-INF/Combate.jsp").forward(request, response);
			
		}
		
		
	}
	

	private void cambiaTurno() {
		if (turno == 1) 
		{
			turno = 2;
		}
		else 
		{
			turno = 1;
		}
		
	}

}
