<%@page import="entidades.Personaje"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ganaste el combate!</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">	
</head>
<body>
	<% 
		Personaje p = ((Personaje)session.getAttribute("Personaje"));

	%>
	<h1 align="center">Ganó el combate  <%= p.getNombre() %></h1>
	</br>
	<label>Tu nivel es:  <%= p.getId_nivel() %></label>
	</br>
	<label>Tu Vida es:  <%= p.getVida() %></label>
	</br>
	<label>Tu Energia es:  <%= p.getEnergia() %></label>
	</br>
	<label>Tu Defensa es:  <%= p.getDefensa() %></label>
	</br>
	<label>Tu Evasion es:  <%= p.getEvasion() %></label>
	<form method="post" action="Win">
		<button name="next_combate" class="btn btn-primary btn-lg" type="submit">Siguiente combate</button>
		<button name="guardar" class="btn btn-success btn-lg" type="submit">Guardar partida</button>
	</form>
</body>
</html>