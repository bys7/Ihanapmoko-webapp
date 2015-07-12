package com.ihanapmoko.helper;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.NameValuePair;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import com.ihanapmoko.bean.User;

public class IhanapmokoUserHelper {

	public User getUserViaEmailAndPassword(String email_address, String password){
		
		User userResult = new User();
		
		ServiceFactory serviceFactory = new ServiceFactory();
		JSONObject jsonResult = new JSONObject();
		
		try{
			NameValuePair[] params = new NameValuePair[3];
	  		params[0] = new NameValuePair();
	  		params[0].setName("serviceMethod");
	  		params[0].setValue(ServiceMethodNames.GET_EMAIL_AND_PASSWORD);
	  		params[1] = new NameValuePair();
			params[1].setName("email_address");
			params[1].setValue(email_address);	
			params[2] = new NameValuePair();
			params[2].setName("password");
			params[2].setValue(password);	  		
			
	  		AuthBean authBean = new AuthBean(
	  				Configurator.getConfig("AUTH_USERNAME"),
	  				Configurator.getConfig("AUTH_PASSWORD"),
	  				Configurator.getConfig("SECRET_KEY"));
	  		
	  		jsonResult = serviceFactory.HttpClientGetJSON(authBean, params,
	  				ServiceMethodNames.GET_EMAIL_AND_PASSWORD);
	  		
	  		ServiceResult sr = (ServiceResult) serviceFactory.getMapper(
	  		  		ServiceResult.class, jsonResult.toString());
	  		
	  		System.out.println("SR MESSAGE RESPONSE: " + sr.getDescription());
	  		
	  		if (sr.getStatus() == ConstantsUtil.SR_STATUS_OK) {
		  		
		  		userResult = (User) serviceFactory.getMapper(User.class, sr.getObj().toString());
	  		}
	  		
	  		
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return userResult;
		
	}
	
	public User saveUser(User user){
		
		JSONObject jsonResult;
		ServiceFactory serviceFactory = new ServiceFactory();
		String stringObject = serviceFactory.parseObject(user);
		User savedUser = new User();
		
		NameValuePair[] params = new NameValuePair[2];
		params[0] = new NameValuePair();
		params[0].setName("serviceMethod");
		params[0].setValue(ServiceMethodNames.CREATE_USER);		
		params[1] = new NameValuePair();
		params[1].setName("bean");
		params[1].setValue(stringObject);
		
		try {
			
			AuthBean authBean = new AuthBean(Configurator.getConfig("AUTH_USERNAME"), Configurator.getConfig("AUTH_PASSWORD") ,  Configurator.getConfig("SECRET_KEY"));
		    jsonResult = serviceFactory.HttpClientGetJSON(authBean, params, ServiceMethodNames.CREATE_USER);
		    ServiceResult serviceResult = (ServiceResult) serviceFactory.getMapper(ServiceResult.class, jsonResult.toString());
		    System.out.println("status: "+serviceResult.getStatus());
		    System.out.println("description: "+serviceResult.getDescription());
		    System.out.println("object: "+serviceResult.getObj());
		    if (serviceResult.getStatus() == ConstantsUtil.SR_STATUS_OK) {
		    	savedUser = (User) serviceFactory.getMapper(User.class, serviceResult.getObj().toString());
		    }
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return savedUser;
		
		
	}

	public User getUserById(int id) {
		
        User user = new User();
		ServiceFactory serviceFactory = new ServiceFactory();
		JSONObject jsonResult = new JSONObject();
		NameValuePair[] params = new NameValuePair[2];
  		params[0] = new NameValuePair();
  		params[0].setName("serviceMethod");
  		params[0].setValue(ServiceMethodNames.GET_USER_BY_ID);
  		params[1] = new NameValuePair();
		params[1].setName("id");
		params[1].setValue(Integer.toString(id));	
		
		try{

	  		AuthBean authBean = new AuthBean(
	  				Configurator.getConfig("AUTH_USERNAME"),
	  				Configurator.getConfig("AUTH_PASSWORD"),
	  				Configurator.getConfig("SECRET_KEY"));
	  		
	  		jsonResult = serviceFactory.HttpClientGetJSON(authBean, params,
	  				ServiceMethodNames.GET_USER_BY_ID);
	  		
	  		ServiceResult sr = (ServiceResult) serviceFactory.getMapper(
	  		  		ServiceResult.class, jsonResult.toString());
	  		
	  		System.out.println("SR MESSAGE RESPONSE: " + sr.getDescription());
	  		
	  		if (sr.getStatus() == ConstantsUtil.SR_STATUS_OK) {

		  		user = (User) serviceFactory.getMapper(User.class, sr.getObj().toString());
	  		}
	  		
	  		
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return user;
	}

	public void updateUser(User user) {
		
		JSONObject jsonResult = new JSONObject();
		ServiceFactory serviceFactory = new ServiceFactory();
		String stringObject = serviceFactory.parseObject(user);

		NameValuePair[] params = new NameValuePair[2];
		params[0] = new NameValuePair();
		params[0].setName("serviceMethod");
		params[0].setValue(ServiceMethodNames.UPDATE_USER);
		params[1] = new NameValuePair();
		params[1].setName("bean");
		params[1].setValue(stringObject);

		try {
			AuthBean authBean = new AuthBean(Configurator.getConfig("AUTH_USERNAME"), Configurator.getConfig("AUTH_PASSWORD") ,  Configurator.getConfig("SECRET_KEY"));
		    jsonResult = serviceFactory.HttpClientGetJSON(authBean, params, ServiceMethodNames.UPDATE_USER);
		    ServiceResult serviceResult = (ServiceResult) serviceFactory.getMapper(ServiceResult.class, jsonResult.toString());
		    System.out.println("status: "+serviceResult.getStatus());
		    System.out.println("description: "+serviceResult.getDescription());
		    System.out.println("object: "+serviceResult.getObj());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	public void checkUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		User user 			= null;
		String user_id 		= null;
		String firstname	= null;
		
		IhanapmokoUserHelper userHelper = new IhanapmokoUserHelper();
		
		
		try{
			user			= new User();
			user_id 		= request.getSession(false).getAttribute("user_id").toString();
			user 			= userHelper.getUserById(Integer.valueOf(user_id));
			
			if(user!=null){
				firstname 	= user.getFirstname();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		request.setAttribute("firstname", firstname);
		request.setAttribute("user_id", user_id);
		
	}
	
	public String fetchJspByServlet(String servletName){
		String jsp = null;
		
		try{
			if(servletName.equals(ConstantsUtil.SERVLET_ADVERTISEMENT)){
				jsp = "advertisement.jsp";
			}else if(servletName.equals(ConstantsUtil.SERVLET_DASHBOARD)){
				jsp = "dashboard.jsp";
			}else if(servletName.equals(ConstantsUtil.SERVLET_LOGIN)){
				jsp = "login.jsp";
			}else if(servletName.equals(ConstantsUtil.SERVLET_SEARCH)){
				jsp = "searchresult.jsp";
			}else if(servletName.equals(ConstantsUtil.SERVLET_HOME)){
				jsp = "index.jsp";
			}else if(servletName.equals(ConstantsUtil.SERVLET_COMMENT)){
				jsp = "sendcomment.jsp";
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return jsp;
	}
	
	public int fetchUserIdBySession(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		User user 			= null;
		String user_id 		= null;
		
		int intUserId		= 0;
		
		IhanapmokoUserHelper userHelper = new IhanapmokoUserHelper();
		
		
		try{
			user			= new User();
			user_id 		= request.getSession(false).getAttribute("user_id").toString();
			user 			= userHelper.getUserById(Integer.valueOf(user_id));
			
			if(user!=null){
				intUserId 	= user.getId();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return intUserId;
		
	}
	
	public User validateUserViaEmail(String email_address){
		
		User userResult = new User();
		
		ServiceFactory serviceFactory = new ServiceFactory();
		JSONObject jsonResult = new JSONObject();
		
		try{
			NameValuePair[] params = new NameValuePair[3];
	  		params[0] = new NameValuePair();
	  		params[0].setName("serviceMethod");
	  		params[0].setValue(ServiceMethodNames.VALIDATE_EMAIL);
	  		params[1] = new NameValuePair();
			params[1].setName("email_address");
			params[1].setValue(email_address);  		
			
	  		AuthBean authBean = new AuthBean(
	  				Configurator.getConfig("AUTH_USERNAME"),
	  				Configurator.getConfig("AUTH_PASSWORD"),
	  				Configurator.getConfig("SECRET_KEY"));
	  		
	  		jsonResult = serviceFactory.HttpClientGetJSON(authBean, params,
	  				ServiceMethodNames.VALIDATE_EMAIL);
	  		
	  		ServiceResult sr = (ServiceResult) serviceFactory.getMapper(
	  		  		ServiceResult.class, jsonResult.toString());
	  		
	  		System.out.println("SR MESSAGE RESPONSE: " + sr.getDescription());
	  		
	  		if (sr.getStatus() == ConstantsUtil.SR_STATUS_OK) {
		  		
		  		userResult = (User) serviceFactory.getMapper(User.class, sr.getObj().toString());
	  		}
	  		
	  		
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return userResult;
		
	}
	
}
