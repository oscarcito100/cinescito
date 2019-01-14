<%-- tag library to be able to use tag url (urlPublic) in a jsp file --%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%-- import jstl library to be able to use core elements in a jsp file --%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html lang="en">

 	<!-- ==================================== HEAD ===================================== -->
  	<head>
    	<meta charset="utf-8">
    	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    	<meta name="viewport" content="width=device-width, initial-scale=1">   
    	<meta name="description" content="Form to create a new banner">
    	<meta name="author" content="Oscar Ruben Germade-Gonzalez Rios">
    	<title>Create new element in the banner</title>

		<!-- create relative route to the resources directory to assign to var="urlPublic" 
     	it means to be able to use any file within /resources we have to use var="urlPublic -->
		<spring:url value="/resources" var="urlPublic" />
		<spring:url value="/banner/save" var="urlForm" />
	
    	<!-- files to integrate bootstrap and jquery -->
    	<link href="${ urlPublic }/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    	<link href="${ urlPublic }/bootstrap/css/theme.css" rel="stylesheet">
    	<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

  	</head>

  	<!-- ============================= BODY ===================================================== -->
  	<body>

    	<!-- +++++++++++++++++++++++++++++ Include the NAVBAR file +++++++++++++++++++++++++++++++++++++++++++ -->
		<jsp:include page="../includes/navbar.jsp"></jsp:include>

		<!-- +++++++++++++++++++++++++++ Main container ++++++++++++++++++++++++++++++++++++++++++++++ -->
      	<div class="container theme-showcase" role="main">

			<!-- ------------------------------------ PAGE HEADER ------------------------- -->
        	<h3 class="blog-title"><span class="label label-success">Data of the banner</span></h3>
		
			<!-- -------------------------------------------- FORM: create new movie ------------- -->
			<!-- DISPLAY ERRORS IN THE FORM -->
	  		<spring:hasBindErrors name="banner"> <!-- name: is the name of the parameter object of the method save() -->
	     		<div class="alert alert-danger" role="alert"> 
	       			Please, correct the next mistakes:
	       			<ul> 
	         			<c:forEach var="error" items="${errors.allErrors}"> --><!-- return object errors -->
	           				<li><spring:message message="${error}" /></li> <!-- error: every error of the object -->
	         			</c:forEach>
	       			</ul> 
	     		</div> 
	  		</spring:hasBindErrors>
	  
	  		<!-- FORM: enctype:allow us to upload files -->
        	<form action="${ urlForm }" method="post" enctype="multipart/form-data">
        		<!-- 1 row -->
            	<div class="row">  
            		<!-- TITLE -->       
               		<div class="col-sm-6">
                  		<div class="form-group">
                     		<label for="titulo">Title</label>             
                     		<input type="text" class="form-control" name="title" id="titulo" required="required"/>
                  		</div>
               		</div>
					<!-- IMAGE -->
               		<div class="col-sm-3">
                  		<div class="form-group">
                     		<label for="imagen">Image</label>
                     		<input type="file" id="archivoImagen" name="imageFile" required="required" />
                     		<p class="help-block">Recommended size: 1140 x 250 </p>
                  		</div> 
               		</div> 
					<!-- STATUS -->
               		<div class="col-sm-3">
                  		<div class="form-group">
                     		<label for="estatus">Status</label>             
                     		<select id="estatus" name="status" class="form-control">
                        		<option value="Active">Active</option>
	                        	<option value="Inactive">Inactive</option>                
	                     	</select>  
                  		</div>
               		</div>
            	</div>
				<!-- BUTTON -->
            	<button type="submit" class="btn btn-danger" >Save</button>
         	</form> 

         	<hr class="featurette-divider">

      		<!-- ++++++++++++++++++++ Include the FOOTER file +++++++++++++++++++++++++++++++++++++ -->
	  		<jsp:include page="../includes/footer.jsp"></jsp:include>

      	</div> <!-- /container -->

    <!-- ++++++++++++++++++++++++++++ Bootstrap core JavaScript +++++++++++++++++++++++++++++ -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script> <!-- better through cdn -->
    <script src="${ urlPublic }/bootstrap/js/bootstrap.min.js"></script> 
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script>
      $(function () {
          $("#fechaEstreno").datepicker({dateFormat: 'dd-mm-yy'});
        }
      );
    </script>

   </body>
</html>
