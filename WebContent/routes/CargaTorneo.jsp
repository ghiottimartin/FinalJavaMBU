<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*"%>
<%@page import="entidades.Usuario"%>
<%@page import="entidades.Partida"%>
<%@page import="logic.CtrlPartidas"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cargar torneos</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
	<% 
		Usuario u = (Usuario)session.getAttribute("usuario");  
		if(u == null){
			response.sendRedirect("index.jsp");
		} else {
			String nom = String.valueOf(u.getNombre());
			String ape = String.valueOf(u.getApellido());

		};
			
		CtrlPartidas ctrlPartidas = new CtrlPartidas();
		List<Partida> partidas = ctrlPartidas.getAllFromUser(u.getId());
		request.setAttribute("partidas", partidas);
	%>
	<h2>Seleccione la partida que desea cargar</h2>
	<div class="container">
		<form method="post" action="Partidas">
			<select name="selectedPartida" class="form-control col-md-12">
			  <c:forEach items="${partidas}" var="partida">
		     	<option value="${partida.id_partida}">
		     		<c:out value="${partida.descripcion}"/>
		     	</option>    	
		      </c:forEach>
			</select>
			<%-- - Guardado: <c:out value="${partida.fecha_creacion.toString()}" --%>
			<button name="cargar" class="btn btn-primary btn-lg" type="submit">Cargar partida</button>
			<button name="volver" class="btn btn-default btn-lg" type="submit">Volver</button>
		</form>
	</div>
	


<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>