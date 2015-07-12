<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>

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

<form id="sendComment" method="post" action="CommentsServlet">
	<div class="ihanapMain">
	
		<%@ include file="/WEB-INF/jsp/header.jsp" %>	
        
        <div class="body padtop40 padbot20">
        	<div class="width960">
            	
                <!---Thumbnails---->
            
            		<div class="mtop20 width65P pullLeft commentBox">
                    	<h1 class="textHead"> Comments and Suggestions</h1>
                       		 <label>Name</label>
                              <input type="text" name="fullname">
							
                        	 <label>Email</label>
                             <input type="text" name="email_address">
                              
                             <label>Message</label>
                             <textarea name="message"></textarea>
                        
                        	<button id="submitComment" class="buttonBlue pullRight mtop20">Send</button>
                    </div>
                    
                    <div class="skyscraper pullRight mtop20">
                    	<iframe src="https://lh4.ggpht.com/ike-jviZQ32RHuhkwLcAt_9vdpBX1oWKU00NX7QRe5GPl7-5sapzZ0u91_ssg_-Ednak2Hj-Hg=w162" frameborder="0" width="100%" height="100%" scrolling="no"></iframe>
                    
                    </div>
            </div>    
        </div>
        
        <!-----Footer----->
        <%@ include file="/WEB-INF/jsp/footer.jsp" %>
	</div>

</form>

</body>

<link href="${pageContext.request.contextPath}/js/script.js" rel="stylesheet"/>
</html>