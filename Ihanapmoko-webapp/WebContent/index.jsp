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
        <%@ include file="/WEB-INF/jsp/header.jsp" %>
               
       	<div class="body padtop40 padbot20">
       		<div class="width960">
       			<div class="leaderboard">
                	<iframe src="https://storage.googleapis.com/support-kms-prod/SNP_501E7C3D5CA3CA07C641E6BAFB8A53C794CF_2922339_en_v2" frameborder="0" width="100%" height="100%" scrolling="no"></iframe>
                </div>
                
                <div class="mtop20 width65P pullLeft">
                	<!-- <div class="search">
                		 <form id="searchForm" method="post" action="Search">

							Magbebenta ako ng? <input type="text" name="searchParameter">
							
							<button id="searchButton" class="buttonBlue">Search</button>
											
						</form>                	
                	</div>
                	
                	<div class="hanapko">
                    	<h1 class="textHead">Hanap ko</h1>
                    	
                        <a href="${pageContext.request.contextPath}/Advertisement" ><button class="buttonBlue" data-toggle="modal" data-target="#myModal">Post</button></a>
                    </div>-->
                    <div class="hanapko">
                    	<h1 class="textHead">
                    		<a href="${pageContext.request.contextPath}/Advertisement" >
                    			(+) Ihanap mo ko ng
                    		</a>
                    		  
                    	</h1>                    	
                    </div>
                    <form id="searchForm" method="post" action="Search">
	                    <div class="hanapko">
	                    	<h1 class="textHead">
	                    		Ito ang mga naghahanap ng                   	
	                    		<input type="text" name="searchParameter">							
								<button id="searchButton" class="buttonBlue">Search</button>
	                    	</h1>
	                    	
	                    </div>
                    </form>
                    	
                    <span class="clear"></span>
                    <br/><br/>
	                <p>IhanapMoKo.com is a spin-off of the well-known buy-and-sell website. 
	                Buy and sell website posts are made for sellers who would want to vend items, 
	                may it be secondhand or brand new. From these posts, 
	                buyers who are eager to either buy or bargain with the sellers find items they are looking for. 
	                However, things turn the opposite way in IhanapMoKo.com. 
	                The said website focuses mainly on "looking for" users/posts. 
	                The posts in IhanapMoKo.com are not item-entitled but rather user-entitled. 
	                The sellers then are the ones to come upon the buyers who are willing to purchase the items they are about to sell.
	                Moreover, these buyers get the chance to declare their desired amount on their posts. 
	                From this, multiple sellers can "bid" on a user, and this user is to choose what he/she considers a wiser purchase.</p>  
                </div>
                
                
                <div class="skyscraper pullRight mtop20">
                	<iframe src="https://lh4.ggpht.com/ike-jviZQ32RHuhkwLcAt_9vdpBX1oWKU00NX7QRe5GPl7-5sapzZ0u91_ssg_-Ednak2Hj-Hg=w162" frameborder="0" width="100%" height="100%" scrolling="no"></iframe>
                
                </div>        
                      
       		</div>
       		<span class="clear"></span>
       		
	       	<div class="leaderboard">
	        	<iframe src="https://storage.googleapis.com/support-kms-prod/SNP_501E7C3D5CA3CA07C641E6BAFB8A53C794CF_2922339_en_v2" frameborder="0" width="100%" height="100%" scrolling="no"></iframe>
	        </div>
       	</div>
        
        
        <%@ include file="/WEB-INF/jsp/footer.jsp" %>
	
	</div>

</body>
</html>