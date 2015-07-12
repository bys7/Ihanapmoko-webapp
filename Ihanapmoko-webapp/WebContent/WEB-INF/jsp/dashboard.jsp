<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/margin.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/ihanap.css">

<script src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.js"></script>

<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootbox.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.timeago.js"></script>

<script language='Javascript'>	

	$(document).ready(function(){ 
		$('#editButton').click(function(){
			$('#editDiv').show();
			$('#displayDiv').hide();
		});
		$('#cancelButton').click(function(){
			$('#editDiv').hide();
			$('#displayDiv').show();
		});		
		$('#updateButton').click(function(){
			if(editValidate()){
				$('.passwordMatch').hide();
				$('#formEdit').submit();
			}else{				
				$('.passwordMatch').show();
			}			
		});		
		$('#deleteButton').click(function(){
			$('#deleteDiv').show();
			$('#deleteButtons').show();
			$('#editButtons').hide();
		});
		$('#cancelDelete').click(function(){
			$('#deleteDiv').hide();
			$('#deleteButtons').hide();
			$('#editButtons').show();
		});
		$('#deleteButtonNow').click(function(){
			if(deleteValidate()){
				$('.passwordMatch').hide();
				$('#formDelete').submit();
			}else{
				$('.passwordMatch').show();
			}
			
		});	
		
	});

	function loadWindow(imgCnt){		
		var img = [], i;
		
		for(i = 1; i <= imgCnt; i++){
		
			img = document.getElementById('imageSize_' + i);
			var imgWidth = img.naturalWidth;
			var imgHeight = img.naturalHeight;
			
			if(imgHeight > imgWidth){
				$('#imageSize_' + i).width(140);
				$('#imageSize_' + i).height(200);
			}else{
				$('#imageSize_' + i).width(300);
				$('#imageSize_' + i).height(200);
			}
		}
		
	}
	
	jQuery(document).ready(function() {
	  	jQuery("abbr.timeago").timeago();
	});

	function editValidate(){
		var password 			= $('#updatePassword').val();
		var confirmPassword 	= $('#validateUpdatePassword').val();
		
		var noError				= false;
				
		if(password === confirmPassword){
			noError	= true;
		}else{
			noError = false;
		}
		
		return noError;
	}
	
	function deleteValidate(){
		var password 			= $('#deletePassword').val();
		var confirmPassword 	= $('#validateDeletePassword').val();
		
		var noError				= false;
				
		if(password === confirmPassword){
			noError	= true;
		}else{
			noError = false;
		}
		
		return noError;
	}
	
