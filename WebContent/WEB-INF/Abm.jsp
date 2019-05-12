<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="entidades.Personaje" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Opciones del personaje</title>
</head>
<body>
	<%
			Personaje p = (Personaje)session.getAttribute("per");		
	%>
	<form method="post" class="form-pers1" action="ABM">
	<div class="personaje">
	<h2>Personaje</h2>
	<br>
	<label>ID</label>
	<input name="id" type="text" class="form-controlp1" disabled <% if(p!=null)
    																	{
    																		%>value="<%=String.valueOf(p.getId())%>"<% 
    																	}else{
    																		%>value=""<%
    																	}
    																	%>>     
	<button name="buscar" class="btn btn-primary btn-lg" type="submit">Buscar</button>
	<br>
	<label>Nombre</label>
    <input name="nombre1" type="text"  class="form-controlp1"  <% if(p!=null)
    																	{
    																		%>value="<%=p.getNombre()%>"<% 
    																	}else{
    																		%>value=""<%
    																	}
    																	%>>
	<br>
	<label>Vida</label>
    <input name="vida1" type="text"  class="form-controlp1"  <% if(p!=null)
    																	{
    																		%>value="<%=String.valueOf(p.getVida())%>"<% 
    																	}else{
    																		%>value=""<%
    																	}
    																	%>>
	<br>
	<label>Energia</label>
    <input name="energia1" type="text" class="form-controlp1"  <% if(p!=null)
    																	{
    																		%>value="<%=String.valueOf(p.getEnergia())%>"<% 
    																	}else{
    																		%>value=""<%
    																	}
    																	%>>
    <br>
	<label>Defensa</label>
    <input name="defensa1" type="text" class="form-controlp1"  <% if(p!=null)
    																	{
    																		%>value="<%=String.valueOf(p.getDefensa())%>"<% 
    																	}else{
    																		%>value=""<%
    																	}
    																	%>>
    <br>
	<label>Evasion</label>
    <input name="evasion1" type="text" class="form-controlp1"  <% if(p!=null)
    																	{
    																		%>value="<%=String.valueOf(p.getEvasion())%>"<% 
    																	}else{
    																		%>value=""<%
    																	}
    																	%>>
    <label>Puntos totales</label>
    <input name="evasion1" type="text" class="form-controlp1"  <% if(p!=null)
    																	{
    																		%>value="<%=String.valueOf(p.getPuntosTotales())%>"<% 
    																	}else{
    																		%>value=""<%
    																	}
    																	%>>
	</div>
	
	<div class="acciones">
	<button name="agregar" type="submit">Agregar</button>
	<button name="modificar" type="submit">Modificar</button>
	<button name="borrar" type="submit">Borrar</button>
	</div>
	
	</form>
</body>
</html>