<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<script type="text/javascript" src="http://code.jquery.com/jquery-1.8.3.min.js"></script> 
<style type="text/css">
	<%@ include file="/WEB-INF/css/margin.css" %>
	<%@ include file="/WEB-INF/css/ihanap.css" %>
</style>
<script type='text/javascript'>	

	$(document).ready(function(){ 
		$('#submitComment').click(function() {
			$('#sendComment').submit();
			//$('#formAttachment').submit();
		});		
		//loadWindow(imgCnt);
		
	});

</script>

<link href="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js" rel="stylesheet"/>
<link href="${pageContext.request.contextPath}/js/bootstrap.min.js" rel="stylesheet"/>
<link href="${pageContext.request.contextPath}/js/bootbox.min.js" rel="stylesheet"/>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Ihanapmoko</title>
</head>
<body>

<form id="sendComment" method="post" action="CommentsServlet">
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
        <div class="footer">
        	<div class="width960 cenText">

                	<a href="#">About us</a>
                    <a href="#">Contact Us</a>
                    <a href="#">Disclaimer</a>
                
            </div>
        
        </div>
	</div>




<input type="submit" value="Submit">

</form>

</body>

<link href="${pageContext.request.contextPath}/js/script.js" rel="stylesheet"/>
</html>