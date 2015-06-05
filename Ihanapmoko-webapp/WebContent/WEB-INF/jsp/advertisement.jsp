<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>


<html>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/margin.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/ihanap.css">

<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootbox.min.js"></script>

<script language='Javascript'>	

		$(document).ready(function(){ 
			
			$('#nextAdvertisement').click(function() {
				if(validate()){
					$('#mandatoryMessage').show();
				}else{					
					$('#formInformation').submit();
				}
				
				//$('#formAttachment').submit();
			});	
			
			$('#postAdvertisement').click(function() {
					if(validatePassword()){
						$('#passwordMatch').show();						
					}else{
						$('#formInformation').submit();
					}
			});	
			
			$("#validateContactNumber").keydown(function(event){
			      // Allow: backspace, delete, tab, escape, and enter
			      if (event.keyCode == 8 || event.keyCode == 9 || event.keyCode == 27 || event.keyCode == 13 ||
			           // Allow: Ctrl+A
			          (event.keyCode == 65 && event.ctrlKey === true) || 
			           // Allow: home, end, left, right
			          (event.keyCode == 190 && event.shiftKey === false) || 
			          // Allow: period
			          (event.keyCode >= 35 && event.keyCode <= 39)) {
			               // let it happen, don't do anything
			               return;
			      }
			      else {
			          // Ensure that it is a number and stop the keypress
			          if (event.shiftKey || (event.keyCode < 48 || event.keyCode > 57) && (event.keyCode < 96 || event.keyCode > 105 )) {
			              event.preventDefault(); 
			          }   
			      }
			  });
			
			
		});
		
	
	
	function loadWindow(imgCnt){
		//var lstFileNameSize = $("#lstFileNameSize").val();
		
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
	
	function validate(){		
		var advertisement 	= 	$('#validateAdvertisement').val();
		var contactNumber	=	$('#validateContactNumber').val();
		var	emailAddress	=	$('#validateEmailAddress').val();
		
		var error 			= false;
		
		if(advertisement === '' || advertisement === null
				|| contactNumber === '' || contactNumber === null
				|| emailAddress === '' || emailAddress === null){
			error = true;
		}	
		return error;
	}
	
	function validatePassword(){
		var password		=	$("#validatePassword").val();
		var ConfirmPassword	=	$("#validateConfirmPassword").val();
		
		var error 			= false;
		
		if(password === ConfirmPassword){
			error = false;
		}else{
			error = true;
		}
		
		return error;
	}
	
</script>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>IhanapMoko</title>

</head>
<body onload="loadWindow('${lstFileNameSize}')">


<form id="formInformation" method="post" action="AdvertisementServlet"  enctype="multipart/form-data">

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
				<div class="mtop20 width77P pullLeft">
					<h1 class="textHead"> Ano Hanap Mo?</h1>
					<p id="mandatoryMessage" class="errorMessage" style="display:none;">Please fill out mandatory field/s.</p>
					<p id="invalidMessage" class="errorMessage" style="display:none;">Invalid input.</p>
					<p id="passwordMatch" class="errorMessage" style="display:none;">Password did not match.</p>
					<c:choose>
	               		<c:when test="${not empty advertisementIterate}">	
              					<c:forEach items="${advertisementIterate}" var="advertisementBean">	
              						<c:choose>
	               						<c:when test="${not empty advertisementBean.password}">
			               					An Email has been sent to you. To check your advertisement please visit your email. Click <a href="HomeServlet">here</a> to check other advertisement
			               				</c:when>
			               				<c:otherwise>
	              							<div class="loginBox width47P pullLeft padbot20">
												<label>Category</label>
												<div class="">
													<c:out value="${cateogoryName}" />
													<input name="category_Id" value="${advertisementBean.category_id}" type="hidden">
												</div>
												<label>Looking for</label>
												<c:out value="${advertisementBean.advertisement }"/><input name="advertisement" value="${advertisementBean.advertisement }" type="hidden">
												<label>Contact Number</label>
												<c:out value="${advertisementBean.contact_number}" /><input name="contact_number" value="${advertisementBean.contact_number}" type="hidden">
												<label>Private</label>
												<c:out value="${advertisementBean.number_is_private}" /><input name="number_is_private" value="${advertisementBean.number_is_private}" type="hidden">
												<label>Email Address</label> 
												<c:out value="${advertisementBean.email_address}" /><input name="email_address" value="${advertisementBean.email_address}" type="hidden">
												<label>Location</label>	 
												<c:out value="${loationName}" /><input name="location_id" value="${advertisementBean.location_id}" type="hidden">
												
											</div>	
											<div class="loginBox width47P pullRight padbot20">
												<c:if test="${not empty advertisementBean.budget}">
													<label>Budget</label>
													<c:out value="${advertisementBean.budget}" /><input name="budget" value="${advertisementBean.budget}" type="hidden">
												</c:if>
												<label>Condition</label>
												<c:out value="${advertisementBean.item_condition}" /><input name="item_condition" value="${advertisementBean.item_condition}" type="hidden">
												<c:if test="${not empty advertisementBean.description}">
													<label>Description</label>
													<c:out value="${advertisementBean.description}" /><input name="description" value="${advertisementBean.description}" type="hidden">
												</c:if>
												<c:if test="${not empty lstFileName}">
													<label>Choose Display Photos </label>
												</c:if>
												<div class="uploadimage">
													<ul>
														<c:forEach items="${lstFileName}" var="lstFileName">
															<c:set var="fileLoop" value="${fileLoop + 1}" />
															<li>														
																<div style="width: 176px; margin: 0 auto;">
																	<img id="imageSize_${fileLoop}" src="/Ihanapmoko-webapp/AdvertisementAttachmentServlet?imageName=${lstFileName}"/>
																	<input type="radio" name="displayPhoto" value="${lstFileName}" />
																</div>	
															</li>
															<c:choose>
																<c:when test="${lstFileNameSize == fileLoop}">
																	<c:set var="delimiterFileName" value="${delimiterFileName }${lstFileName}"/>
																</c:when>
																<c:otherwise>
																	<c:set var="delimiterFileName" value="${delimiterFileName}${lstFileName}|"/>
																</c:otherwise>
															</c:choose>																					
														</c:forEach>
														
													</ul>
													
												</div>
												<input type="hidden" name="delimiterFileName" value="${delimiterFileName}" />
												<label>Password</label>
												<input id="validatePassword" type="password" name="password"><br/>
												<label>Confirm Password</label>
												<input id="validateConfirmPassword" type="password" name="confirmPassword"><br/>
												<button id="postAdvertisement" type="button" class="buttonBlue mtop20 pullRight clear">Post</button>
												
											</div>
	              						</c:otherwise>
              						</c:choose>	
								<div class="clear"></div>								
							</c:forEach>              			
	               		</c:when>
	               		<c:otherwise>
	               			<div class="loginBox width47P pullLeft padbot20">
	                			<label>Category</label>
	                			<div class="">
	                				<select name="category_Id">
										<c:forEach var="allCategory" items="${allCategory}">
											<option value="${allCategory.id}" >${allCategory.category}</option>
										</c:forEach>
									</select>
	                			</div>
	                			<label class="required">Looking for</label>
	                			<input id="validateAdvertisement" type="text" name="advertisement"/>
	                			<label class="required">Contact Number</label>
	                			<input id="validateContactNumber" type="text" name="contact_number"/>
	                			<input type="checkbox" name="number_is_private"/><label>Private</label>
	                			<label class="required">Email Address</label> 
	                			<input id="validateEmailAddress" type="text" name="email_address"/>	                    			
	                			<label>Location</label>	                			
	                			<select name="location_id">
									<c:forEach var="allLocation" items="${allLocation}">
										<option value="${allLocation.id}" >${allLocation.location}</option>
									</c:forEach>
								</select>
							</div>
							<div class="loginBox width47P pullRight padbot20">
								
								<label>Budget</label>
								<input type="text" name="budget"/>
								<label>Condition</label>
								<select name="item_condition">
									<option value="Brand New">Brand New</option>
									<option value="Used">Used</option>
								</select>
								<label>Description</label>
								<textarea name="description" style="margin: 0px; height: 172px; width: 373px;"></textarea>
								<label>Photos </label>
								<input accept="image/*" type="file" name="displayPictureTwo" class="buttonOrange mtop10" multiple >
								<button id="nextAdvertisement" type="button" class="buttonBlue mtop20 pullRight clear">Next</button>
								<div class="clear"></div>		
								
										               		
							</div>
						</c:otherwise>
				    </c:choose> 	                         
				</div>
				<div class="skyscraper pullRight mtop20">
               	<iframe src="https://lh4.ggpht.com/ike-jviZQ32RHuhkwLcAt_9vdpBX1oWKU00NX7QRe5GPl7-5sapzZ0u91_ssg_-Ednak2Hj-Hg=w162" frameborder="0" width="100%" height="100%" scrolling="no"></iframe>
               </div>
               
			</div>                   
             	<div class="clear"></div>  		
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

</form>

</body>
<script src="${pageContext.request.contextPath}/js/script.js"></script> 
</html>