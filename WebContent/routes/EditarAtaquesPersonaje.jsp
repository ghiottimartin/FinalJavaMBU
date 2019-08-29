<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*"%>
<%@page import="entidades.Personaje"%>
<%@page import="entidades.Ataque"%>
<%@page import="entidades.Usuario"%>
<%@page import="entidades.Rol"%>
<%@page import="logic.ControladorABMAtaque"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Agregado de ataques</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<link
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
	rel="stylesheet"
	integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN"
	crossorigin="anonymous">
<style>
body {
	background-color: #0072DD;
}

h4 {
	color: white;
}

h1 {
	margin-top: 30px;
}
h1, h3 {
	color: white;
	text-align: center;
}
</style>
</head>
<body>
	<%
		Usuario u = (Usuario) session.getAttribute("usuario");
		if (u == null) {
			response.sendRedirect("index.jsp");
		}
		Personaje currPers = (Personaje) request.getSession().getAttribute("currentPersonaje");
		ControladorABMAtaque ctrlAtaque = new ControladorABMAtaque();
		List<Ataque> ataques = ctrlAtaque.getNewByEnergy(currPers.getEnergia(), currPers.getId());
		request.setAttribute("ataques", ataques);
	%>
	<form method="post" action="${pageContext.request.contextPath}/Menu"
		id="menu">
		<nav class="navbar navbar-expand-lg navbar-dark bg-dark"> <a
			class="navbar-brand" href="#">Guerra!</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item dropdown my-2 my-sm-0"><a
					class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
					role="button" data-toggle="dropdown" aria-haspopup="true"
					aria-expanded="false"> <%=u.getNombreUsuario()%>
				</a> <%
 	if (u != null && u.getRol().equals("admin")) {
 %>
					<div class="dropdown-menu" aria-labelledby="navbarDropdown">
						<div class="dropdown-item">
							<button name="ataques" class="btn btn-default btn-sm">Ataques</button>
						</div>
						<div class="dropdown-divider"></div>
						<div class="dropdown-item">
							<button name="exit" class="btn btn-danger btn-sm">Salir</button>
						</div>
					</div> <%
 	}
 %></li>
			</ul>
		</div>
		</nav>
	</form>
	<div class="container">
		<h1>
			Agregar ataques al personaje:
			<%=currPers.getNombre()%>
		</h1>
		<form method="post" action="${pageContext.request.contextPath}/Win"
			id="personajes" class="">
			<h3>
				Elija ataques en base a la energia de su personaje, la cual es de
				<%=currPers.getEnergia()%>
			</h3>

			<div style="padding-left: 0" class="col-md-12">
				<h4>Ataques</h3>
				<select name="selectedAttacks" class="form-control col-md-12 h-50"
					multiple>
					<c:forEach items="${ataques}" var="ataque">
						<option value="${ataque.id_ataque}">
							<c:out value="${ataque.nombre_ataque}" /> - Requiere:
							<c:out value="${ataque.energia_requerida}" /> de energia
						</option>
					</c:forEach>
				</select>
			</div>

			<br>

			<div class="row">
				<div class="col-md-12">
					<button name="guardarPersonaje" type="submit"
						class="btn btn-success">Aceptar</button>
					<button name="cancelar" type="submit" class="btn btn-light">Cancelar</button>
				</div>
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