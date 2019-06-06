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
<title>Ataques | Fight Club</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<style type="text/css">
body {
	background-color: #0072DD;
}

span {
	font-size: 20px;
}

td {
	color: white;
}
</style>
</head>
<body>
	<div id="app" class="container">
		<%
			ControladorABMAtaque ctrlAtaque = new ControladorABMAtaque();
			List<Ataque> ataques = ctrlAtaque.getAll();
			request.setAttribute("ataques", ataques);
			Usuario u = (Usuario)session.getAttribute("usuario");			
		%>

		<table class="table">
			<thead>
				<tr>
					<th scope="col">ID</th>
					<th scope="col">Nombre Ataque</th>
					<th scope="col">Energia</th>
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
						<td><a
							href="${pageContext.request.contextPath}/Ataques?edit=true&id=<c:out value="${ataque.id_ataque}" />">
								<button class="btn btn-success">Editar</button>
						</a> <a
							href="${pageContext.request.contextPath}/Ataques?erase=true&id=<c:out value="${ataque.id_ataque}" />">
								<button class="btn btn-danger">Borrar</button>
						</a></td>

					</tr>
				</c:forEach>

			</tbody>
		</table>
		<a href="/WebPage/routes/ABMAtaques/nuevoAtaque.jsp">
			<button class="btn btn-light">Agregar ataque</button>
		</a> <a href="/WebPage/routes/Menu.jsp">
			<button class="btn btn-light">Volver</button>
		</a>

	</div>
</body>
</html>

