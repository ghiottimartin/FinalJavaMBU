package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Request;

import logic.CtrlRegistro;
import utils.ApplicationException;
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
			if (request.getParameter("registrar") != null) {
				CtrlRegistro ctrl = new CtrlRegistro();
				Usuario currentUser = this.mapUserFromForm(request);

				request.getSession().setAttribute("emailErroneo", "false");
				if (this.validateMail(currentUser.getEmail())) {
					request.getSession().setAttribute("emailErroneo", "true");
				}
				request.getSession().setAttribute("contraseñaNoCoincide", "false");
				if (this.validaContraseña(currentUser, request.getParameter("passwordRepeated"))) {
					request.getSession().setAttribute("contraseñaNoCoincide", "true");
				}
				if (!this.validateMail(currentUser.getEmail())
						&& !this.validaContraseña(currentUser, request.getParameter("passwordRepeated"))) {
					ctrl.register(currentUser);
					response.sendRedirect("index.jsp");
				} else {
					response.sendRedirect("routes/Registro.jsp");
				}

				/*
				 * } else { Cookie c = new Cookie("userName",
				 * request.getParameter("nombreUsuario")); response.addCookie(c);
				 * response.sendRedirect("routes/Registro.jsp");
				 * 
				 * }
				 */
			}

			if (request.getParameter("volver") != null) {
				response.sendRedirect("index.jsp");
			}
		} catch (ApplicationException e) {
			request.getSession().setAttribute("error", e.getMessage());
			response.sendRedirect("/WebPage/routes/MensajeError.jsp");
		}

	}

	public Usuario mapUserFromForm(HttpServletRequest request) {
		Usuario user = new Usuario();

		user.setNombreUsuario(request.getParameter("nombreUsuario"));
		user.setPassword(request.getParameter("password"));
		user.setEmail(request.getParameter("email"));
		user.setNombre(request.getParameter("nombre"));
		user.setApellido(request.getParameter("apellido"));

		return user;
	}

	public boolean validateMail(String email) {
		Pattern pattern = Pattern.compile(
				"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		Matcher mather = pattern.matcher(email);
		if (mather.find() == false) {
			return true;
		} else {
			return false;
		}
	}

	public boolean validaContraseña(Usuario user, String contraseña) {
		System.out.println(user.getPassword().compareTo(contraseña));
		if (user.getPassword().compareTo(contraseña) != 0) {
			return true;
		} else {
			return false;
		}
	}

}
