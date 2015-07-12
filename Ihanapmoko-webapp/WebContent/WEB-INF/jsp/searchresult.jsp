<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/margin.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/ihanap.css">

<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootbox.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.timeago.js"></script>

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
	
	jQuery(document).ready(function() {
	  	jQuery("abbr.timeago").timeago();
	});

</script>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Ihanapmoko</title>
</head>
<body onload="loadWindow('${lstFileNameSize}')">
	
	<div style="display:nont;"><fmt:formatDate var="currentDate" pattern="M-dd-yyyy" value="${currentDate}" /></div>
	
	<div class="ihanapMain">
		<%@ include file="/WEB-INF/jsp/header.jsp" %>
        
        <div class="body padtop40 padbot20">
        	<div class="width960">
        		<div class="leaderboard">
		        	<iframe src="https://storage.googleapis.com/support-kms-prod/SNP_501E7C3D5CA3CA07C641E6BAFB8A53C794CF_2922339_en_v2" frameborder="0" width="100%" height="100%" scrolling="no"></iframe>
		        </div>	
		        
		        <div class="thumbnails mtop20">
		        	<div class="search">
		        		<form id="searchForm" method="post" action="Search">
							Ito ang mga naghahanap ng 
								<input type="text" name="searchParameter" value="${searchParameter}">
								Location								                			
	                			<select name="location_id">
	                				<option value="">  </option>
									<c:forEach var="allLocation" items="${allLocation}">
										<option value="${allLocation.id}" ${allLocation.id == selectedLocation ? 'selected' : ''}>${allLocation.location}</option>
									</c:forEach>
								</select>
								Category
								<select name="category_id">
									<option value="">  </option>
									<c:forEach var="allCategory" items="${allCategory}">
										<option value="${allCategory.id}" ${allCategory.id == selectedCategory ? 'selected' : ''}>${allCategory.category}</option>
									</c:forEach>
								</select>				
							<button id="searchButton" class="buttonBlue">Search</button>	<br/>
							<c:if test="${not empty searchParameter}">
								<c:out value="${lstFileNameSize}"/> result for "<c:out value="${searchParameter}"/>"
							</c:if>								
						</form> 
		        	</div>
		        	<div class="gallery">        	
		        		<ul>
		        			<c:forEach items="${lstSearchResult}" var="searchBean" >
		        				<c:set var="fileLoop" value="${fileLoop + 1}" />        				
		        				<li>        					
		        					<a href="Dashboard?dashboardId=${searchBean.advertisementId}" class="imgHolder">
		        						<img id="imageSize_${fileLoop}" src="/SearchImageServlet?imageName=${searchBean.picture_destination}"/>
		        					       					
		       							<p class="mbot10 mtop10"><b><c:out value="${searchBean.advertisement}"/></b></p>
		       						</a> 
		       						<p class="mbot10 mtop10"><span><c:out value="${searchBean.location}"/></span>
		       						|<span><abbr class="timeago" title="<fmt:formatDate pattern="yyyy-MM-dd" value="${searchBean.date_created}"/>T<fmt:formatDate pattern="HH:mm:ss" value="${searchBean.date_created}"/>"></abbr></span></p>        					
		        				</li>        				
		        			</c:forEach>
		        		</ul>
		        	</div>
		        	<c:out value="${searchPagination}" escapeXml="false" />
		        </div>
        	</div>       
        	<span class="clear"></span>
       		
	       	<div class="leaderboard">
	        	<iframe src="https://storage.googleapis.com/support-kms-prod/SNP_501E7C3D5CA3CA07C641E6BAFB8A53C794CF_2922339_en_v2" frameborder="0" width="100%" height="100%" scrolling="no"></iframe>
	        </div> 	
        </div>
        
        
        
         <!-----Footer----->
        <%@ include file="/WEB-INF/jsp/footer.jsp" %>
	</div>

</body>

<link href="${pageContext.request.contextPath}/js/script.js" rel="stylesheet"/>

</html>