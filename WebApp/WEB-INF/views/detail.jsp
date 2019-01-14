<%-- aggregating the jstl tag libraries to the view to be able to display the data from the model --%>
<%-- tag library to be able to use tag url (urlPublic) in a jsp file --%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%-- import jstl library to be able to use core elements like loops, conditionals... in a jsp file --%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html lang="en">

	<!-- ==================================== HEAD ===================================== -->
	<head>
    	<meta charset="utf-8">
    	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    	<meta name="viewport" content="width=device-width, initial-scale=1">   
    	<meta name="description" content="Show the movies in cartelera in Cines Cito">
    	<meta name="author" content="Oscar Ruben Germade-Gonzalez Rios">
    	<title>Details</title>
	
		<!-- create relative route to the resources directory to assign to var="urlPublic" 
     	it means to be able to use any file within /resources we have to use var="urlPublic -->
		<spring:url value="/resources" var="urlPublic" />
		<spring:url value="/" var="urlRoot" />
	
		<!-- files to integrate bootstrap -->
    	<link href="${ urlPublic }/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    	<link href="${ urlPublic }/bootstrap/css/theme.css" rel="stylesheet">

  	</head>
  
 	<!-- ============================= BODY ===================================================== -->
	<body>

		<!-- +++++++++++++++++++++++++++++ Include the navbar file +++++++++++++++++++++++++++++++++++++++++++ -->
		<jsp:include page="includes/navbar.jsp"></jsp:include>
		
		<!-- +++++++++++++++++++++++++++ Main container ++++++++++++++++++++++++++++++++++++++++++++++ -->
		<div class="container theme-showcase" role="main">

			<!-- Marketing messaging -->
			<div class="container marketing">
				
				<!-- ----------------------------- Header: title of the film -->
				<div class="page-header">
					<h2>${ film.title }</h2>
				</div>
				
				<!-- ------------------------------------ Film details -------------------- -->
				<div class="row">
					<!-- Image -->
					<div class="col-sm-3">
						<p class="text-center">
							<img class="img-rounded" src="${ urlPublic }/images/${ film.image }" alt="${ film.title }" title="${ film.title }" width="155" height="220">            
						</p>
					</div>
					<!-- Details -->
					<div class="col-sm-9">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h3 class="panel-title">DETAILS</h3>
							</div>
							<div class="panel-body">                           
								<p>
									Title : ${ film.title } <br>
									Actors : ${ film.detail.actors }<br>
									Director: ${ film.detail.director }<br>                  
									Classification: ${ film.classification } <br>
									Duration: ${ film.duration } minutes <br>
									Gender: ${ film.gender } <br>                  
									Premier Date: ${ film.premierDate }                  
								</p> 
							</div>
						</div>                          
					</div>
				</div>
				
				<!-- ----------------------------------------- timetable -------------------- -->
				<div class="panel panel-default">
					<!-- header -->
					<div class="panel-heading">
						<h3 class="panel-title"><span class="label label-success">${ dateSelected }</span></h3>
					</div>
					<!-- body -->
					<div class="panel-body">
						<table class="table table-striped">
							<thead>
								<tr>                  
									<th>Time</th>
									<th>Room</th>                                  
									<th>Price</th>                                  
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${ times }" var="time">
									<tr>
										<td>${ time.time }</td>
										<td>${ time.room }</td>  
										<td>${ time.price } $</td> 
									</tr>
								</c:forEach>                        								
							</tbody>           
						</table>
					</div>
				</div>

				<!-- VIDEO: from youtube and sipnosis  -->
				<div class="row">
					<!-- VIDEO-->
					<div class="col-sm-7">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h3 class="panel-title">Trailer</h3>
							</div>
							<div class="panel-body">
								<iframe width="100%" height="315" src="${ film.detail.trailer }" ></iframe>
							</div>
						</div>           
					</div> 
					<!-- SINOPSIS -->
					<div class="col-sm-5">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h3 class="panel-title">SINOPSIS</h3>
							</div>
							<div class="panel-body">
								<p>${ film.detail.sinopsis }</p>
							</div>
						</div>                          
					</div>
				</div>

			</div><!-- /.container -->

			<hr class="featurette-divider">

			<!-- ++++++++++++++++++++ Include the footer file +++++++++++++++++++++++++++++++++++++ -->
	  		<jsp:include page="includes/footer.jsp"></jsp:include>

		</div> <!-- /end main container -->

		<!-- ++++++++++++++++++++++++++++ Bootstrap core JavaScript +++++++++++++++++++++++++++++ -->
    	<!-- Placed at the end of the document so the pages load faster -->
    	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script> <!-- better through cdn -->
    	<script src="${ urlPublic }/bootstrap/js/bootstrap.min.js"></script> 
	</body>
</html>
