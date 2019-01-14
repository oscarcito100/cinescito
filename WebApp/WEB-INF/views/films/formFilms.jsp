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
    <meta name="description" content="Form to create a new movie">
    <meta name="author" content="Oscar Ruben Germade-Gonzalez Rios">
    <title>Create new movie</title>

	<!-- create relative route to the resources directory to assign to var="urlPublic" 
     it means to be able to use any file within /resources we have to use var="urlPublic -->
	<spring:url value="/resources" var="urlPublic" />
	<spring:url value="/films/save" var="urlForm" />
	
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
      <div class="page-header">
		<h3 class="blog-title"><span class="label label-success">Data of the movie</span></h3>
      </div>
	  
	  <!-- -------------------------------------------- FORM: create new movie ------------- -->
	  <!-- DISPLAY ERRORS IN THE FORM that happen with the data binding -->
	  <spring:hasBindErrors name="film"> <!-- name: is the name of the parameter model object of the method save() -->
	     <div class="alert alert-danger" role="alert"> 
	       Please, correct the next mistakes:
	       <ul> 
	         <c:forEach var="error" items="${ errors.allErrors }"> <!-- return a list with all errors -->
	           <li><spring:message message="${ error }" /></li> <!-- error: every error of the object -->
	         </c:forEach>
	       </ul> 
	     </div> 
	  </spring:hasBindErrors>
	  
	  <!-- FORM: enctype:allow us to upload files
	  			 modelAttribute: Pass the model class 'Films' to the form to be able to use it in the view
	  			 using the spring form tags in case of an error the values of the inputs will stand,
	  			 also inputs will have the values by default we gave them in the Model Class-->
      <form:form action="${ urlForm }" method="post" enctype="multipart/form-data" modelAttribute="film">
        <div class="row"> 
          <!-- IMAGE BY DEFAULT -->
          <div class="col-sm-3">
            <div class="form-group">
              <img class="img-rounded" src="${ urlPublic }/images/${ film.image }" alt="Image by default" title="Image by default" width="150" height="200">
            </div>  
          </div>
        </div>
        <!-- 1 row -->
        <div class="row">
        
          <!-- ID: is hidden to keep the same id when it is used to update a film, so the film is not treat as a new film -->
          <form:hidden path="id"/> 
          <form:hidden path="detail.id"/> 
          
          <!-- TITLE -->
          <div class="col-sm-3">
            <div class="form-group">
              <label for="title">Title</label>
              <form:input type="text" class="form-control" path="title" id="title" required="required" />
            </div>  
          </div>
          
          <!-- DURATION -->
          <div class="col-sm-3">
            <div class="form-group">
              <label for="duracion">Duration</label>
              <form:input type="text" class="form-control" path="duration" id="duracion" required="required" />
            </div>  
          </div>
          <!-- CLASSIFICATION -->
          <div class="col-sm-3">
            <div class="form-group">
              <label for="clasificacion" class="control-label">Classification</label>              
              <form:select id="clasificacion" path="classification" class="form-control">
                <form:option value="A">Classification A</form:option>
                <form:option value="B">Classification B</form:option>
                <form:option value="C">Classification C</form:option>                  
              </form:select>             
            </div> 
          </div>
          <!-- GENDER -->
          <div class="col-sm-3">
            <div class="form-group">
              <label for="genero" class="control-label">Gender</label>  
              <%-- items: allow us to display dinamically the gender from the controller --%>            
              <form:select id="genero" path="gender" class="form-control" items="${ gender }">               
              </form:select>             
            </div> 
          </div>         
        </div>

		<!-- 2 row -->
        <div class="row">
          <!-- STATUS -->
          <div class="col-sm-3">
            <div class="form-group">
              <label for="estatus" class="control-label">Status</label>              
              <form:select id="genero" path="status" class="form-control">
                <form:option value="Activa">Active</form:option>
                <form:option value="Inactiva">Inactive</form:option>               
              </form:select>             
            </div> 
          </div> 
          <!-- PREMIER DATE -->    
          <div class="col-sm-3">
            <div class="form-group">
              <label for="fechaEstreno">Premier Date</label>             
              <form:input type="text" class="form-control" path="premierDate" id="fechaEstreno" required="required" />
            </div>  
          </div>
		  <!-- IMAGE: don't put tag form: neither change name attribute by path -->
          <div class="col-sm-3">
            <div class="form-group">
              <label for="imagen">Image</label>
              <form:hidden path="image"/>
              <input type="file" id="imageFile" name="imageFile" />
              <p class="help-block">Image of the movie</p>
            </div> 
          </div>
        </div>

        <!-- 3 row: DETAILS --> 
        <div class="page-header">
            <h3 class="blog-title"><span class="label label-success">Details</span></h3>
        </div>

        <div class="row">
          <!-- DIRECTOR -->
          <div class="col-sm-3">
            <div class="form-group">
              <label for="director">Director</label>
              <form:input type="text" class="form-control" path="detail.director" id="director" required="required" />
            </div>  
          </div>
          <!-- ACTORS -->
          <div class="col-sm-3">
            <div class="form-group">
              <label for="actores">Actors</label>
              <form:input type="text" class="form-control" path="detail.actors" id="actores" required="required" />
            </div>  
          </div>
		  <!-- TRAILER -->
          <div class="col-sm-6">
            <div class="form-group">
              <label for="trailer">Trailer URL (Youtube)</label>
              <form:input type="text" class="form-control" path="detail.trailer" id="trailer" placeholder="URL completa del video de YOUTUBE" required="required" />
            </div>  
          </div> 
        </div> 
		
		<!-- 4 ROW -->
        <div class="row">
        <!-- SINOPSIS -->
          <div class="col-sm-6">
            <div class="form-group">
              <label for="sinopsis">Sinopsis</label>
              <form:textarea class="form-control" rows="5" cols="15" path="detail.sinopsis" id="sinopsis"/>
            </div> 
          </div> 
        </div>
        
        <!-- BUTTON -->
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
          $("#fechaEstreno").datepicker({dateFormat: 'dd-mm-yy'});
        }
      );
    </script>
  </body>
</html>
