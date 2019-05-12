<%@page import="logic.CtrlCombate"%>
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
	<% 
		Personaje p1= ((Personaje)session.getAttribute("P1"));
		Personaje p2= ((Personaje)session.getAttribute("P2"));
		CtrlCombate combate = (CtrlCombate)session.getAttribute("CtrlCombate");
		int vida1,vida2,energia1,energia2;
		vida1 = combate.getVidaP1();
		vida2 = combate.getVidaP2();
		energia1 = combate.getEnergiaP1();
		energia2 = combate.getEnergiaP2();
	%>
	
	<form method="post" class="form-pers1" action="War">
	<div class="fixed">
	<h2>Personaje 1</h2>
	<label>Nombre</label>
    <input name="nombre1" type="text"  class="form-control" disabled value="<%=p1.getNombre()%>">
	<br>
	<label>Vida</label>
    <input name="vida1" type="text"  class="form-control" disabled value="<%=String.valueOf(vida1)%>">
	<br>
	<label>Energia</label>
    <input name="energia1" type="text" class="form-control" disabled value="<%=String.valueOf(energia1) %>">
    <br>
	<label>Defensa</label>
    <input name="defensa1" type="text" class="form-control" disabled value="<%=String.valueOf(p1.getDefensa()) %>">
    <br>
	<label>Evasion</label>
    <input name="evasion1" type="text" class="form-control" disabled value="<%=String.valueOf(p1.getEvasion()) %>">
</div>

<div class="absolute">
	<h2>Personaje 2</h2>
	<label>Nombre</label>
    <input name="nombre2" type="text"  class="form-control" disabled value="<%=p2.getNombre() %>">
	<br>
	<label>Vida</label>
    <input name="vida2" type="text"  class="form-control" disabled value="<%=String.valueOf(vida2) %>">
	<br>
	<label>Energia</label>
    <input name="energia2" type="text" class="form-control" disabled value="<%=String.valueOf(energia2) %>">
    <br>
	<label>Defensa</label>
    <input name="defensa2" type="text" class="form-control" disabled value="<%=String.valueOf(p2.getDefensa()) %>">
    <br>
	<label>Evasion</label>
    <input name="evasion2" type="text" class="form-control" disabled value="<%=String.valueOf(p2.getEvasion()) %>">
	
	</div>
	
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