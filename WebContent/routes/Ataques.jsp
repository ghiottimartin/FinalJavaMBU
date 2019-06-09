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
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.0.0/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.css" />
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

.modal {
	padding: 20px;
	height: 200px;
}

.modal .cartel p, .modal .cartel button {
	margin: 0 auto;
	display: block;
}

.modal .cartel p {
	margin-top: 30px;
	margin-bottom: 30px;
}

.close-modal {
	top: 10px !important;
	right: 10px !important;
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
</style>
</head>
<body>
	<div id="app" class="container">
		<script type="text/javascript">
			function callServlet() {
				alert(confirm("HOLA"));
			}
		</script>
		<%
			ControladorABMAtaque ctrlAtaque = new ControladorABMAtaque();
			List<Ataque> ataques = ctrlAtaque.getAll();
			request.setAttribute("ataques", ataques);
			Usuario u = (Usuario) session.getAttribute("usuario");
			int idAtaqueABorrar = 0;
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
						<td><c:out value="${ataque.id_ataque}" />
							<div id="ex1" class="modal">
								<div class="cartel">
									<div class="row">
										<p class="mensajeBorrado">Seguro que desea borrar el
											ataque?</p>
									</div>
									<div class="row">
										<div class="contenedorBotones">

											<div class="itemLeft">
												<a
													style="color: transparent; text-decoration: none; margin-left: 30px; float: left;"
													href="${pageContext.request.contextPath}/Ataques?erase=true&id=<c:out value="${ataque.id_ataque}" />">
													<button name="borrarAtaque" style="text-decoration: none;"
														class="btn btn-success">Aceptar</button>
												</a>
											</div>
											<div class="itemRight">
												<a href="#" rel="modal:close"
													style="text-decoration: none; margin-left: 30px; float: left;">
													<button
														style="border: 1px solid black; text-decoration: none;"
														class="btn btn-light">Cancelar</button>
												</a>
											</div>
										</div>
									</div>
								</div>
							</div></td>
						<td><c:out value="${ataque.nombre_ataque}" /></td>
						<td><c:out value="${ataque.energia_requerida}" />
						<td>
						<td><a
							href="${pageContext.request.contextPath}/Ataques?edit=true&id=<c:out value="${ataque.id_ataque}" />">
								<button class="btn btn-success">Editar</button>
						</a> <a href="${pageContext.request.contextPath}/Ataques?erase=true&id=<c:out value="${ataque.id_ataque}" />">
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
	<script language="javascript" type="text/javascript">
		borrarAtaque(idAtaque)
		{
			alert(idAtaque);
		}
	</script>
</body>
</html>

