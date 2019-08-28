<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*"%>
<%@page import="entidades.Usuario"%>
<%@page import="entidades.AtributosRolNivel"%>
<%@page import="entidades.Ataque"%>
<%@page import="entidades.Rol"%>
<%@page import="logic.ControladorABMAtaque"%>
<%@page import="logic.ControladorABMCPersonaje"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Agregar personaje</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<link
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
	rel="stylesheet"
	integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN"
	crossorigin="anonymous">
<style>
body {
	background-color: #0072DD;
}

h1 {
	text-align: center;
	margin-top: 30px;
}

.textoElija {
	text-align: center;
}

.fila {
	display: flex;
	flex-direction: row;
	justify-content: space-between;
	width: 100%;
}

.item {
	flex-grow: 1;
	padding: 10px;
}

label {
	margin-top: 10px;
}
.botones {
	display: flex;
	justify-content: center;
}

.botones button {
	width: 250px;
	margin-top: 20px; 
	margin-right: 10px;
}
</style>
</head>
<body>
	<div class="container" id="app">
		<%
			Usuario u = (Usuario) session.getAttribute("usuario");
			if (u == null) {
				response.sendRedirect("index.jsp");
			} else {
				String nom = String.valueOf(u.getNombre());
				String ape = String.valueOf(u.getApellido());

			} ;

			ControladorABMCPersonaje ctrlPersonaje = new ControladorABMCPersonaje();
			List<Rol> roles = ctrlPersonaje.getAllRoles();
			request.setAttribute("roles", roles);

			AtributosRolNivel atr = new AtributosRolNivel();
			if ((AtributosRolNivel) session.getAttribute("atributos") != null) {
				atr = (AtributosRolNivel) session.getAttribute("atributos");

			} ;

			Rol currentRol = (Rol) session.getAttribute("selectedRole");
		%>
		<h1>Creaci�n de personajes</h1>
		<p class="textoElija">Por favor elija primero el rol del
			personaje.</p>
		<form method="post"
			action="${pageContext.request.contextPath}/Personajes" id="register"
			class="">
			<div class="row">
				<div class="fila">
					<div class="item">
						<h3>Rol de personaje</h3>
						<label>Elija el rol que tendr� el personaje.</label>

						<%
							if (currentRol != null) {
						%><label>Su rol elegido es: <%=currentRol.getDescripcion_rol()%></label>
						<%
							}
						%>

						<div class="row">
							<div class="col-sm-10">
								<select name="selectedRole" class="form-control h-100"
									v-model="roles">
									<c:forEach items="${roles}" var="rol">
										<option value="${rol.id_rol}">
											<c:out value="${rol.descripcion_rol}" />
										</option>
									</c:forEach>
								</select>
							</div>
							<div class="col-sm-2 pull-right">
								<button name="selectRole" type="submit"
									class="btn btn-success h-100">
									<i class="fa fa-arrow-right"></i>
								</button>
							</div>
						</div>
					</div>
					<div class="item">
						<h3>Personaje</h3>
						<label>Nombre</label> <input class="form-control" name="nombre"
							type="text" placeholder="Nombre" value="" />
					</div>
				</div>
				<div class="fila">
					<div class="item">
						<label>Vida</label> <input class="form-control" name="vida"
							type="text" placeholder="Vida" v-model="life"
							value="<%=String.valueOf(atr.getVida())%>" disabled /> <label>Energia</label>
						<input class="form-control" name="energia" type="text"
							placeholder="Energia" v-model="energy"
							value="<%=String.valueOf(atr.getEnergia())%>" disabled />
					</div>
					<div class="item">
						<label>Defensa</label> <input class="form-control" name="defensa"
							type="text" placeholder="Defensa" v-model="defense"
							value="<%=String.valueOf(atr.getDefensa())%>" disabled /> <label>Evasi�n</label>
						<input class="form-control" name="evasion" type="text"
							placeholder="Evasion" v-model="evasion"
							value="<%=String.valueOf(atr.getEvasion())%>" disabled />
					</div>
				</div>
			</div>
			<div class="botones">
				<button name="agregarAtaques" type="submit" class="btn btn-info">Agregar
					ataques</button>
				<button name="cancelar" type="submit" class="btn btn-light">Cancelar</button>
			</div>
		</form>
	</div>




	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/vue/1.0.18/vue.min.js"></script>
	<script>
		vm = new Vue({
			el : '#app',
			data : {
				initialPoints : 100,
				life : 0,
				energy : 0,
				defense : 0,
				evasion : 0,
				attacks : [],
				roles : []
			},
			computed : {
				points : function() {
					return this.initialPoints - this.life - this.energy
							- this.defense - this.evasion;
				},
				selectedAttacks : function() {
					return this.attacks.length;
				}
			},
			methods : {
				prueba : function() {
					var someJsVar = "<c:out value='${ataques}'/>";
					return someJsVar;
				}
			}
		})
	</script>
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
		integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous"></script>
</body>
</html>