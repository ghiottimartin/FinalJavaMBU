<%@page import="entidades.Personaje"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Combate</title>

	<link href="C:\Users\Juan Lucas\git\Web\Web\build\css\bootstrap.min.css" rel="stylesheet">

	<link href="C:\Users\Juan Lucas\git\Web\Web\build\css\signin.css" rel="stylesheet">
	
	<link href="C:\Users\Juan Lucas\git\Web\Web\build\css\Personajes.css" rel="stylesheet">
	
	<style type="text/css">
    <%@include file="bootstrap.min.css" %>
    <%@include file="signin.css" %>
    div.fixed {
    position: relative;
    top: 0px;
    left: 50px;
    width: 300px;
}
div.absolute {
    position: absolute;
    top: 100px;
    right: 50px;
    width: 300px;
}
div.acciones {
    position: absolute;
    top: 100px;
    left: 500px;
    width: 300px;
}
	</style>	
</head>

<body>
	<h1 class="headings-principal" align="center">Combate!</h1>

	
	<form method="post" class="form-pers1" action="War">
	<div class="fixed">
	
	<div class="acciones">
	<h2>Turno</h2>
	<br>
	<input name="nombreTurno" type="text" class="form-control" disabled value="<%=String.valueOf(session.getAttribute("nombreTurno")) %>" >
	<br>
	<h2>Energia</h2>
	<input name="energiaUsar" type="text" class="form-control">
	<br>
	<br>
	<button name="atacar" class="btn btn-primary btn-lg" type="submit">Atacar</button>
	<button name="defender" class="btn btn-lg btn-default" type="submit">Defender</button>
	</div>
	
	<%
		if(request.getAttribute("evadido")!=null)
		{
			%>	<div class="acciones">
					<h2><%=request.getAttribute("evadido") %></h2>					
				</div> <% 
		}
	%>
	</form>

</body>
</html>