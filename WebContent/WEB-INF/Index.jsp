<%@page import="logic.ControladorABMCPersonaje"%>
<%@page import="java.util.*"%>
<%@page import="entidades.Personaje"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
  <head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    
    <title>Inicio</title>

    <!-- Bootstrap core CSS -->
    <link href="C:\Users\Juan Lucas\git\Web\Web\build\css\bootstrap.min.css" rel="stylesheet">

	<link href="C:\Users\Juan Lucas\git\Web\Web\build\css\signin.css" rel="stylesheet">
	
	<style type="text/css">
    <%@include file="bootstrap.min.css" %>
    <%@include file="signin.css" %>
	</style>	


  </head>

  <body>
  	<%
  		ControladorABMCPersonaje ctrl = new ControladorABMCPersonaje();
  		ArrayList<Personaje> personajes = ctrl.getAll();
  	%>

    <div class="container">

      <form method="post" class="form-signin" action="Start">
        <h2 class="form-signin-heading">Elija los personajes</h2>
        <br> 
        <label for="inputEmail" class="sr-only">Personaje 1</label>
        <select id="personaje1" name="Personaje1" class="form-control">
        <%
        	if(personajes!=null)
        	{
        		for(Personaje p:personajes)
        		{
        			%>
        			<option value="<%=p.getNombre()%>"><%=p.getNombre()%></option>
        			<%
        		}
        	}
        %>
        </select>
        <br>  
        <br>      
        <label for="inputPassword" class="sr-only">Personaje 2</label>
          <select id="personaje2" name="Personaje2" class="form-control">
        <%
        	if(personajes!=null)
        	{
        		for(Personaje p:personajes)
        		{
        			%>
        			<option value="<%=p.getNombre()%>"><%=p.getNombre()%></option>
        			<%
        		}
        	}
        %>
        </select>
		<br> 
		<br> 
        <button class="btn btn-lg btn-default" type="submit">Pelear!</button>
      </form>

    </div> <!-- /container -->


    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>
  </body>
</html>