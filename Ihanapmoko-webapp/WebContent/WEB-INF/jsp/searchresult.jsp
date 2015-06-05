<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<script type='text/javascript'>	

	$(document).ready(function(){ 
		$('#submitComment').click(function() {
			$('#sendComment').submit();
			//$('#formAttachment').submit();
		});		
		//loadWindow(imgCnt);
		
	});
	
	function loadWindow(imgCnt){		
		var img = [], i;
		
		for(i = 1; i <= imgCnt; i++){
		
			img = document.getElementById('imageSize_' + i);
			var imgWidth = img.naturalWidth;
			var imgHeight = img.naturalHeight;
			
			if(imgHeight > imgWidth){
				$('#imageSize_' + i).width(70);
				$('#imageSize_' + i).height(100);
			}else{
				$('#imageSize_' + i).width(150);
				$('#imageSize_' + i).height(100);
			}
		}
		
	}

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
<body onload="loadWindow('${lstFileNameSize}')">
	
	<div style="display:nont;"><fmt:formatDate var="currentDate" pattern="M-dd-yyyy" value="${currentDate}" /></div>
	
	<div class="ihanapMain">
		<div class="header">
        	<div class="width960">
                <a href="HomeServlet"><span class="logo pullLeft"></span></a>
                
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
		        
		        <div class="thumbnails mtop20">
		        	<div class="search">
		        		<form id="searchForm" method="post" action="SearchServlet">
							Ano hanap mo? <input type="text" name="searchParameter" value="${searchParameter}">					
							<button id="searchButton" class="buttonBlue">Search</button>									
						</form> 
		        	</div>
		        	<div class="gallery">        	
		        		<ul>
		        			<c:forEach items="${lstSearchResult}" var="searchBean" >
		        				<c:set var="fileLoop" value="${fileLoop + 1}" />        				
		        				<li>        					
		        					<a href="DashboardServlet?${searchBean.advertisementId}" class="imgHolder">
		        						<img id="imageSize_${fileLoop}" src="/Ihanapmoko-webapp/SearchImageServlet?imageName=${searchBean.picture_destination}"/>
		        					</a>        					
		       						<p class="mbot10 mtop10"><c:out value="${searchBean.advertisement}"/></p>
		       						<div style="display:nont;"><fmt:formatDate var="dateCreated" pattern="M-dd-yyyy" value="${searchBean.date_created}" /></div>
		       						
		       						<p class="mbot10 mtop10"><span><c:out value="${searchBean.location}"/></span>|<c:out value="${currentDate - dateCreated}"/><span></span></p>        					
		        				</li>        				
		        			</c:forEach>
		        		</ul>
		        	</div>
		        </div>
        	</div>        	
        </div>
        
        
        
         <!-----Footer----->
        <div class="footer">
        	<div class="width960 cenText">

                	<a href="#">About us</a>
                    <a href="CommentsServlet">Contact Us</a>
                    <a href="#">Disclaimer</a>
                
            </div>
        
        </div>
	</div>

</body>

<link href="${pageContext.request.contextPath}/js/script.js" rel="stylesheet"/>

</html>