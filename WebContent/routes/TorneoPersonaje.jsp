<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="entidades.Usuario"%>
<%@page import="entidades.Personaje"%>
<%@page import="logic.ControladorABMCPersonaje"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Menu | Fight Club</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<style>
.button {
	width: 300px;
}

body {
	background-color: #0072DD;
	margin: 30px 0 30px 0;
}

h1 {
	text-align: center;	
}

select {
	margin-top: 30px;
	margin-bottom: 30px;
}
</style>
</head>
<body>
	<div class="container">
		<form method="post" action="${pageContext.request.contextPath}/Torneo"
			id="tournament" class="">
			<%
				Usuario u = (Usuario) session.getAttribute("usuario");
				String nom = String.valueOf(u.getNombre());
				String ape = String.valueOf(u.getApellido());
			%>
			<h1>
				Hola,
				<%=nom%>
				<%=ape%>
			</h1>
			<%
				if (u == null) {
					response.sendRedirect("index.jsp");
				}

				ControladorABMCPersonaje ctrlPersonaje = new ControladorABMCPersonaje();
				List<Personaje> personajes = ctrlPersonaje.recuperarPersonajesDeUsuario(u.getId());
				request.setAttribute("personajes", personajes);
			%>

			<div class="d-flex flex-column align-items-center">
				<label>Elija a su personaje</label> <select name="idPersonaje"
					class="form-control col-md-12">
					<c:forEach items="${personajes}" var="personaje">
						<option value="${personaje.id}">
							<c:out value="${personaje.nombre}" />
						</option>
					</c:forEach>
				</select>
				<div class="botones">
					<button name="seleccionar" type="submit" class="btn btn-success">Seleccionar</button>
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