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
      <meta name="author" content="Oscar Ruben Germade-Gonzalez Rios">
      <meta name="description" content="A contact form from a client to the company">
      <title>Contact Form</title>
      
      <!-- create relative route to the resources directory to assign to var="urlPublic" 
      it means to be able to use any file within /resources we have to use var="urlPublic -->
	  <spring:url value="/resources" var="urlPublic" />
	  <spring:url value="/contact/save" var="urlForm" />
	  
      <!-- files to integrate bootstrap and jquery -->
      <link href="${ urlPublic }/bootstrap/css/bootstrap.min.css" rel="stylesheet">
      <link href="${ urlPublic }/bootstrap/css/theme.css" rel="stylesheet">
      <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

   </head>

   <!-- ============================= BODY ================================================ -->
   <body>
    <!-- +++++++++++++++++++++++++++++ Include the NAVBAR file +++++++++++++++++++++++++++++++++++++++++++ -->
	<jsp:include page="../includes/navbar.jsp"></jsp:include>
	
	  <!-- +++++++++++++++++++++++++++ Main container ++++++++++++++++++++++++++++++++++++++ -->
      <div class="container theme-showcase" role="main">

         <h3 class="blog-title text-center"><span class="label label-success">Contact</span></h3><br>  
		 
		 <!-- DISPLAY ERRORS IN THE FORM that happen with the data binding -->
	     <spring:hasBindErrors name="contact"> <!-- name: is the name of the parameter model object of the method save() -->
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
      	  
		 ${ contact }
		 <!-- FORM -->
         <form:form class="form-horizontal" method="post" action="${ urlForm }" modelAttribute="contact">
         
            <!-- NAME -->
            <div class="form-group">
               <label for="nombre" class="col-sm-2 control-label">Name</label>
               <div class="col-sm-10">
                  <form:input type="text" class="form-control" id="nombre" path="name" placeholder="Your Name" required="required"/>
               </div>
            </div>
            
            <!-- EMAIL -->
            <div class="form-group">
               <label for="email" class="col-sm-2 control-label">Email</label>
               <div class="col-sm-10">
                  <form:input type="email" class="form-control" path="email" id="email" placeholder="Email" required="required"/>
               </div>
            </div>
			
			<!-- CHOOSE GENDERS
				 	multiple: allows to choose multiple elements in the select element -->
            <div class="form-group">
               <label for="genero" class="col-sm-2 control-label">Favourite Genders</label>
               <div class="col-sm-10">
                  <form:select id="genero" path="favouriteGenders" multiple="multiple" class="form-control" items="${ gender }"/> 
               </div>
            </div>
			
			<!-- YOUR EXPERIENCE
			     type="radio" is deleted because the form:radiobutton already specifies what it is -->
            <div class="form-group">
               <label class="col-sm-2 control-label">Your experience with us</label>
               <div class="col-sm-10">
                  <label><form:radiobutton path="rating" value="1"/> Very poor</label>
                  <label><form:radiobutton path="rating" value="2"/> Poor</label>
                  <label><form:radiobutton path="rating" value="3"/> Regular</label>
                  <label><form:radiobutton path="rating" value="4"/> Good</label>
                  <label><form:radiobutton path="rating" value="5"/> Very good</label>
               </div>
            </div>
			
			<!-- CHOOSE NOTIFICATIONS
			     form:checkboxes: we can render several with just one declaration
			     but also with can use form:checkbox individually as we did with form:radiobutton -->
            <div class="form-group">
               <label class="col-sm-2 control-label">Would you like to receive notifications?:</label>
               <div class="col-sm-10">
               	  <form:checkboxes items="${ typesOfNotifications }" path="notifications"/>
               </div>
            </div>

			<!-- COMMENTS -->
            <div class="form-group">
               <label class="col-sm-2 control-label">Comments:</label>
               <div class="col-sm-10">
                  <form:textarea class="form-control" path="comment" id="comentarios" rows="5"/>
               </div>
            </div>

			<!-- BUTTONS -->
            <div class="form-group">
               <div class="col-sm-offset-2 col-sm-10">
                  <button type="submit" class="btn btn-success">Send me</button>
               </div>
            </div>
         </form:form> <!-- /End form -->

         <hr class="featurette-divider">

         <!-- ++++++++++++++++++++ Include the FOOTER file +++++++++++++++++++++++++++++++++++++ -->
	     <jsp:include page="../includes/footer.jsp"></jsp:include>

      </div> <!-- /container -->

      <!-- ++++++++++++++++++++++++++++ Bootstrap core JavaScript +++++++++++++++++++++++++++++ -->
      <!-- Placed at the end of the document so the pages load faster -->
      <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script> <!-- better through cdn -->
      <script src="${ urlPublic }/bootstrap/js/bootstrap.min.js"></script> 
      <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script> 		
   </body>
</html>
