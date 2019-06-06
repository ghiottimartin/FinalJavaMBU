<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="entidades.Usuario"%>
<%@page import="entidades.Torneo"%>
<%@page import="entidades.Personaje"%>
<%@page import="logic.CtrlTorneo"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Menu | Fight Club</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<style>
	.button {
		width: 300px;
	}
</style>
</head>
<body>
	<div class="container">
	<form method="post" action="${pageContext.request.contextPath}/Torneo" id="tournament" class="">		
		<% 
		Usuario u = (Usuario)session.getAttribute("usuario");  String nom = String.valueOf(u.getNombre());
		String ape = String.valueOf(u.getApellido());
		Torneo t = (Torneo)session.getAttribute("torneo");
		int idUsuarioPersonaje = Integer.valueOf(t.getIdUsuarioPersonaje());
		CtrlTorneo ctrlTorneo = new CtrlTorneo();
		Personaje p = ctrlTorneo.getpersonaje(idUsuarioPersonaje);
		%> <h1>Hola, <%= nom %> <%= ape %> </h1> 
		<% if(u == null){
			response.sendRedirect("index.jsp");
		} %>
		
		<div class="d-flex flex-column align-items-center">
 			<label>Esta a punto de iniciar el combate</label>
			<div class="botones">
				<button name="iniciar" type="submit" class="btn btn-success">Iniciar</button>
			</div>
		</div>
	</form>	
	</div>
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>