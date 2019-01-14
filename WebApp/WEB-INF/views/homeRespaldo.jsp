<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<%-- aggregating the jstl tag libraries to the view to be able to display the data from the model --%>	
<%-- import jstl library to be able to use core elements like loops, conditionals... in a jsp file --%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%-- import jstl library to be able to use format elements in a jsp file --%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%-- tag library to be able to use tag url in a jsp file: <spring:url /> --%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE html >
<html>
<head>
<!-- create relative route to the resources directory to assign to var="urlPublic" 
     it means to be able to use any file within /resources we have to use var="urlPublic -->
<spring:url value="/resources" var="urlPublic" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Pagina principal</title>
<!-- call the bootstrap file to be able to use it -->
<link rel="stylesheet" href="${ urlPublic }/bootstrap/css/bootstrap.min.css">

</head>

<body>
	<%--  
	<h1>Lista de peliculas</h1>
	<ol>
		<c:forEach items="${ films }" var="film"> <!-- var is a temporary variable that represents an element of the list -->
			<li>${ film }</li>
		</c:forEach>
	</ol>--%>

	<div class="panel panel-default">
		<div class="panel-heading">Tabla de peliculas</div>
		<div class="panel-body">
			<table class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th>Id</th>
						<th>Titulo</th>
						<th>Duracion</th>
						<th>Clasificacion</th>
						<th>Genero</th>
						<th>Imagen</th>
						<th>Fecha de estreno</th>
						<th>Status</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${ films }" var="film">
						<!-- var is a temporary variable that represents an element of the list -->
						<tr>
							<td>${ film.id }</td>
							<td>${ film.title }</td>
							<td>${ film.duration } minutos</td>
							<td>${ film.classification }</td>
							<td>${ film.gender }</td>
							<td><img src="${urlPublic}/images/${ film.image }" width="80" height="100"></td>
							<td><fmt:formatDate value="${ film.premierDate }" pattern="dd-MM-yyyy"/></td>
							<td>
								<c:choose>
									<c:when test="${ film.status == 'Activa' }">
										<span class="label label-success">ACTIVA</span>
									</c:when>
									<c:otherwise>
										<span class="label label-danger">INACTIVA</span>
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>