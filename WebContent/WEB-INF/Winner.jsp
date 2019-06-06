<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ganador</title>

	<link href="C:\Users\Juan Lucas\git\Web\Web\build\css\bootstrap.min.css" rel="stylesheet">

	<link href="C:\Users\Juan Lucas\git\Web\Web\build\css\signin.css" rel="stylesheet">
	
	<link href="C:\Users\Juan Lucas\git\Web\Web\build\css\Personajes.css" rel="stylesheet">
	
	<style type="text/css">
    <%@include file="bootstrap.min.css" %>
    <%@include file="signin.css" %>
	</style>	
</head>
<body>
		<h1 align="center">Gano el <%=session.getAttribute("msg") %></h1>
		<form method="post" action="Win">
			<button name="volver" class="btn btn-primary btn-lg" type="submit">Volver</button>
		</form>
</body>
</html>