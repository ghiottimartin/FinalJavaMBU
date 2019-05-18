package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.CtrlRegistro;
import entidades.Usuario;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * Servlet implementation class Registro
 */
@WebServlet("/Registro")
public class Registro extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Registro() {
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
		if(request.getParameter("registrar")!= null){
			CtrlRegistro ctrl = new CtrlRegistro();
			Usuario currentUser = this.mapUserFromForm(request);
			try {
				//hacer validaciones
				if(this.validateUser(currentUser, request.getParameter("passwordRepeated"))){
					ctrl.register(currentUser);
					response.sendRedirect("index.jsp");
				} else {
			      Cookie c = new Cookie("userName", request.getParameter("nombreUsuario"));
			      response.addCookie(c);
			      response.sendRedirect("routes/Registro.jsp");
			      
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if(request.getParameter("volver")!= null){
			response.sendRedirect("index.jsp");
		}
					
	}
	
	public Usuario mapUserFromForm(HttpServletRequest request) {
		Usuario user = new Usuario();
		
		user.setNombreUsuario(request.getParameter("nombreUsuario"));
		user.setPassword(request.getParameter("password"));
		user.setEmail(request.getParameter("email"));
		
		return user;
	}
	
	public boolean validateUser(Usuario user, String repeatedPassword) {
		Pattern pattern = Pattern
                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        String email = user.getEmail();
        Matcher mather = pattern.matcher(email);
        if (mather.find() == false) {
            return false;
        }
        if(user.getPassword() != repeatedPassword){
        	return false;
        }
		return true;
	}

}
