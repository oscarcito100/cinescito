<%-- tag library to be able to use tag url in a jsp file --%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!-- create relative route to the resources directory to assign to var="urlPublic" 
     it means to be able to use any file within /resources we have to use var="urlPublic -->
<spring:url value="/" var="urlRoot" />

<!-- +++++++++++++++++++++++++++++ FIXED NAVBAR +++++++++++++++++++++++++++++++++++++++++++ -->
    <nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="${ urlRoot }">Cine Cito</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav">   
          	<li><a href="${ urlRoot }films/indexPaginate?page=0">Movies</a></li>  
          	<li><a href="${ urlRoot }banner/index">Banner</a></li>  
          	<li><a href="${ urlRoot }contact/create">Contact</a></li> 
          	<li><a href="${ urlRoot }news/indexPaginate?page=0">News</a></li>     
            <li><a href="#">About</a></li>
            <li><a href="#">Login</a></li>            
          </ul>
        </div><!-- /nav-collapse -->
      </div>
    </nav> <!-- END fixed navbar -->