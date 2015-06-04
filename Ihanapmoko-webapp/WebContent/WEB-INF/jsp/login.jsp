<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
                <span class="logo pullLeft"></span>
                
              
                <span class="clear"></span>
            </div>
        </div>
        <!----Body----->
        <div class="body padtop40 padbot20">
        	<div class="width960">
        	<form id="registration-form" action="RegistrationServlet" method="post">
                <div class="loginBox width50P pullLeft borderRight padbot20">
                       <h1 class="textHead mbot10">Sign Up</h1>
                        
                        <label>Email</label>
                        <input name="regEmail" type="text"/>
                        
                        <label>First name</label>
                        <input name="regFirstName" type="text"/>
                        
                        <label>Middle name</label>
                        <input name="regMiddleName" type="text"/>
                        
                        <label>Last name</label>
                        <input name="regLastName" type="text"/>
                        
                        <label>Contact #</label>
                        <input name="regContactNumber" type="text"/>
                        
                         <span class="clear"></span>
                         
                        <label>Password</label>
                        <input name="regPassword" type="text"/>
                        
                        <label>Confirm Password</label>
                        <input name="regConfirmPassword" type="text" onChange="checkPasswordMatch();"/>
                        
                        <span class="clear"></span>
                        <button id="registration-form-btn" class="buttonOrange pullRight mtop20 mright20">Register</button>
                    	
                        <span class="clear"></span>
                </div>
                 </form>
            	<div class="loginBox width50P pullRight padbot20">
                	<h1 class="textHead mbot10">Login</h1>
                    
                    <label>
                    	Username
                    </label>
                    <input type="text"/>
                    <label>
                    	Password
                    </label>
                    <input type="text"/>
                    <span class="clear"></span>
                    <button class="buttonOrange pullRight mtop20 mright20">Login</button>
                
                	<span class="clear"></span>
                </div>
            	
                <span class="clear"></span>
            
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
</body>
</html>