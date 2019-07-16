<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*"%>
<%@page import="entidades.Usuario"%>
<%@page import="entidades.AtributosRolNivel"%>
<%@page import="entidades.Ataque"%>
<%@page import="entidades.Rol"%>
<%@page import="logic.ControladorABMAtaque"%>
<%@page import="logic.ControladorABMCPersonaje"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Agregar personaje</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
	<div class="container" id="app">
	<% 
		Usuario u = (Usuario)session.getAttribute("usuario");  
		if(u == null){
			response.sendRedirect("index.jsp");
		} else {
			String nom = String.valueOf(u.getNombre());
			String ape = String.valueOf(u.getApellido());

		};
		
	
			ControladorABMAtaque ctrlAtaque = new ControladorABMAtaque();
			List<Ataque> ataques = ctrlAtaque.getAll();
			request.setAttribute("ataques", ataques);
			ControladorABMCPersonaje ctrlPersonaje = new ControladorABMCPersonaje();
			List<Rol> roles = ctrlPersonaje.getAllRoles();
			request.setAttribute("roles", roles);
			
			AtributosRolNivel atr = new AtributosRolNivel();
			if((AtributosRolNivel)session.getAttribute("atributos") != null){
				atr = (AtributosRolNivel)session.getAttribute("atributos");
				
			}; 
		%>
		<h1>Hola, <%= atr.getVida() %> </h1>
		<h1>Creaci�n de personajes</h1>
		<form  method="post" action="${pageContext.request.contextPath}/Personajes" id="register" class="">
			<div class="row">
				<div class="col-md-6">
					<h3>Personaje</h3>
					<label>Usted tiene {{points}} puntos a otorgarle a su personaje, elija bien</label>
					
					<label>Nombre</label>
					<input class="form-control" name="nombre" type="text" placeholder="Nombre" value="" />
					<br>
					<label>Vida</label>
					<input class="form-control" name="vida" type="text" placeholder="Vida" v-model="life" value="<%=String.valueOf(atr.getVida()) %>" disabled/>
					<br>
					<label>Energia</label>
					<input class="form-control" name="energia" type="text" placeholder="Energia" v-model="energy" value="<%=String.valueOf(atr.getEnergia()) %>" disabled/>
					<br>
					<label>Defensa</label>
					<input class="form-control" name="defensa" type="text" placeholder="Defensa" v-model="defense" value="<%=String.valueOf(atr.getDefensa()) %>" disabled/>
					<br>
					<label>Evasi�n</label>
					<input class="form-control" name="evasion" type="text" placeholder="Evasion" v-model="evasion" value="<%=String.valueOf(atr.getEvasion()) %>" disabled/>
					<br>
					
				</div>
				<div class="col-md-6 ">
					<div class="col-md-12">
						<h3>Ataques</h3>
					
						<label>Elija los ataques iniciales con los {{points}} puntos restantes</label>
					    <select name="selectedAttacks" class="form-control col-md-12 h-50" v-model="attacks" multiple>
						  <c:forEach items="${ataques}" var="ataque">
					     	<option value="${ataque.id_ataque}">
					     		<c:out value="${ataque.nombre_ataque}"/> - Requiere: <c:out value="${ataque.energia_requerida}"/> de energia
					     	</option>    	
					      </c:forEach>
						</select>
						
						<div v-if="selectedAttacks != 0">
							<p>Usted seleccion� {{selectedAttacks}} ataques</p>
						</div>
					</div>
					
					<div class="col-md-12">
						<h3>Rol de personaje</h3>
						<label>Elija el rol que tendr� el personaje</label>
						<select name="selectedRole" class="form-control col-md-12 h-50" v-model="roles">
						  <c:forEach items="${roles}" var="rol">
					     	<option value="${rol.id_rol}">
					     		<c:out value="${rol.descripcion_rol}"/>
					     	</option>    	
					      </c:forEach>
						</select>
						<br>
						<button name="selectRole" type="submit" class="btn btn-success">Seleccionar rol</button>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<button name="guardarPersonaje" type="submit" class="btn btn-success">Aceptar</button>
					<button name="cancelar" type="submit" class="btn btn-light">Cancelar</button>
				</div>
			</div>
		</form>
	</div>




<script src="https://cdnjs.cloudflare.com/ajax/libs/vue/1.0.18/vue.min.js"></script>
<script>
vm = new Vue({
	  el: '#app',
	  data: {
		  initialPoints: 100,
		  life: 0,
		  energy: 0,
		  defense: 0,
		  evasion: 0,
		  attacks: [],
		  roles: []
	  },
	  computed: {
		  points: function(){
			  return this.initialPoints - this.life - this.energy - this.defense - this.evasion;
		  },
		  selectedAttacks: function(){
			  return this.attacks.length;
		  }
	  },
	  methods: {
		  prueba: function() {
			  var someJsVar = "<c:out value='${ataques}'/>";
			  return someJsVar;
		  }
	  }
})

</script>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>