<%@page import="entidades.Ataque"%>
<%@page import="entidades.Usuario"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Fight Club</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<style type="text/css">
body {
	background-color: #0072DD;
}

.contenedorMensaje {
	margin-top: 50px !important;
	width: 500px;
	height: 300px;
	margin: 0 auto;
	display: block;
	background-color: white;
	border: 1px solid black;
}

h1 {
	margin-top: 60px;
}

h1, h2 {
	color: red;
	text-align: center;
}
</style>

</head>
<body>
	<%
		Usuario u = (Usuario) session.getAttribute("usuario");
		if (u == null) {
			response.sendRedirect("/index.jsp");
		}
		String error = (String) session.getAttribute("error");
	%>
	<form method="post" action="${pageContext.request.contextPath}/Menu"
		id="menu">
		<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
			<a class="navbar-brand" href="#">Guerra!</a>
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
	<div class="contenedorMensaje">
		<h1>Ups!!</h1>
		<%
			if (error != null) {
		%>
		<h2 align="center">
			<c:out value="${error}" />
		</h2>
		<%
			}
		%>
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