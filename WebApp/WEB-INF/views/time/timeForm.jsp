<%-- tag library to be able to use tag url (urlPublic) in a jsp file --%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%-- tag library to be able to use form elements in a jsp file --%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%-- import jstl library to be able to use core elements in a jsp file --%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html lang="en">
  <!-- ==================================== HEAD ===================================== -->
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">   
    <meta name="description" content="Form to add the schedule of a movie">
    <meta name="author" content="Oscar Ruben Germade-Gonzalez Rios">
    <title>Adding the schedule of a movie</title>

	<!-- create relative route to the resources directory to assign to var="urlPublic" 
     it means to be able to use any file within /resources we have to use var="urlPublic -->
	<spring:url value="/resources" var="urlPublic" />
	<spring:url value="/time/save" var="urlForm" />
	
    <!-- files to integrate bootstrap and jquery -->
    <link href="${ urlPublic }/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="${ urlPublic }/bootstrap/css/theme.css" rel="stylesheet">
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

  </head>
  
  <!-- ============================= BODY ================================================ -->
  <body>

    <!-- +++++++++++++++++++++++++++++ Include the NAVBAR file +++++++++++++++++++++++++++ -->
	<jsp:include page="../includes/navbar.jsp"></jsp:include>

	<!-- +++++++++++++++++++++++++++ Main container ++++++++++++++++++++++++++++++++++++++ -->
	<div class="container theme-showcase" role="main">
	  <!-- ------------------------------------ PAGE HEADER ------------------------- -->
	  <h3 class="blog-title"><span class="label label-success">Data of the schedule</span></h3>  
	  
	  <!-- DISPLAY ERRORS IN THE FORM that happen with the data binding -->
	  <spring:hasBindErrors name="time"> <!-- name: is the name of the parameter model object of the method save() -->
	     <div class="alert alert-danger" role="alert"> 
	       Please, correct the next mistakes:
	       <ul> 
	         <c:forEach var="error" items="${ errors.allErrors }"> <!-- return a list with all errors -->
	           <li><spring:message message="${ error }" /></li> <!-- error: every error of the object -->
	         </c:forEach>
	       </ul> 
	     </div> 
	  </spring:hasBindErrors>
	  
	  <!-- ------- MESSAGE: successful message after saving a A TIME FORM if the 'message' attribute exist -->
      <c:if test="${ message != null }">
      	<div class='alert alert-success' role="alert">${ message }</div>
      </c:if>
      		
	  <!-- FORM: -->
	  <form:form action="${ urlForm }" method="POST" modelAttribute="time">
		  <!-- 1 Row -->
		  <div class="row"> 
		  
		    <!-- CHOOSE FILM -->        
			<div class="col-sm-3">
			  <div class="form-group">
				<label for="idPelicula" class="control-label">Film</label>              
				<form:select id="idPelicula" path="film.id" class="form-control" items="${ films }" itemValue="id" itemLabel="title">
				</form:select>             
			  </div> 
			</div>
		  </div>
		  
		  <!-- 2 row -->
		  <div class="row">
		  
		    <!-- DATE --> 
			<div class="col-sm-3">
			  <div class="form-group">
				<label for="fecha">Date</label>             
				<form:input type="text" class="form-control" path="date" id="fecha" required="required"/>
			  </div> 			   
			</div>
			
			<!-- TIME -->
			<div class="col-sm-3">
			  <div class="form-group">
				<label for="hora">Time</label>
				<form:input type="text" class="form-control" path="time" id="hora" placeholder="Format: HH:mm Exemple 21:30" required="required"/>
			  </div>  
			</div>
			
		    <!-- ROOM -->
			<div class="col-sm-3">
			  <div class="form-group">
				<label for="sala" class="control-label">Room</label>              
				<form:select id="sala" path="room" class="form-control">
				  <option value="Premium">VIP room</option>
				  <option value="Room 1">Room 1</option>
				  <option value="Room 2">Room 2</option>
				  <option value="Room 3">Room 3</option>                
				</form:select>             
			  </div> 
			</div>

  			<!-- PRICE -->
			<div class="col-sm-3">
			  <div class="form-group">
				<label for="precio">Price</label>
				<form:input type="text" class="form-control" path="price" id="precio" required="required"/>
			  </div>  
			</div>
		  </div> <!-- End of 2 row -->

		  <button type="submit" class="btn btn-danger" >Save</button>
	  </form:form> 

	  <hr class="featurette-divider">

      <!-- ++++++++++++++++++++ Include the FOOTER file +++++++++++++++++++++++++++++++++++++ -->
	  <jsp:include page="../includes/footer.jsp"></jsp:include>
    </div> <!-- /end main container -->

    <!-- ++++++++++++++++++++++++++++ Bootstrap core JavaScript +++++++++++++++++++++++++++++ -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script> <!-- better through cdn -->
    <script src="${ urlPublic }/bootstrap/js/bootstrap.min.js"></script> 
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    
    <script>
      $(function () {
            $("#fecha").datepicker({dateFormat: 'dd-mm-yy'});
        }
      );
    </script>
  </body>
</html>