package com.ihanapmoko.helper;

import java.util.Date;

import org.apache.commons.httpclient.NameValuePair;
import org.codehaus.jettison.json.JSONObject;

import com.ihanapmoko.bean.Comments;

public class IhanapmokoCommentsHelper {

	public Comments addNewComment(String fullname, String email_address, String message){
		
		ServiceFactory serviceFactory = new ServiceFactory();
    	JSONObject jsonResult = new JSONObject();
		Comments comments 	= null;
//		Date date			= new Date();
		
		
		try{
			comments = new Comments();
			comments.setFullname(fullname);
			comments.setEmail_address(email_address);
			comments.setMessage(message);
			comments.setDate_sent(new Date());
			comments.setRead_marker(ConstantsUtil.COMMENTS_MARK_UNREAD);
			
			String stringComments = serviceFactory.parseObject(comments);
			
			NameValuePair[] params = new NameValuePair[2];
	  		params[0] = new NameValuePair();
	  		params[0].setName("serviceMethod");
	  		params[0].setValue(ServiceMethodNames.CREATE_COMMENT);
	  		params[1] = new NameValuePair();
			params[1].setName("stringComments");
			params[1].setValue(stringComments);
			
			AuthBean authBean = new AuthBean(
	  				Configurator.getConfig("AUTH_USERNAME"),
	  				Configurator.getConfig("AUTH_PASSWORD"),
	  				Configurator.getConfig("SECRET_KEY"));
	  		
	  		jsonResult = serviceFactory.HttpClientGetJSON(authBean, params,
	  				ServiceMethodNames.CREATE_COMMENT);
	  		
	  		ServiceResult sr = (ServiceResult) serviceFactory.getMapper(
	  		  		ServiceResult.class, jsonResult.toString());
			
	  		comments = (Comments) serviceFactory.getMapper(Comments.class, sr.getObj().toString());
	  		
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return comments;
		
	}
	
}
