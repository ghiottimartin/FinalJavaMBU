package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Personaje;
import entidades.Torneo;
import logic.CtrlCombate;
import logic.CtrlTorneo;
import logic.ControladorABMCPersonaje;
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
		CtrlTorneo ctrlTorneo = new CtrlTorneo();
		ControladorABMCPersonaje ctrlPersonaje = new ControladorABMCPersonaje();
		PrintWriter out = response.getWriter(); 
		if(request.getParameter("atacar")!= null){
			try {
				int energia = controlador.getEnergiaFromAtaque(Integer.parseInt(request.getParameter("idAtaque")));
				//if(controlador.ataque(Integer.parseInt(request.getParameter("energiaUsar")), turno))
				if(controlador.ataque(energia, turno))
				{
					if(turno == 1){
						request.getSession().setAttribute("nombreTurno", controlador.getPerTurno()); 					
						request.getSession().setAttribute("msg", "personaje: "+ String.valueOf(controlador.getPerTurno()));
						
						Torneo t = (Torneo) request.getSession().getAttribute("torneo");
						//Asignacion de experiencia
						Personaje p = (Personaje) request.getSession().getAttribute("P1");
						int cant_experiencia = controlador.getExperienciaFromCombate(ctrlTorneo.getIdCombateActivo(t.getId()));
						ctrlPersonaje.updateExperienciaPersonaje(p.getId(), cant_experiencia);
						
						//Subida de nivel
						int id_next_nivel = controlador.getNivelByExperiencia(cant_experiencia);
						ctrlPersonaje.levelUpPersonaje(p, id_next_nivel);
						int id_next_combate = ctrlTorneo.updateCombateActivo(t.getId());
						Personaje per = ctrlPersonaje.getById(p.getId());
						request.getSession().setAttribute("Personaje", per); 	
						if(id_next_combate == 0){
							//va a pantalla de ganaste torneo
							request.getRequestDispatcher("routes/Winner.jsp").forward(request, response);
						} else {
							//va a pantalla ganaste combate WinCombat.jsp
							request.getRequestDispatcher("routes/WinCombat.jsp").forward(request, response);
						}		
						
					}
					else {
						//tiene que ir a la pantalla de perdida
						request.getRequestDispatcher("routes/Loser.jsp").forward(request, response);
					}
					
				}else{
					if(controlador.isEvadido())
					{
						request.setAttribute("evadido", "El ataque fue evadido");
						controlador.setEvadido(false);
					}
					request.getSession().setAttribute("nombreTurno", controlador.getPerTurno());
					this.cambiaTurno();
					//request.getRequestDispatcher("WEB-INF/Combate.jsp").forward(request, response);
					request.getRequestDispatcher("routes/Combate.jsp").forward(request, response);
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
			request.getRequestDispatcher("routes/Combate.jsp").forward(request, response);
			
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
