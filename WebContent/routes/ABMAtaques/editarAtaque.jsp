<%@page import="entidades.Ataque"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Fight Club</title>

<!-- Bootstrap core CSS -->
<link href="WebContent\WEB-INF\bootstrap.min.css" rel="stylesheet">

<link href="C:\Users\Juan Lucas\git\Web\Web\build\css\signin.css"
	rel="stylesheet">

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">

<style type="text/css">
body {
	background-color: #0072DD;
	margin-top: 100px;
}

.contenedorLogin img {
	margin: 0 auto;
	max-width: 150px;
	display: block;
	margin-top: 70px;
}

.botones {
	margin: 0 auto;
	display: flex;
	flex-direction: row;
	align-items: center;
	justify-content: center;
}

.botones button {
	border-radius: 5px;
	border: none;
	padding: 8px;
	width: 150px;
	align-self: center;
}

.botones button:first-child {
	margin-right: 20px;
}

.inputs {
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
}

.inputs input {
	width: 100%;
	float: left;
	margin-bottom: 20px;
	padding: 4px;
}

label {
	float: left;
	width: 80px;
}

.contenedorInput {
	width: 300px;
}
</style>

</head>
<body>
	<%
		Ataque ataque = (Ataque) session.getAttribute("ataque");
		session.setAttribute("ataque",ataque);
	%>
	<div class="contenedorLogin">
		<h1 align="center">Editar Ataque</h1>
		<form method="post"
			action="${pageContext.request.contextPath}/Ataques" id="menu"
			class="">
			<div class="inputs">
				<div class="contenedorInput">
					<input class="form-control" id="nombre_ataque" name="nombre_ataque"
						type="string" placeholder="Nombre Ataque" value="<c:out value="${ataque.nombre_ataque}"/>"/>
				</div>
				<div class="contenedorInput">
					<input class="form-control" id="energia_requerida"
						name="energia_requerida" type="number"
						placeholder="Energia Requerida" value="<c:out value="${ataque.energia_requerida}"/>">
				</div>
			</div>
			<div class="botones">
				<button name="editarAtaque" class="btn btn-light btn-lg">Editar</button>
				<button name="volver" type="submit" class="btn btn-success btn-lg">Volver</button>
			</div>
		</form>
	</div>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/vue/1.0.18/vue.min.js"></script>
<script>
vm = new Vue({
	  el: '#app',
	  data: {
		  nombre_ataque: 'Hola',
		  energia_ataque: 100,
	  },
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