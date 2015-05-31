<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>


<html>

<style type="text/css">
<%@ include file="/WEB-INF/css/ihanapmoko.css" %>
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.8.3.min.js"></script> 
<link href="${pageContext.request.contextPath}/js/jquery-ui.min.js" rel="stylesheet"/>
<script type='text/javascript'>	

		$(document).ready(function(){ 
			$('#submitButton').click(function() {
				$('#formInformation').submit();
				//$('#formAttachment').submit();
			});		
			//loadWindow(imgCnt);
			
		});
	
	function loadWindow(imgCnt){
		//var lstFileNameSize = $("#lstFileNameSize").val();
		
		var img = [], i;
		
		for(i = 1; i <= imgCnt; i++){
		
			img = document.getElementById('imageSize_' + i);
			var imgWidth = img.naturalWidth;
			var imgHeight = img.naturalHeight;
			
			if(imgHeight > imgWidth){
				$('#imageSize_' + i).width(150);
				$('#imageSize_' + i).height(200);
			}else{
				$('#imageSize_' + i).width(300);
				$('#imageSize_' + i).height(200);
			}
		}
		
	}
	
	var loadFile = function(event) {
	    var output = document.getElementById('output');
	    output.src = URL.createObjectURL(event.target.files[0]);
	  };
</script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>IhanapMoko</title>

</head>
<body onload="loadWindow('${lstFileNameSize}')">


<form id="formInformation" method="post" action="AdvertisementServlet"  enctype="multipart/form-data">

<c:choose>
	<c:when test="${not empty advertisementIterate}">
		<c:forEach items="${advertisementIterate}" var="advertisementBean">
			Looking for: <c:out value="${advertisementBean.advertisement }"/><input name="advertisement" value="${advertisementBean.advertisement }" type="hidden"><br/>
			Category:<c:out value="${cateogoryName}" /><input name="category_Id" value="${advertisementBean.category_id}" type="hidden"><br/>		
			Contact Number: <c:out value="${advertisementBean.contact_number}" /><input name="contact_number" value="${advertisementBean.contact_number}" type="hidden"><br/>
			Private: <c:out value="${advertisementBean.number_is_private}" /><input name="number_is_private" value="${advertisementBean.number_is_private}" type="hidden"><br/>
			Email Address: <c:out value="${advertisementBean.email_address}" /><input name="email_address" value="${advertisementBean.email_address}" type="hidden"><br/>
			Location:<c:out value="${loationName}" /><input name="location_id" value="${advertisementBean.location_id}" type="hidden"><br/>
			Budget: <c:out value="${advertisementBean.budget}" /><input name="budget" value="${advertisementBean.budget}" type="hidden"><br/>		
			Description: <c:out value="${advertisementBean.description}" /><input name="description" value="${advertisementBean.description}" type="hidden"><br/>
			Condition:<c:out value="${advertisementBean.item_condition}" /><input name="item_condition" value="${advertisementBean.item_condition}" type="hidden"><br/>	
				Sample Picture: 
				<c:forEach items="${lstFileName}" var="lstFileName">
					<c:set var="fileLoop" value="${fileLoop + 1}" />
					<div class="advertisementPicture">
						<img id="imageSize_${fileLoop}" src="/Ihanapmoko-webapp/AdvertisementAttachmentServlet?imageName=${lstFileName}"/>
						<input type="radio" name="displayPhoto" value="${lstFileName}" />
					</div>
					<c:choose>
						<c:when test="${lstFileNameSize == fileLoop}">
							<c:set var="delimiterFileName" value="${delimiterFileName }${lstFileName}"/>
						</c:when>
						<c:otherwise>
							<c:set var="delimiterFileName" value="${delimiterFileName}${lstFileName}|"/>
						</c:otherwise>
					</c:choose>
					
				</c:forEach>						
				<input name="delimiterFileName" type="hidden" value="${delimiterFileName}" />
				<input id="lstFileNameSize" name="lstFileNameSize" type="hidden" value="${lstFileNameSize}" />
			<br/>
			Password: <input type="password" name="password"><br/>
			Confirm Password: <input type="password" name="confirmPassword"><br/>
			<input type="submit" value="Post"/>
		</c:forEach>
	</c:when>
	<c:otherwise>
		Looking for: <input type="text" name="advertisement"/><br/>
		Category:
			<select name="category_Id">
				<c:forEach var="allCategory" items="${allCategory}">
					<option value="${allCategory.id}" >${allCategory.category}</option>
				</c:forEach>
			</select><br/>
		Contact Number: <input type="text" name="contact_number"/><br/>
		Private: <input type="checkbox" name="number_is_private"/><br/>
		Email Address: <input type="text" name="email_address"/><br/>
		Location:
			<select name="location_id">
				<c:forEach var="allLocation" items="${allLocation}">
					<option value="${allLocation.id}" >${allLocation.location}</option>
				</c:forEach>
			</select><br/>
		Budget: <input type="text" name="budget"/><br/>
		Description: <input type="text" name="description"/><br/>
		Condition:
			<select name="item_condition">
				<option value="Brand New">Brand New</option>
				<option value="Used">Used</option>
			</select><br/>	
			Sample Picture: <input accept="image/*" type="file" name="displayPictureTwo" multiple ><br/>
		<input type="submit" value="Next"/>
	</c:otherwise>
</c:choose>


</form>

</body>
</html>