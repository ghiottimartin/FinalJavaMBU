<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Fight Club</title>

<!-- Bootstrap core CSS -->
<link href="WebContent\WEB-INF\bootstrap.min.css" rel="stylesheet">

<link href="C:\Users\Juan Lucas\git\Web\Web\build\css\signin.css"
	rel="stylesheet">

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">

<style type="text/css">
body {
	background-color: #0072DD;
}

h1 {
	color: white;
}

p {
	text-align: center;
}

.contenedorLogin img {
	margin: 0 auto;
	max-width: 150px;
	display: block;
	margin-top: 70px;
}

.botones {
	margin: 0 auto;
	display: flex;
	flex-direction: row;
	align-items: center;
	justify-content: center;
}

.botones button {
	border-radius: 5px;
	border: none;
	padding: 8px;
	width: 150px;
	align-self: center;
}

.botones button:first-child {
	margin-right: 20px;
}

.inputs {
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
}

.inputs input {
	width: 100%;
	float: left;
	margin-bottom: 20px;
	padding: 4px;
}

label {
	float: left;
	width: 80px;
}

.contenedorInput {
	width: 300px;
}
</style>

</head>
<body>
	<div class="contenedorLogin">
		<img src="swords.png" alt="swords" />
		<h1 align="center">Bienvenido a Fight Club</h1>
		<form method="post" action="Inicio" id="menu" class="">
			<div class="inputs">
				<div class="contenedorInput">
					<input class="form-control" id="nombreUsuario" name="nombreUsuario"
						type="string" placeholder="Nombre de Usuario" value="" />
				</div>
				<div class="contenedorInput">
					<input class="form-control" id="password" name="password"
						type="password" placeholder="Contraseña" value="" />
				</div>
				<%
					if (session.getAttribute("usuario") == "erroneo") {
				%>
				<span style="color: red;"><p>Usuario y/o contraseña
						incorrectos</p></span>
				<%
					session.setAttribute("usuario", "erroneo2");
					}
				%>
			</div>
			<div class="botones">
				<button name="login" type="submit" class="btn btn-success">Login</button>
				<button name="registro" type="submit" class="btn btn-light">Registro</button>
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