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
    	<meta name="description" content="Show the list of all NEWS in a table with their features">
    	<meta name="author" content="Oscar Ruben Germade-Gonzalez Rios">
    	<title>List of News</title>
	
		<!-- create relative route to the resources directory to assign to var="urlPublic" 
     	it means to be able to use any file within /resources we have to use var="urlPublic -->
		<spring:url value="/resources" var="urlPublic" />
		<spring:url value="/news/create" var="urlCreate" />
		<spring:url value="/news/edit" var="urlEdit" />
		<spring:url value="/news/delete" var="urlDelete" />
		<spring:url value="/news/indexPaginate" var="urlIndexPaginate" />
		
		<!-- files to integrate bootstrap -->
    	<link href="${ urlPublic }/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    	<link href="${ urlPublic }/bootstrap/css/theme.css" rel="stylesheet">
  	</head>

  	<!-- ============================= BODY ========================================== -->
  	<body>

    	<!-- +++++++++++++++++++++++++++++ Include the NAVBAR file +++++++++++++++++++++++++++++++++++++++++++ -->
		<jsp:include page="../includes/navbar.jsp"></jsp:include>
	
		<!-- +++++++++++++++++++++++++++ Main container ++++++++++++++++++++++++++++++++++++++++++++++ -->
    	<div class="container theme-showcase" role="main">
    		
    		<!-- ------------------------------------ PAGE HEADER ------------------------- -->	
      		<div class="page-header">
				<h3 class="blog-title">List of news</h3>
      		</div>
      		
      		<!-- ------- MESSAGE: successful message after saving a news if the 'message' attribute exist -->
      		<c:if test="${ message != null }">
      			<div class='alert alert-success' role="alert">${ message }</div>
      		</c:if>
      		
      		<!-- ---------------------- BUTTON: link to add a new movie ------------------- -->
      		<a href="${urlCreate}" class="btn btn-success" role="button" title="New Movie" >Add a News</a><br><br>
      		
      		<!-- TABLE: display list of films and their features -->
      		<div class="table-responsive">
        		<table class="table table-hover table-striped table-bordered">
        			<thead>
        				<tr>
                			<th>Title</th>
                			<th>Date Release</th>
                			<th>Detail</th>
                			<th>Status</th>
                			<th>Options</th>
            			</tr>
        			</thead>
            		<tbody>
            			 <%-- <c:forEach var="new" items="${ news }"> if we use a List --%>
            			 <c:forEach var="n" items="${ news.content }">
            				<tr>
            					<td>${ n.titulo }</td>
            					<td><fmt:formatDate value="${ n.datePublished }" pattern="dd-MM-yyyy"/></td>
            					<td>${ n.detail }</td>
            					<td>
            						<c:choose>
										<c:when test="${ n.status eq 'Activa' }">
											<span class="label label-success">ACTIVE</span>
										</c:when>
										<c:otherwise>
											<span class="label label-danger">INACTIVE</span>
										</c:otherwise>
									</c:choose>
            					</td>
            					<td>
            						<a href="${ urlEdit }/${ n.id }" class="btn btn-success btn-sm" role="button" title="Edit"><span class="glyphicon glyphicon-pencil"></span></a>
									<a href="${ urlDelete }/${ n.id }" class="btn btn-danger btn-sm" role="button" title="Delete"><span class="glyphicon glyphicon-trash"></span></a>
            					</td>
            				</tr>
            			</c:forEach>           		
            		</tbody>           		
            	</table>
            	
             	<!-- List of pagination -->
            	<nav aria-label="">
            		<ul class="pager">
            			<li><a href="${ urlIndexPaginate }?page=${ n.number - 1 }">Backward</a></li>
            			<li><a href="${ urlIndexPaginate }?page=${ n.number + 1 }">Forward</a></li>
            		</ul>
            	</nav>
           	</div>
      		
    		<hr class="featurette-divider">

      		<!-- --------------------------- Include the FOOTER file -------------------- -->
	  		<jsp:include page="../includes/footer.jsp"></jsp:include>

    	</div> <!-- /end main container -->
    
    	<!-- ++++++++++++++++++++++++++++ Bootstrap core JavaScript +++++++++++++++++++++++++++++ -->
    	<!-- Placed at the end of the document so the pages load faster -->
    	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script> <!-- better through cdn -->
    	<script src="${ urlPublic }/bootstrap/js/bootstrap.min.js"></script> 
	</body>
</html>