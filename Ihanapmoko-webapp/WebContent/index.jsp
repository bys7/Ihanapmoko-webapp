<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<script type='text/javascript'>	

		$(document).ready(function(){ 
			
			
		});
	
	
	var loadFile = function(event) {
	    var output = document.getElementById('output');
	    output.src = URL.createObjectURL(event.target.files[0]);
	  };
</script>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/margin.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/ihanap.css">

<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootbox.min.js"></script>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Ihanapmoko</title>
</head>
<body>

	<div class="ihanapMain">	
		<div class="header">
        	<div class="width960">
                <span class="logo pullLeft"></span>
                
                <!---Login--->
                    <button class="buttonOrange pullRight mtop20">Login/Signup</button>
                    
                    <!--<div class="pullRight mtop20">
                    	<span class="pullLeft mright20 textWhite">Login as <a href="#" class="linkName">Name</a></span>
                    	<button class="buttonRed pullRight">Logout</button>
                        <span class="clear"></span>
                    </div>
                
                <!----------->
                <span class="clear"></span>
            </div>
        </div>
                     
       	<div class="body padtop40 padbot20">
       		<div class="width960">
       			<div class="leaderboard">
                	<iframe src="https://storage.googleapis.com/support-kms-prod/SNP_501E7C3D5CA3CA07C641E6BAFB8A53C794CF_2922339_en_v2" frameborder="0" width="100%" height="100%" scrolling="no"></iframe>
                </div>
                
                <div class="mtop20 width65P pullLeft">
                	<div class="search">
                		 <form id="searchForm" method="post" action="SearchServlet">

							Ano hanap mo? <input type="text" name="searchParameter">
							
							<button id="searchButton" class="buttonBlue">Search</button>
											
						</form>                	
                	</div>
                	
                	<div class="hanapko">
                    	<h1 class="textHead">Hanap ko</h1>
                    	
                        <a href="${pageContext.request.contextPath}/AdvertisementServlet" ><button class="buttonBlue" data-toggle="modal" data-target="#myModal">Post</button></a>
                    </div>
                </div>
                
                <div class="skyscraper pullRight mtop20">
                	<iframe src="https://lh4.ggpht.com/ike-jviZQ32RHuhkwLcAt_9vdpBX1oWKU00NX7QRe5GPl7-5sapzZ0u91_ssg_-Ednak2Hj-Hg=w162" frameborder="0" width="100%" height="100%" scrolling="no"></iframe>
                
                </div> 
       		</div>
       	</div>
        
        <div class="footer">
        	<div class="width960 cenText">

                	<a href="#">About us</a>
                    <a href="CommentsServlet">Contact Us</a>
                    <a href="#">Disclaimer</a>
                
            </div>
        
        </div>
	
	</div>

</body>
</html>