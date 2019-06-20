<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*"%>
<%@page import="entidades.Usuario"%>
<%@page import="entidades.Personaje"%>
<%@page import="logic.ControladorABMCPersonaje"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<title>Editar Personajes</title>
</head>
<body>
	<div class="container" id="app-editar">
		<% 
		Usuario u = (Usuario)session.getAttribute("usuario");  String nom = String.valueOf(u.getNombre());
		String ape = String.valueOf(u.getApellido());
		%> <p>Perfil: <%= nom %> <%= ape %> </p> 
		<% if(u == null){
			response.sendRedirect("index.jsp");
		} %>
		<% 
			ControladorABMCPersonaje ctrlPersonaje = new ControladorABMCPersonaje();
			ArrayList<Personaje> personajes_usuario = ctrlPersonaje.recuperarPersonajesDeUsuario(u.getId());
			request.setAttribute("personajes", personajes_usuario);
		%>
		<h1>Edición de personajes</h1>
		<select name="selectedAttacks" class="form-control col-md-12 h-50" v-model="attacks" multiple>
		  <c:forEach items="${personajes}" var="personaje">
	     	<option value="${personaje.id}">
	     		<c:out value="${personaje.nombre}"/>
	     	</option>    	
	      </c:forEach>
		</select>
	</div>
<script src="https://cdnjs.cloudflare.com/ajax/libs/vue/1.0.18/vue.min.js"></script>
<script>
edit = new Vue({
	 
})
</script>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>