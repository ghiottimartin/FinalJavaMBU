<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*"%>
<%@page import="entidades.Personaje"%>
<%@page import="entidades.Ataque"%>
<%@page import="entidades.Rol"%>
<%@page import="logic.ControladorABMAtaque"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Agregado de ataques</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
</head>
<body>
	<% 
		Personaje currPers = (Personaje)request.getSession().getAttribute("currentPersonaje"); 
		Rol currentRol = (Rol)request.getSession().getAttribute("selectedRole");
		ControladorABMAtaque ctrlAtaque = new ControladorABMAtaque();
		List<Ataque> ataques = ctrlAtaque.getAllByEnergy(currPers.getEnergia());
		request.setAttribute("ataques", ataques);
	%>
	<div class="container">
		<h1>Agregar ataques al personaje: <%= currPers.getNombre() %> </h1>
		<label>Recordar que su personaje es uno de tipo: <%= currentRol.getDescripcion_rol() %> </label>
		<form method="post" action="${pageContext.request.contextPath}/Personajes" id="personajes" class="">
			<h3>Elija ataques en base a la energia de su personaje, la cual es de <%= currPers.getEnergia() %> </h3>
			
			<div class="col-md-12">
				<h3>Ataques</h3>
			
			    <select name="selectedAttacks" class="form-control col-md-12 h-50" multiple>
				  <c:forEach items="${ataques}" var="ataque">
			     	<option value="${ataque.id_ataque}">
			     		<c:out value="${ataque.nombre_ataque}"/> - Requiere: <c:out value="${ataque.energia_requerida}"/> de energia
			     	</option>    	
			      </c:forEach>
				</select>
			</div>
			
			<br>
			
			<div class="row">
				<div class="col-md-12">
					<button name="guardarPersonaje" type="submit" class="btn btn-success">Aceptar</button>
					<button name="cancelar" type="submit" class="btn btn-light">Cancelar</button>
				</div>
			</div>
	
		</form>
		
	</div>


<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

</body>
</html>