<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<script language=javascript type='text/javascript'>
	$(document).ready(function(){ 
		$('#submitButton').click(function() {
			$('#formInformation').submit();
			//$('#formAttachment').submit();
		});	
	});
	
	 var loadFile = function(event) {
	    var output = document.getElementById('output');
	    output.src = URL.createObjectURL(event.target.files[0]);
	  };
</script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>IhanapMoko</title>

</head>
<body>
<img src="<c:url value="/images/temp/DSC_0019.jpg"/>" />
<form id="formInformation" method="post" action="AdvertisementServlet"  enctype="multipart/form-data">

Looking for: <input type="text" name="advertisement"><br/>
Category:
	<select name="category_Id">
		<c:forEach var="allCategory" items="${allCategory}">
			<option value="${allCategory.id}" >${allCategory.category}</option>
		</c:forEach>
	</select><br/>
Contact Number: <input type="text" name="contact_number"><br/>
Private: <input type="checkbox" name="number_is_private"><br/>
Email Address: <input type="text" name="email_address"><br/>
Location:
	<select name="location_id">
		<c:forEach var="allLocation" items="${allLocation}">
			<option value="${allLocation.id}" >${allLocation.location}</option>
		</c:forEach>
	</select><br/>
Budget: <input type="text" name="budget"><br/>
Password: <input type="password" name="password"><br/>
Confirm Password: <input type="password" name="confirmPassword"><br/>
Description: <input type="text" name="description"><br/>
Condition:
	<select name="item_condition">
		<option value="Brand New">Brand New</option>
		<option value="Used">Used</option>
	</select><br/>	
	Sample Picture: <input accept="image/*" type="file" name="displayPictureTwo" onchange="loadFile(event)" ><br/>
	<img id="output" /><br/>
<input type="submit" value="Post"/>
</form>
<iframe src="AdvertisementAttachmentServlet"></iframe>
</body>
</html>