</script>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>IhanapMoko</title>
</head>
<body onload="loadWindow('${biddingPictureSize}')">

	<div class="ihanapMain">
		<%@ include file="/WEB-INF/jsp/header.jsp" %>  
	     
	     <div class="body padtop40 padbot20">
	     	<div class="width960">
	     		<div class="mtop20">
	     			<div class="search">
		        		<form id="searchForm" method="post" action="Search">
							Ano hanap mo? <input type="text" name="searchParameter" value="${searchParameter}">					
							<button id="searchButton" class="buttonBlue">Search</button>									
						</form> 
		        	</div>
		        	<c:if test="${not empty errorMessage}">
		        		<p id="invalidMessage" class="errorMessage">You don't have a permission to edit/delete this advertisement. Please input correct password.</p>
		        	</c:if>
		        	<p class="errorMessage passwordMatch" style="display:none;">Password did not match.</p>
		        	<div class="productView mtop40">
	                   	<div class="pullLeft width30P imageBox">
	                       	<img id="big" src="/SearchImageServlet?imageName=${dashboard.picture_destination}">	
	                           <div id="thumbnailsProduct" class="mtop20">
									<c:forEach items="${pictureGallery}" var="pictureGallery">
										<a href="/SearchImageServlet?imageName=${pictureGallery.picture_destination}"><img src="/SearchImageServlet?imageName=${pictureGallery.picture_destination}"></a>
									</c:forEach>                      		                         		
	                           </div> 
	                       </div>
	                       <div class="pullRight information">
	                       
	                       	<h2>Ang hinahanap ko ay:  <span><c:out value="${dashboard.advertisement}" /></span></h2>	                           
	                           <div id="displayDiv">
		                           <p><b>Views:</b>	<span class="mleft20"><c:out value="${dashboard.views}" /></span></p>
		                           <p><b>Category:</b>	<span class="mleft20"><c:out value="${dashboard.category}" /></span></p>
		                           <c:if test="${not empty dashboard.budget}">
		                           		<p><b>Est Price:</b>	<span class="mleft20">Php <fmt:formatNumber value="${dashboard.budget}" pattern="#,##0"/></span></p>
		                           </c:if>		                           
		                           <p><b>Item Condition:</b>	<span class="mleft20"><c:out value="${dashboard.item_condition}" /></span></p>
		                           <p><b>Location:</b>	<span class="mleft20"><c:out value="${dashboard.location}" /></span></p>
		                           	<c:if test="${dashboard.number_is_private == 0 }">
		                           		<p><b>Contact:</b>	<span class="mleft20"><b><c:out value="${dashboard.contact_number}" /></b></span></p>
		                           	</c:if>		                           
		                           <p><b>Email Address:</b>	<span class="mleft20"><c:out value="${dashboard.email_address}" /></span></p>	                           
		                           <p><b>Date Posted:</b>	
		                           		<span class="mleft20"><abbr class="timeago" title="<fmt:formatDate pattern="yyyy-MM-dd" value="${dashboard.date_created}"/>T<fmt:formatDate pattern="HH:mm:ss" value="${dashboard.date_created}" />"></abbr></span>
		                           	</p>
		                           <c:if test="${not empty dashboard.description}" >
		                           		<p><b>Description:</b><span class="mleft20"><c:out value="${dashboard.description}" /></span></p>
		                           </c:if>
		                           
		                           <div id="deleteDiv" style="display:none">
		                           		<form id="formDelete" method="post" action="Dashboard" enctype="multipart/form-data">
		                           			<input name="dashboardCommand" type="hidden" value="delete"/> 
			                           		<input name="dashboardId" type="hidden" value="${dashboard.advertisement_id}"/>			                           		
			                           		<p>
				                           		<b>Password:<input id="deletePassword" type="password" name="password"></b>
				                           	</p>
				                           	<p>
				                           		<b>Confirm Password:</b><input id="validateDeletePassword" type="password" name="confirmPassword">
				                           	</p>
				                           	<div id="deleteButtons">				                           	
					                           	<p>				                           		
						                           	<button id="deleteButtonNow" type="button" class="buttonRed pullLeft edit">Delete</button>
						                           	<button id="cancelDelete" type="button" class="buttonRed pullRight delete">Cancel</button> 					                           	
					                           	</p> 
				                           	</div>                  		
		                           		</form>
		                           </div>
		                           <div id="editButtons">
			                           <p>
				                           <button id="editButton" type="button" class="buttonOrange pullLeft mtop20 edit">Edit</button>		                           
				                           <button id="deleteButton" type="button" class="buttonRed pullRight delete">Delete</button>
			                           </p>
			                       </div>
	                           </div>
	                           <div id="editDiv" style="display:none">
		                           <form id="formEdit" method="post" action="Dashboard" enctype="multipart/form-data">
		                           		<input name="dashboardCommand" type="hidden" value="edit"/> 
		                           		<input name="dashboardId" type="hidden" value="${dashboard.advertisement_id}"/>
		                           		<p><b>Ang hinahanap ko ay:</b>	<input name="advertisement" type="text" value="${dashboard.advertisement}" /> </p>
			                           	<p><b>Category:</b> 
			                           		<select name="category_id">
												<c:forEach var="allCategory" items="${allCategory}">
													<option value="${allCategory.id}" ${allCategory.category == allCategory.category ? 'selected' : ''} >${allCategory.category}</option>
												</c:forEach>
											</select>		                           		
			                           	</p>
			                           	<p><b>Est Price:</b> <input name="budget" type="text" value="${dashboard.budget}" /></p>
			                           	<p>
			                           		<b>Item Condition:</b>
			                           		<select name="item_condition">
			                           			<c:forEach var="conditionList" items="${conditionList}">
			                           				<option value="${conditionList}" ${conditionList == dashboard.item_condition ? 'selected' : ''}>${conditionList}</option>
			                           			</c:forEach>
			                           		</select>
			                           	</p>
			                           	<p><b>Location:</b>	
			                           		<select name="location_id">
												<c:forEach var="allLocation" items="${allLocation}">
													<option value="${allLocation.id}" ${allLocation.location == dashboard.location ? 'selected' : ''}>${allLocation.location}</option>
												</c:forEach>
											</select>
			                           	</p>
			                           	<c:choose>
			                           		<c:when test="${dashboard.number_is_private == 0 }">
			                           			<p><b>Contact:</b><input name="contact_number" type="text" value="${dashboard.contact_number}" /> </p>
			                           		</c:when>
			                           		<c:otherwise>
			                           			<input name="contact_number" type="hidden" value="${dashboard.contact_number}" />
			                           		</c:otherwise>
			                           	</c:choose>		                           	
			                           	<p>
			                           		<label class="customCheckbox" style="">
			                           			<input type="checkbox" name="number_is_private" value="${dashboard.number_is_private}" <c:if test="${dashboard.number_is_private == '1'}">checked="checked"</c:if>/><span class="checkbg"></span>			                           		
			                           		</label>Private
			                           	</p>		                          
			                           	<p><b>Email Address:</b>	<input name="email_address" type="text" value="${dashboard.email_address}" /> <span class="mleft20"><c:out value="" /></span></p>	                           
			                           
			                           	<p><b>Description:</b><textarea name="description" style="margin: 0px; height: 172px; width: 373px;">${dashboard.description}</textarea></p>
			                           	<p>
			                           		<b>Password:<input id="updatePassword" type="password" name="password"></b>
			                           	</p>
			                           	<p>
			                           		<b>Confirm Password:</b><input id="validateUpdatePassword" type="password" name="confirmPassword">
			                           	</p>
										<p>
			                           		<button id="updateButton" type="button" class="buttonOrange pullLeft mtop20 edit">Update</button>
			                           		<button id="cancelButton" type="button" class="buttonRed pullRight delete">Cancel</button>
		                           		</p>
		                           </form>
	                           </div>
	                           
	                           
	                       </div>
	                   	<span class="clear"></span>
	                 </div>
	                 
	                 <div class="bidBox mtop20">
	                 	<c:if test="${not empty biddingList }">
	                 		<h2>Offers</h2>
	                 	</c:if>
	                 		                 	
	                 	<div>
	                 		<ul>
	                 			<c:forEach items="${biddingList}" var="biddingList">
	                 				<li>
	                 					<p class="bidname pullLeft"><b><c:out value="${biddingList.firstname}"/> <c:out value="${biddingList.middlename}" /> <c:out value="${biddingList.lastname}"/></b></p>
	                 					<p class="despcription pullLeft">
	                 						<c:out value="${biddingList.message}" /><br/>
	                 						<c:if test="${not empty biddingList.picture_destination}">
	                 							<c:set var="fileLoop" value="${fileLoop + 1}" />
	                 							<c:if test="${not empty biddingList.picture_destination}">
	                 								<img id="imageSize_${fileLoop}" src="/SearchImageServlet?imageName=${biddingList.picture_destination}"/>
	                 							</c:if>
	                 						</c:if>
	                 						<br/>
	                 						<abbr class="timeago" title="<fmt:formatDate pattern="yyyy-MM-dd" value="${biddingList.date_posted}"/>T<fmt:formatDate pattern="HH:mm:ss" value="${biddingList.date_posted}" />"></abbr>	                 					
	                 					</p>
	                 					<span class="clear"></span>
	                 				</li>
	                 			</c:forEach>
	                 		</ul>	                 	
	                 	</div>
	                 	<span class="clear"></span>
	                 </div>
	                 
	                 <form method="post" action="Dashboard"  enctype="multipart/form-data">
	                 	<input name="dashboardId" value="${dashboard.advertisement_id}" type="hidden" />
	                 	<div class="mtop20">
	                      	<h2>Ipost Mo Ibebenta Mo</h2>	
	                          <div class="pad20 bidNowBox">
		                          	<c:choose>
					        			<c:when test="${not empty user_id}">				        				
						        			  <div class="pullLeft">
				                                  <label>Photo upload</label>
				                                 
				                                  <input accept="image/*" type="file" name="displayPictureTwo" class="buttonOrange mtop10" />
				                              </div>
				                              <div class="pullLeft mleft20 textAreaBox">
				                                  <textarea name="message"></textarea>
				                                  
				                                  <button class="buttonBlue pullRight mtop10" >Best Offer</button>
				                              </div>				        			
					        			</c:when>
					        			<c:otherwise>
					        			  	<a href="login" ><button type="button" class="buttonOrange pullRight mtop20">Post an offer</button></a>
					        			</c:otherwise>
			        				</c:choose>
	                          	
	                              	<span class="clear"></span>
	                          </div>
	                      </div>
	                 </form>
	                 
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

<script src="${pageContext.request.contextPath}/js/script.js"></script> 

</html>