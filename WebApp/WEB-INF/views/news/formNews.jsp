<%-- aggregating the jstl tag libraries to the view to be able to display the data from the model --%>
<%-- tag library to be able to use tag url (urlPublic) in a jsp file --%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%-- import jstl library to be able to use core elements in a jsp file --%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%-- tag library to be able to use form elements in a jsp file --%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html lang="en">

<!-- ==================================== HEAD ===================================== -->
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="includes the form to create a new news in the main page">
    <meta name="author" content="oscar ruben germande-gonzalez rios">
    <title>Create news</title>   

	<!-- create relative route to the resources directory to assign to var="urlPublic" 
     it means to be able to use any file within /resources we have to use var="urlPublic -->
	<spring:url value="/resources" var="urlPublic" />
	<spring:url value="/news/save" var="urlForm" />
	
    <!-- files to integrate bootstrap -->
    <link href="${ urlPublic }/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="${ urlPublic }/bootstrap/css/theme.css" rel="stylesheet">

  </head>

<!-- ============================= BODY ===================================================== -->
  <body>
	<!-- +++++++++++++++++++++++++++++ Include the navbar file +++++++++++++++++++++++++++++++++++++++++++ -->
	<jsp:include page="../includes/navbar.jsp"></jsp:include>

    <div class="container theme-showcase" role="main">

      <h3 class="blog-title"><span class="label label-success">Data to create news</span></h3>
	  
	  <!-- ++++++++++++++++++++++++++++ FORM ++++++++++++++++++++++++++++++++++++++++ -->
	  	  <!-- DISPLAY ERRORS IN THE FORM that happen with the data binding -->
	  <spring:hasBindErrors name="news"> <!-- name: is the name of the parameter model object of the method save() -->
	     <div class="alert alert-danger" role="alert"> 
	       Please, correct the next mistakes:
	       <ul> 
	         <c:forEach var="error" items="${ errors.allErrors }"> <!-- return a list with all errors -->
	           <li><spring:message message="${ error }" /></li> <!-- error: every error of the object -->
	         </c:forEach>
	       </ul> 
	     </div> 
	  </spring:hasBindErrors>
	  
	  <!-- ------- MESSAGE: successful message after saving a movie if the 'message' attribute exist 
      <c:if test="${ message != null }">
      	<div class='alert alert-success' role="alert">${ message }</div>
      </c:if>-->
	  	  
      <!-- FORM: enctype:allow us to upload files
	  			 modelAttribute: Pass the model class 'Films' to the form to be able to use it in the view
	  			 using the spring form tags in case of an error the values of the inputs will stand,
	  			 also inputs will have the values by default we gave them in the Model Class-->
      <form:form action="${ urlForm }" method="POST" modelAttribute="news">
        <div class="row"> 
        
          <!-- ID: is hidden to keep the same id when it is used to update a news, so the news is not treat as a new news -->
          <form:hidden path="id"/>
        
          <!-- TITLE -->        
          <div class="col-sm-6">
            <div class="form-group">
              <label for="titulo">Title</label>                           
              <form:input type="text" class="form-control" path="titulo" id="titulo" required="required"/>
            </div>
          </div>
          
          <!-- STATUS -->
          <div class="col-sm-3">
            <div class="form-group">
              <label for="estatus">Status</label>             
              <form:select id="estatus" path="status" class="form-control">
                <form:option value="Activa">Active</form:option>
                <form:option value="Inactiva">Inactive</form:option>                
              </form:select>  
            </div>
          </div>
        </div>
        
        <div class="row"> 
        
          <!-- DETAILS -->
          <div class="col-sm-12">
            <div class="form-group">
              <label for="detalle">Details</label>             
              <form:textarea class="form-control" path="detail" id="detalle" rows="10"></form:textarea>
            </div>  
          </div>
        </div>

		<!-- BUTTON to submit the form -->
        <button type="submit" class="btn btn-danger" >Save</button>
      </form:form> 

      <hr class="featurette-divider">

      <!-- ++++++++++++++++++++ Include the footer file +++++++++++++++++++++++++++++++++++++ -->
	  <jsp:include page="../includes/footer.jsp"></jsp:include>

    </div> <!-- / main container -->

    <!-- +++++++++++++++++++++ Bootstrap core JavaScript ++++++++++++++++++++++++ -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script> 
    <script src="${ urlPublic }/bootstrap/js/bootstrap.min.js"></script>
    
    <!-- files to use the web editor: TinyMCE -->
    <script src="${ urlPublic }/tinymce/tinymce.min.js"></script>
    <script>
      tinymce.init({
          selector: '#detalle', // the id of the textarea where we insert this plugin
          plugins: "textcolor, table lists code",
          toolbar: " undo redo | bold italic | alignleft aligncenter alignright alignjustify \n\
                    | bullist numlist outdent indent | forecolor backcolor table code" 
      }); // in toolbar we insert the name of the buttons to insert in the editor
    </script>
  </body>
</html>
