<%@page import="logic.CtrlCombate"%>
<%@page import="entidades.Personaje"%>
<%@page import="entidades.Usuario"%>
<%@page import="entidades.Ataque"%>
<%@page import="entidades.Torneo"%>
<%@page import="logic.CtrlTorneo"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Combate</title>
	
	
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">	
</head>

<body>
	<h1 class="headings-principal" align="center">Combate!</h1>
	<% 
		Personaje p1= ((Personaje)session.getAttribute("P1"));
		Personaje p2= ((Personaje)session.getAttribute("P2"));
		CtrlCombate combate = (CtrlCombate)session.getAttribute("CtrlCombate");
		int vida1,vida2,energia1,energia2;
		vida1 = combate.getVidaP1();
		vida2 = combate.getVidaP2();
		energia1 = combate.getEnergiaP1();
		energia2 = combate.getEnergiaP2();
		String nombrepersonaje = String.valueOf(p1.getNombre());
		String nombreEnemigo = String.valueOf(p2.getNombre());
		List<Ataque> ataques = (List<Ataque>)session.getAttribute("ataques");
		request.setAttribute("ataques", ataques);
	%>
	
	<form method="post" class="form-pers1" action="${pageContext.request.contextPath}/War">
	
	<div class="row">	
		<div class="col-md-4">
			<h2><%= nombrepersonaje %></h2>
			<label>Nombre</label>
		    <input name="nombre1" type="text"  class="form-control" disabled value="<%=p1.getNombre()%>">
			<br>
			<label>Vida</label>
		    <input name="vida1" type="text"  class="form-control" disabled value="<%=String.valueOf(vida1) %>">
			<br>
			<label>Energia</label>
		    <input name="energia1" type="text" class="form-control" disabled value="<%=String.valueOf(energia1) %>">
		    <br>
			<label>Defensa</label>
		    <input name="defensa1" type="text" class="form-control" disabled value="<%=p1.getDefensa() %>">
		    <br>
			<label>Evasion</label>
		    <input name="evasion1" type="text" class="form-control" disabled value="<%=p1.getEvasion() %>">
		    
		</div>
	
		<div class="col-md-4">
			<h2>Turno</h2>
			<br>
			<input name="nombreTurno" type="text" class="form-control" disabled value="<%=String.valueOf(session.getAttribute("nombreTurno")) %>" >
			<br>
			<h2>Elegir Ataque</h2>

		    <select name="idAtaque" class="form-control">
			  <c:forEach items="${ataques}" var="ataque">
		     	<option value="${ataque.id_ataque}">
		     		<c:out value="${ataque.nombre_ataque}"/>
		     	</option>    	
		      </c:forEach>
			</select>
			
			<br>
			<br>
			<button name="atacar" class="btn btn-primary btn-lg" type="submit">Atacar</button>
			<button name="defender" class="btn btn-lg btn-default" type="submit">Defender</button>
		</div>
	
		<div class="col-md-4">
			<h2><%= nombreEnemigo %></h2>
			<label>Nombre</label>
		    <input name="nombre1" type="text"  class="form-control" disabled value="<%=p2.getNombre()%>">
			<br>
			<label>Vida</label>
		    <input name="vida1" type="text"  class="form-control" disabled value="<%=String.valueOf(vida2) %>">
			<br>
			<label>Energia</label>
		    <input name="energia1" type="text" class="form-control" disabled value="<%=String.valueOf(energia2) %>">
		    <br>
			<label>Defensa</label>
		    <input name="defensa1" type="text" class="form-control" disabled value="<%=p2.getDefensa() %>">
		    <br>
			<label>Evasion</label>
		    <input name="evasion1" type="text" class="form-control" disabled value="<%=p2.getEvasion() %>">
			
		</div>
	</div>
	
	<div>
	<%
		if(request.getAttribute("evadido")!=null)
		{
			%>	<div>
					<h2><%=request.getAttribute("evadido") %></h2>					
				</div> <% 
		}
	%>
	</div>
	</form>

</body>
</html>