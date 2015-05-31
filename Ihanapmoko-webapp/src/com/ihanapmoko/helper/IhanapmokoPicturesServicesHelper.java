package com.ihanapmoko.helper;

import org.apache.commons.httpclient.NameValuePair;
import org.codehaus.jettison.json.JSONObject;

import com.ihanapmoko.bean.Pictures;
import com.ihanapmoko.bean.PicturesServices;

public class IhanapmokoPicturesServicesHelper {

	public PicturesServices createPictures(PicturesServices picturesBean){
		
		PicturesServices picturesServices = null;
		
		ServiceFactory serviceFactory = new ServiceFactory();
    	JSONObject jsonResult = new JSONObject();
    	
    	try{
    		picturesServices = new PicturesServices();
    		
    		String strPicturesServices = serviceFactory.parseObject(picturesBean);
    		
    		NameValuePair[] params = new NameValuePair[2];
	  		params[0] = new NameValuePair();
	  		params[0].setName("serviceMethod");
	  		params[0].setValue(ServiceMethodNames.CREATE_PICTURES_SERVICES);
	  		params[1] = new NameValuePair();
			params[1].setName("strPicturesServices");
			params[1].setValue(strPicturesServices);
			
			AuthBean authBean = new AuthBean(
	  				Configurator.getConfig("AUTH_USERNAME"),
	  				Configurator.getConfig("AUTH_PASSWORD"),
	  				Configurator.getConfig("SECRET_KEY"));
	  		
	  		jsonResult = serviceFactory.HttpClientGetJSON(authBean, params,
	  				ServiceMethodNames.CREATE_PICTURES_SERVICES);
	  		
	  		ServiceResult sr = (ServiceResult) serviceFactory.getMapper(
	  		  		ServiceResult.class, jsonResult.toString());
	  		
	  		picturesServices = (PicturesServices) serviceFactory.getMapper(PicturesServices.class, sr.getObj().toString());
    		
    	}catch(Exception e){
    		e.printStackTrace();
    	}
		
		return picturesServices;
	}
	
}
