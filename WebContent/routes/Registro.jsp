<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="entidades.Usuario"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registro | Fight Club</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<style>
body {
	background-color: #0072DD;
	margin-top: 30px;
}

.container-limit {
	max-width: 1100px;
	margin: 0 auto;
}

h1 {
	text-align: center;
	color: black;
	margin-bottom: 20px;
}

label {
	color: black;
}

input {
	margin-bottom: 20px;
}

.botones {
	display: flex;
	justify-content: space-evenly;
	align-content: center;
	margin-top: 20px;
}

button {
	align-self: center;
	width: 150px;
	margin-bottom: 20px;
}

.columna {
	display: flex;
	flex-direction: column;
	justify-content: center;
}

.columna input {
	max-width: 300px;
}
</style>
</head>
<body>
	<div class="container container-limit">
		<form method="post"
			action="${pageContext.request.contextPath}/Registro" id="register"
			class="">
			<div class="col-lg-6 offset-lg-3">
				<%
					if (session.getAttribute("contraseñaNoCoincide") == "true") {
				%>
				<span style="color: red;"><p>Las contraseñas no
						coinciden.</p></span>
				<%
					session.setAttribute("contraseñaNoCoincide", "false");
					}
				%>
				<%
					if (session.getAttribute("emailErroneo") == "true") {
				%>
				<span style="color: red;"><p>El email no tiene formato de
						email.</p></span>
				<%
					session.setAttribute("emailErroneo", "false");
					}
				%>
				<h1>Registro de usuario</h1>
				<label>Nombre de usuario:</label>
				<%
					Cookie cookie = null;
					Cookie[] cookies = null;
					String username = null;

					// Get an array of Cookies associated with the this domain
					cookies = request.getCookies();

					if (cookies != null) {
						for (int i = 0; i < cookies.length; i++) {
							cookie = cookies[i];
							if (cookie.getName().equals("userName")) {
								username = cookie.getValue();
								break;
							}
						}
					}

					if (username != null) {
				%><input class="form-control" id="nombreUsuario"
					name="nombreUsuario" type="string"
					placeholder="Ingrese Nombre de Usuario" value="<%=username%>" />
				<%
					} else {
				%><input class="form-control" id="nombreUsuario"
					name="nombreUsuario" type="string"
					placeholder="Ingrese Nombre de Usuario" value="" />
				<%
					}
				%>
				<label>Nombre:</label> <input class="form-control" id="email"
					name="nombre" type="string" placeholder="Nombre" value="" /> <label>Apellido:</label>
				<input class="form-control" id="email" name="apellido" type="string"
					placeholder="Apellido" value="" /> <label>Email:</label> <input
					class="form-control" id="email" name="email" type="string"
					placeholder="Ingrese email" value="" /> <label>Contraseña:</label>
				<input class="form-control" id="password" name="password"
					type="password" placeholder="Ingrese Contraseña" value="" /> <label>Repita
					contraseña:</label> <input class="form-control" id="password"
					name="passwordRepeated" type="password"
					placeholder="Reingrese Contraseña" value="" />
				<div class="botones">
					<button name="registrar" type="submit" class="btn btn-success">Aceptar</button>
					<button name="volver" type="submit" class="btn btn-light">Cancelar</button>
				</div>

				<%
					if (username != null) {
				%><span style="color: red;"><p>Las contraseñas deben
						coincidir</p></span>
				<%
					}
				%>
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