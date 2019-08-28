<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="entidades.Usuario"%>
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
body {	
	background-color: #0072DD;
	margin: 30px 0 30px 0;
}

h1 {
	text-align: center;
}

button {
	width: 300px;
	background-color: #007bff;
}

.formulario {
	margin: 0 auto;
	display: block;
	margin-top: 30px;
}

.botones {
	margin-top: 50px;
}
</style>
</head>
<body>
	<div class="container">
		<form method="post" action="${pageContext.request.contextPath}/Torneo"
			id="tournament" class="formulario">
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
			%>

			<div class="botones s d-flex flex-column align-items-center">
				<div class="p-2">
					<button name="nuevo" type="submit"
						class="btn btn-light btn-lg">Crear nuevo Torneo</button>
				</div>
				<div class="p-2">
					<button name="cargar" type="submit"
						class="btn btn-light btn-lg">Cargar partida</button>
				</div>
				<div class="p-2">
					<button name="volver" type="submit"
						class="btn btn-success">Volver</button>
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