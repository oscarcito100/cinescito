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
    <title>Cines Cito | Bienvenido</title>
	
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

	<!-- +++++++++++++++++++++++++++++ 1. Include the NAVBAR file +++++++++++++++++++++++++++++++++++++++++++ -->
	<jsp:include page="includes/navbar.jsp"></jsp:include>
	
	<!-- +++++++++++++++++++++++++++ 2. Main container ++++++++++++++++++++++++++++++++++++++++++++++ -->
    <div class="container theme-showcase" role="main">

      <!-- -------------------- 2.1 Carousel ------------------------------------------- -->
      <div id="myCarousel" class="carousel slide" data-ride="carousel">
        <!-- INDICATORS -->
        <ol class="carousel-indicators">
        	<c:forEach items="${ banners }" var="banner" varStatus="loop">
        		<c:choose>
        			<c:when test="${loop.index == 0}">
        				<li data-target="#myCarousel" data-slide-to="${loop.index}" class="active"></li>
        			</c:when>
        			<c:otherwise>
        				<li data-target="#myCarousel" data-slide-to="${loop.index}"></li>
        			</c:otherwise>
        		</c:choose>
        	</c:forEach>
        </ol>
        <!-- ITEMS: Image Size 1140 x 250 -->
        <div class="carousel-inner" role="listbox">
        	<c:forEach items="${ banners }" var="banner" varStatus="loop">
        		<c:choose>
        			<c:when test="${loop.index == 0}">
        				<div class="item active">         
            				<img src="${ urlPublic }/images/${ banner.fileName }" alt="${ banner.title }" title="${ banner.title }" >
          				</div>
        			</c:when>
        			<c:otherwise>
        				<div class="item">         
            				<img src="${ urlPublic }/images/${ banner.fileName }" alt="${ banner.title }" title="${ banner.title }" >
          				</div>
        			</c:otherwise>
        		</c:choose>
        	</c:forEach>
        </div>
        <!-- Controls -->
        <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
          <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
          <span class="sr-only">Previous</span>
        </a>
        <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
          <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
          <span class="sr-only">Next</span>
        </a>
      </div><!-- /carousel -->
	  
	  <!-- --------------------- 2.2 Look for the dates of the films in la cartelera section ----------------------------------- -->
      <div class="row page-header">          
        <div class="col-lg-12">         
          <h2 class="text text-center"><span class="label label-success">EN CARTELERA</span></h2>          
          
          <!-- form to send the day selected to show the movies that are on screen that selected day 
               action: name of the url that receive the data 
               name: is the attribute that binds the input with the @RequestParam -->
          <form class="form-inline" action="${ urlRoot }search" method="post">
            <div class="form-group">
              <label for="fecha">Fecha: </label>
              <select id="fecha" name="dateSelected" class="form-control">
              	 <!-- insert the current day plus the next days in the <select> -->
                 <c:forEach items="${ dates }" var="date">
                 	<c:choose>
                 		<c:when test="${ date eq currentDate }">
                 			<option selected value="${ date }">${ date }</option> 
                 		</c:when>
                 		<c:otherwise>
                 			<option value="${ date }">${ date }</option> 
                 		</c:otherwise>
                 	</c:choose> 
                 </c:forEach>              
              </select>
            </div>            
            <button type="submit" class="btn btn-primary">Buscar</button>
          </form>
        </div>
      </div>
 
 	  <!-- ++++++++++++++++++++++++++++ 2.3 SubContainer: Films and news sections +++++++++++++++++++++++++++++++++++++++++++ -->     
      <div class="container marketing">     
		<!-- ------------------------------- 2.3.1 Films of cartelera ------------------------------------ -->
        <div class="row">	  
		  <c:forEach items="${ films }" var="film">
		    <div class="col-xs-12 col-sm-6 col-md-3">
               <img class="img-rounded" src="${ urlPublic }/images/${ film.image }" alt="${ film.title }" title="${ film.title }" width="150" height="200">
               <h4>${ film.title }</h4>
               <h4>
              	 <span class="label label-default">${ film.classification }</span>
                 <span class="label label-default">${ film.duration } min.</span>
                 <span class="label label-default">${ film.gender }</span>
               </h4>
               <!-- doing it with @PathVariable -->          
               <p><a class="btn btn-sm btn-primary" href="detail/${ film.id}/${ currentDate }" role="button">Consulta Horarios &raquo;</a></p>
               <%-- if we do it with @RequestParam
               <p><a class="btn btn-sm btn-primary" href="detail?idMovie=${ film.id }&date=${ currentDate }" role="button">Consulta Horarios &raquo;</a></p>
             --%>
             </div>
		  </c:forEach>	   
        </div>

		<!---------------- 2.3.2 NEWS --------------------------------------------------------- -->
        <div class="page-header">
          <h2 class="text text-center"><span class="label label-success">News</span></h2>
        </div>

		<c:forEach items="${ last3News }" var="news">
        	<div class="row">
          		<div class="col-sm-12 blog-main">
            		<div class="blog-post">              
              			<h3 class="blog-post-title">${ news.titulo }</h3>
              			<p class="blog-post-meta"><span class="label label-danger">Published: ${ news.datePublished }</span></p>             
              			<p>${ news.detail }</p>
              			<hr class="featurette-divider">
            		</div>
          		</div>
        	</div>			
		</c:forEach>


      </div> <!-- /sub container -->

      <!-- ++++++++++++++++++++ 3. Include the FOOTER file +++++++++++++++++++++++++++++++++++++ -->
	  <jsp:include page="includes/footer.jsp"></jsp:include>

    </div> <!-- /main container -->

    <!-- ++++++++++++++++++++++++++++ Bootstrap core JavaScript +++++++++++++++++++++++++++++ -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script> <!-- better through cdn -->
    <script src="${ urlPublic }/bootstrap/js/bootstrap.min.js"></script> 
  </body>
</html>
