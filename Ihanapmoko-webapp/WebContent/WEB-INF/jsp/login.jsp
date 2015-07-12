<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>IhanapMoko</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/ihanap.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/margin.css">
<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootbox.min.js"></script>
<script src="${pageContext.request.contextPath}/js/login.js"></script>
</head>
<body>
    <div class="ihanapMain">
    	<div class="header">
        	<div class="width960">        		
                <a href="Home"><span class="logo pullLeft"></span></a>
                <span class="clear"></span>
            </div>
        </div>
        <!----Body----->
        <div class="body padtop40 padbot20">
        	<div class="width960">
        	<form id="registration-form" action="RegistrationServlet" method="post">
                <div class="loginBox width50P pullLeft borderRight padbot20">
                	<c:if test="${not empty registrationErrorMessage}">
                		<p class="errorMessage" ><c:out value="${registrationErrorMessage}" /></p>
                	</c:if>
                       <h1 class="textHead mbot10">Sign Up</h1>
                        
                        <p id="mandatoryMessage" class="errorMessage" style="display:none;">Please fill out mandatory field(s).</p>
                        
                        <label class="required">Email Address</label>
                        <input name="regEmail" type="text" placeholder="place your email here"/>
                        
                        <label class="required">First name</label>
                        <input name="regFirstName" type="text"/>
                        
                        <label class="required">Middle name</label>
                        <input name="regMiddleName" type="text"/>
                        
                        <label class="required">Last name</label>
                        <input name="regLastName" type="text"/>
                        
                        <label class="required">Contact Number</label>
                        <input name="regContactNumber" type="text"/>
                        
                        <span class="clear"></span>
                         
                        <p id="regPasswordMatch" class="errorMessage" style="display:none;">Password did not match.</p>
                        <p id="regPasswordShort" class="errorMessage" style="display:none;">Password is too short.</p>
                        
                        <label class="required">Password</label>
                        <input id="regPassword" name="regPassword"  type="password"/>
                        
                        <label class="required">Confirm Password</label>
                        <input id="regConfirmPassword" name="regConfirmPassword" type="password"/>
                        
                        <span class="clear"></span>
                        <button id="registration-form-btn" type="button" class="buttonOrange pullRight mtop20 mright20">Register</button>
                    	
                        <span class="clear"></span>
                </div>
                 </form>
                 <form id="login-form" action="LoginServlet" method="post">
                 	<input type="hidden" name="previousPage" value="${previousPage}" />
	            	<div class="loginBox width50P pullRight padbot20">
	                	<h1 class="textHead mbot10">Login</h1>
	                	<c:if test="${not empty errorMessage}">
	                		<p id="invalidMessage" class="errorMessage" ><c:out value="${errorMessage}" /></p>
	                	</c:if>
	                     <p id="loginInvalidInput" class="errorMessage" style="display:none;">Invalid input.</p>
	                    <label>
	                    	Email Address
	                    </label>
	                    <input name="username" type="text"/>
	                    <label>
	                    	Password
	                    </label>
	                    <input name="password" type="password"/>
	                    <span class="clear"></span>
	                    <button id="login-form-btn" class="buttonOrange pullRight mtop20 mright20">Login</button>
	                
	                	<span class="clear"></span>
	                </div>
            	</form>
                <span class="clear"></span>
            
            </div>
        </div>
    	<!-----Footer----->
        <div class="footer">
        	<div class="width960 cenText">

                	<a href="#">About us</a>
                    <a href="Comments">Contact Us</a>
                    <a href="#">Disclaimer</a>
                
            </div>
        
        </div>
    
    </div>
</body>
</html>