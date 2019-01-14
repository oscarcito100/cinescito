<%-- tag library to be able to use tag url (urlPublic) in a jsp file --%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%-- import jstl library to be able to use core elements in a jsp file --%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%-- import jstl library to be able to use format elements in a jsp file --%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<!DOCTYPE html>
<html lang="en">

  	<!-- ==================================== HEAD ===================================== -->
   	<head>
    	<meta charset="utf-8">
    	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    	<meta name="viewport" content="width=device-width, initial-scale=1">   
    	<meta name="description" content="Show the list of all banners in a table with their features">
    	<meta name="author" content="Oscar Ruben Germade-Gonzalez Rios">
    	<title>List of Banners</title>
	
		<!-- create relative route to the resources directory to assign to var="urlPublic" 
     	it means to be able to use any file within /resources we have to use var="urlPublic -->
		<spring:url value="/resources" var="urlPublic" />
		<spring:url value="/banner/create" var="urlCreate" />
	
		<!-- files to integrate bootstrap -->
    	<link href="${ urlPublic }/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    	<link href="${ urlPublic }/bootstrap/css/theme.css" rel="stylesheet">
  	</head>
  	
  	<!-- ============================= BODY ===================================================== -->
  	<body>

    	<!-- +++++++++++++++++++++++++++++ Include the NAVBAR file +++++++++++++++++++++++++++++++++++++++++++ -->
		<jsp:include page="../includes/navbar.jsp"></jsp:include>
	
		<!-- +++++++++++++++++++++++++++ Main container ++++++++++++++++++++++++++++++++++++++++++++++ -->
    	<div class="container theme-showcase" role="main">

			<!-- ------------------------------------ PAGE HEADER ------------------------- -->	
      		<h3>List of elements of the Banner</h3>
      		
      		<!-- ---------------------- BUTTON: link to add a new element in the banner ------------------- -->
      		<a href="${ urlCreate }" class="btn btn-success" role="button" title="New Banner" >Add a new Banner</a><br><br>

			<!-- TABLE: display list of films and their features -->
       		<div class="table-responsive">
        		<table class="table table-hover table-striped table-bordered">
        			<thead>
        				<tr>
                			<th>Id</th>
                			<th>Title</th>                           
                			<th>Published Date</th>              
                			<th>File name</th>
                			<th>status</th>
                			<th>Options</th>              
            			</tr>
        			</thead>
            		<tbody>
            			<c:forEach items="${ banners }" var="banner">
            				<tr>
                				<td>${ banner.id }</td>
                				<td>${ banner.title }</td>
                 				<td><fmt:formatDate value="${ banner.publishedDate }" pattern="dd-MM-yyyy"/></td>    
                				<td>${ banner.fileName}</td>                         
                				<td>
                					<c:choose>
										<c:when test="${ banner.status eq 'Active' }">
											<span class="label label-success">ACTIVE</span>
										</c:when>
										<c:otherwise>
											<span class="label label-danger">INACTIVE</span>
										</c:otherwise>
									</c:choose>
								</td>
                				<td>
                    				<a href="#" class="btn btn-success btn-sm" role="button" title="Edit" ><span class="glyphicon glyphicon-pencil"></span></a>
                    				<a href="#" class="btn btn-danger btn-sm" role="button" title="Delete" ><span class="glyphicon glyphicon-trash"></span></a>
                				</td>
            				</tr>
            			</c:forEach>
            		</tbody>         
        		</table>
      		</div> 
      		 
      		<hr class="featurette-divider">

      		<!-- --------------------------- Include the FOOTER file -------------------- -->
	  		<jsp:include page="../includes/footer.jsp"></jsp:include>
   		</div> <!-- /container -->

    	<!-- ++++++++++++++++++++++++++++ Bootstrap core JavaScript +++++++++++++++++++++++++++++ -->
    	<!-- Placed at the end of the document so the pages load faster -->
    	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script> <!-- better through cdn -->
    	<script src="${ urlPublic }/bootstrap/js/bootstrap.min.js"></script>     
  	</body>
</html>
