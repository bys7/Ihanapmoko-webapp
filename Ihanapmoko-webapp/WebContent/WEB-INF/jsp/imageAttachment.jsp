<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>

<form id="formAttachment" method="post" action="AdvertisementAttachmentServlet" enctype= multipart/form-data>
	<c:forEach items="${lstImages}" var="lstImages">
		<img src="<c:url value="/images/temp/${lstImages.picture_destination}"/>" />
		<input type="text" value="${lstImages.picture_destination}" />
	</c:forEach>
	<img src="/DSC_0068.jpg" />
	Sample Picture: <input accept="image/*" type="file" name="primaryPicture"><br/>		
	Sample Picture: <input accept="image/*" type="file" name="displayPictureOne"><br/>
	Sample Picture: <input accept="image/*" type="file" name="displayPictureTwo"><br/>
	<input type="submit" value="Attach" />
</form>
