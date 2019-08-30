<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="logic.ControladorABMAtaque"%>
<%@page import="java.util.*"%>
<%@page import="entidades.Ataque"%>
<%@page import="entidades.Usuario"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
}

.container {
	margin-top: 30px;
}

span {
	font-size: 20px;
}

td, th {
	color: white;
}

.contenedorBotones {
	width: 100%;
}

.itemLeft {
	float: left;
	margin-left: 30px;
}

.itemRight {
	float: right;
	margin-right: 30px;
}

.contenedorBotones button {
	margin: 0 auto;
	display: block;
}

h1 {
	color: white;
}

.modal-footer {
	border: none;
	display: flex;
	justify-content: center;
}

.modal-header {
	border: none;
}

.modal-content {
	margin-top: 200px;
}
</style>
</head>
<body>
	<%
		Usuario u = (Usuario) session.getAttribute("usuario");
		if (u == null) {
			response.sendRedirect("index.jsp");
		}
		ControladorABMAtaque ctrlAtaque = new ControladorABMAtaque();
		List<Ataque> ataques = ctrlAtaque.getAll();
		request.setAttribute("ataques", ataques);
		if (u == null) {
			response.sendRedirect("index.jsp");
		}
		int idAtaqueABorrar = 0;
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
	<!-- Modal -->
	<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">¿Realmente
						desea borrar el ataque?</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-danger" data-dismiss="modal">Si</button>
					<button type="button" data-dismiss="modal" class="btn btn-success">No</button>
				</div>
			</div>
		</div>
	</div>
	<div class="container">
		<h1>ABM Ataques</h1>
		<table class="table">
			<thead>
				<tr>
					<th scope="col">ID</th>
					<th scope="col">Nombre Ataque</th>
					<th scope="col">Energia</th>
					<th scope="col"></th>
					<th scope="col">Acciones</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${ataques}" var="ataque">
					<tr>
						<td><c:out value="${ataque.id_ataque}" /></td>
						<td><c:out value="${ataque.nombre_ataque}" /></td>
						<td><c:out value="${ataque.energia_requerida}" />
						<td>
						<td style="display:flex;"><a
							href="${pageContext.request.contextPath}/Ataques?edit=true&id=<c:out value="${ataque.id_ataque}" />">
								<button style="margin-right:10px" class="btn btn-success">Editar</button>
						</a>
							<form method="post"
								action="${pageContext.request.contextPath}/Ataques" id="menu">
								<a data-toggle="modal" data-target="#exampleModal">
									<button class="btn btn-danger">Borrar</button> <input
									type="submit" class="ataqueABorrar" name="ataqueABorrar"
									value="Borrar${ataque.id_ataque}" />
								</a>
							</form></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<a href="/WebPage/routes/ABMAtaques/nuevoAtaque.jsp">
			<button class="btn btn-success">Agregar ataque</button>
		</a> <a href="/WebPage/routes/Menu.jsp">
			<button class="btn btn-light">Volver</button>
		</a>
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