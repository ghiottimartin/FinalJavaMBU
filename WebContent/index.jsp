<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Fight Club</title>

 	<!-- Bootstrap core CSS -->
    <link href="WebContent\WEB-INF\bootstrap.min.css" rel="stylesheet">

	<link href="C:\Users\Juan Lucas\git\Web\Web\build\css\signin.css" rel="stylesheet">
	
	<style type="text/css">
		html {
		    background-color: #0072DD;
		}
		.contenedorLogin img {
			margin: 0 auto;
			max-width: 150px;
			display: block;
			margin-top: 70px;
		}
		
		.contenedorLogin form {
			
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
		<img src="swords.png"alt="swords" />
		<h1 align ="center">Bienvenido a Fight Club</h1>
		<form method="post" action="Inicio" id="menu" class="form-signin">
			<div class="inputs">
				<div class="contenedorInput">
					<label>Usuario:</label>
					<input id="nombreUsuario" name="nombreUsuario" type="string" placeholder="Nombre de Usuario" value="" />
				</div>
				<div class="contenedorInput">
					<label>Contraseña:</label>
					<input id="password" name="password" type="string" placeholder="Contraseña" value="" />
				</div>
			</div>	
			<div class="botones">
				<button name="login" type="submit" class="btn btn-primary btn-lg">Login</button>
				<button name="registro" type="submit" class="btn btn-primary btn-lg">Registro</button>
			</div>
		</form>
	</div>
</body>
</html>