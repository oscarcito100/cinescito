<%-- tag library to be able to use tag url (urlPublic) in a jsp file --%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<!DOCTYPE html>
<html lang="en">
<!-- ==================================== HEAD ===================================== -->
	<head>
    	<meta charset="utf-8">
    	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    	<meta name="viewport" content="width=device-width, initial-scale=1">   
    	<meta name="description" content="500 (Internal Server Error)">
    	<meta name="author" content="Oscar Ruben Germade-Gonzalez Rios">
    	<title>500 (Internal Server Error)</title>
    	
		<!-- create relative route to the resources directory to assign to var="urlPublic" 
     	it means to be able to use any file within /resources we have to use var="urlPublic -->    	
		<spring:url value="/resources" var="urlPublic" />

		<!-- files to integrate bootstrap -->		
		<link href="${urlPublic}/bootstrap/css/bootstrap.min.css" rel="stylesheet">	
   		<link href="${urlPublic}/bootstrap/css/theme.css" rel="stylesheet">	
	</head>
  	<!-- ============================= BODY ========================================== -->
<body>

	<jsp:include page="../includes/navbar.jsp"></jsp:include>
	
	<div class="container theme-showcase" role="main">

		<div class="panel panel-danger">
			<div class="panel-heading">
				<h3 class="panel-title">Error 500</h3>
			</div>
			<div class="panel-body">
				<img src="${urlPublic}/images/error.png" width="48" height="48" class="center">
				<h1>Sorry!!</h1>
				<h4>Internal Server Error!</h4>
				<div class="alert alert-danger" role="alert">
					<h4><span class="label label-warning">Description of Error:</span></h4>
					${pageContext.exception.message}
				</div>		
				<br>
				<button class="btn btn-success" onclick="goBack()">Back</button>
			</div>
		</div>

		<jsp:include page="../includes/footer.jsp"></jsp:include>		

	</div> <!-- /container -->

	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="${urlPublic}/bootstrap/js/bootstrap.min.js"></script>
		
	<script>
		function goBack() {
		    window.history.back();
		}
	</script>
	
</body>
</html>