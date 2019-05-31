<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Ataques | Fight Club</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<style type="text/css">
	    <%@include file="../WEB-INF/bootstrap.min.css" %>
	    <%@include file="../WEB-INF/signin.css" %>
	    body {
	    	background-color: #0072DD;
		}
		span {
			font-size: 20px;
		}
		td {
			color: white;
		}
	</style>
</head>
<body>
	<div id="app" class="container">
		<form method="post" action="${pageContext.request.contextPath}/Registro" id="register" class="">
			<table class="table">
			  <thead>
			    <tr>
			      <th scope="col">ID</th>
			      <th scope="col">Nombre Ataque</th>
			      <th scope="col">Energia</th>
			      <th scope="col">Acciones</th>
			    </tr>
			  </thead>
			  <tbody>
			    <tr>
			      <td scope="row">1</th>
			      <td>Kame-kame-ha</td>
			      <td>100</td>
			      <td>
			      	<button type="submit" class="btn btn-success">
						Editar
					</button>
			      	<button type="submit" class="btn btn-danger">
						Borrar
					</button>
			      </td>
			    </tr>
			    <tr>
			      <td scope="row">2</th>
			      <td>Henkidama</td>
			      <td>1000</td>
			      <td>
			      	<button type="submit" class="btn btn-success">
						Editar
					</button>
			      	<button type="submit" class="btn btn-danger">
						Borrar
					</button>
			      </td>
			    </tr>
			    <tr>
			      <td scope="row">3</th>
			      <td>Bolita de fuego</td>
			      <td>20</td>
			      <td>
			      	<button type="submit" class="btn btn-success">
						Editar
					</button>
			      	<button type="submit" class="btn btn-danger">
						Borrar
					</button>
			      </td>
			    </tr>
			  </tbody>
			</table>
			<button type="submit" class="btn btn-light">Agregar ataque</button>
		</form>
	</div>
	
	<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
	<script>
		var app = new Vue({
			  el: '#app',
			  data: {
			    ataques: [],
			  }
			})
	</script>
</body>
</html>

