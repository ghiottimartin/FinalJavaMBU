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
.button {
	width: 370px;
	margin: 5px;
}

.jumbotron {
	display: flex;
	text-align: center;
	background-color: #0072DD;
}

.descripcionMenu p, .descripcionMenu h1 {
	color: white;
}

.descripcionMenu p {
	margin-left: 10px;
}

.contenedorMenu {
	display: flex;
	justify-content: center;
}

.contenedorMenu .item {
	display: flex;
	flex-direction: column;
	justify-content: space-between;
	border: 1px solid #0072DD;
	border-radius: 30px;
	text-align: center;
	margin: 10px;
	padding: 10px;
	height: 230px;
	align-items: center;
}

.contenedorMenu p {
	max-width: 550px;
	text-align: center;
}
</style>
</head>
<body>
	<%
		Usuario u = (Usuario) session.getAttribute("usuario");
		if (u == null) {
			response.sendRedirect("/WebPage/index.jsp");
		}
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
					<li class="nav-item dropdown my-2 my-sm-0"><i
						class="fa fa-user"></i> <%
 	if (u != null) {
 %> <a
						class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
						role="button" data-toggle="dropdown" aria-haspopup="true"
						aria-expanded="false"> <%=u.getNombreUsuario()%></a> <%
 	}
 %> <%
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

		<div class="jumbotron">
			<div class="container descripcionMenu">
				<h1 class="display-3">Bienvenido a Guerra!</h1>
				<p>Elija una de las opciones para comenzar</p>
			</div>
		</div>

		<div class="container">

			<div class="contenedorMenu">
				<div class="item columna">
					<h2>Agregar Personajes</h2>
					<p>Agregar personajes que son tuyos, para poder enfrentarte a
						los grandes villanos de Guerra!</p>
					<button name="loadCharacter" class="button btn btn-primary btn-lg">Agregar
						Personajes</button>
				</div>
				<div class="item columna">
					<h2>Torneo</h2>
					<p>Comience un torneo contra nuestros grandes villanos y
						comience a ganar experiencia con sus personajes.</p>
					<button name="torneo"
						class="button btn btn-primary btn-lg btn-block">Torneo</button>
				</div>
				<%
					if (u != null && u.getRol().equals("admin")) {
				%>
				<div class="item columna">
					<h2>Ataques</h2>
					<p>
						<strong>Función administradora.</strong> Agregar ataques para
						poder ser seleccionado por los usuarios en la creación de sus
						personajes.
					</p>
					<button name="ataques"
						class="button btn btn-primary btn-lg btn-block">Ataques</button>
				</div>
				<%
					}
				%>

			</div>

			<hr>

			<!--  <div class="d-flex flex-column align-items-center">
					<div class="p-2">
						<button name="loadCharacter" class="button btn btn-primary btn-lg">Agregar Personajes</button>
					</div>
					<div class="p-2">
						<button class="button btn btn-primary btn-lg btn-block">Torneo</button>
					</div>
			//if(u != null && u.getRol().equals("admin")) {				%>
					<div class="p-2">
						<button name="ataques" class="button btn btn-primary btn-lg btn-block">Ataques</button>
					</div>
			//}				%>
					<div class="p-2">
						<button name="exit" class="button btn btn-danger btn-lg btn-block">Salir</button>
					</div>	
				</div> -->
		</div>

	</form>


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