<%@page import="utils.ApplicationException"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*"%>
<%@page import="entidades.Usuario"%>
<%@page import="entidades.Partida"%>
<%@page import="logic.CtrlPartidas"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cargar torneos</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<style>
body {
	background-color: #0072DD;;
}

h2 {
	color: white;
	text-align: center;
	margin-top: 30px;
}

.botones {
	display: flex;
	justify-content: center;
}

button {
	margin-top: 30px;
	margin-right: 10px;
}
</style>
</head>
<body>
	<%
		Usuario u = (Usuario) session.getAttribute("usuario");
		if (u == null) {
			response.sendRedirect("/WebPage/index.jsp");
		}
		try {
			CtrlPartidas ctrlPartidas = new CtrlPartidas();
			List<Partida> partidas = ctrlPartidas.getAllFromUser(u.getId());
			request.setAttribute("partidas", partidas);
		} catch (ApplicationException e) {
			request.getSession().setAttribute("error", e.getMessage());
			response.sendRedirect("/WebPage/routes/MensajeError.jsp");
		}
	%>
	<form method="post" action="${pageContext.request.contextPath}/Menu"
		id="menu">
		<nav class="navbar navbar-expand-lg navbar-dark bg-dark"> <a
			class="navbar-brand" href="${pageContext.request.contextPath}/routes/Menu.jsp">Guerra!</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item dropdown my-2 my-sm-0"> <%
 	if (u != null) {
 %> <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
					role="button" data-toggle="dropdown" aria-haspopup="true"
					aria-expanded="false"> <%=u.getNombreUsuario()%></a> <%
 	}
 %>
					<div class="dropdown-menu" aria-labelledby="navbarDropdown">
						<div class="dropdown-item">
							<button name="personaje" class="btn btn-default btn-sm">Crear
								Personaje</button>
						</div>
						<div class="dropdown-item">
							<button name="torneo" class="btn btn-default btn-sm">Torneos</button>
						</div>
						<%
							if (u != null && u.getRol().equals("admin")) {
						%>
						<div class="dropdown-item">
							<button name="ataques" class="btn btn-default btn-sm">Ataques</button>
						</div>
						<%
							}
						%>
						<div class="dropdown-divider"></div>
						<div class="dropdown-item">
							<button name="exit" class="btn btn-danger btn-sm">Salir</button>
						</div>
					</div></li>
			</ul>
		</div>
		</nav>
	</form>
	<h2>Seleccione la partida que desea cargar</h2>
	<div class="container">
		<form method="post"
			action="${pageContext.request.contextPath}/Partidas">
			<select name="selectedPartida" class="form-control col-md-12">
				<c:forEach items="${partidas}" var="partida">
					<option value="${partida.id_partida}">
						<c:out value="${partida.descripcion}" />
					</option>
				</c:forEach>
			</select>
			<%-- - Guardado: <c:out value="${partida.fecha_creacion.toString()}" --%>
			<div class="botones">
				<button name="cargar" class="btn btn-success" type="submit">Cargar
					partida</button>
				<button name="volver" class="btn btn-light" type="submit">Volver</button>
			</div>
		</form>
	</div>
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
		integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous"></script>
</body>
</html>