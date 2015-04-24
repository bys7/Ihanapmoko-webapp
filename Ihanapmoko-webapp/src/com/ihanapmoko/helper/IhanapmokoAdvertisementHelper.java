package com.ihanapmoko.helper;

import org.apache.commons.httpclient.NameValuePair;
import org.codehaus.jettison.json.JSONObject;

import com.ihanapmoko.bean.Advertisement;

public class IhanapmokoAdvertisementHelper {

	public Advertisement createAdvertisement(Advertisement advertisementBean){
		
		Advertisement advertisement = null;
		
		ServiceFactory serviceFactory = new ServiceFactory();
    	JSONObject jsonResult = new JSONObject();
    	
    	try{
    		advertisement = new Advertisement();
    		
    		String strAdvertisement = serviceFactory.parseObject(advertisementBean);
    		
    		NameValuePair[] params = new NameValuePair[2];
	  		params[0] = new NameValuePair();
	  		params[0].setName("serviceMethod");
	  		params[0].setValue(ServiceMethodNames.CREATE_ADVERTISEMENT);
	  		params[1] = new NameValuePair();
			params[1].setName("strAdvertisement");
			params[1].setValue(strAdvertisement);
			
			AuthBean authBean = new AuthBean(
	  				Configurator.getConfig("AUTH_USERNAME"),
	  				Configurator.getConfig("AUTH_PASSWORD"),
	  				Configurator.getConfig("SECRET_KEY"));
	  		
	  		jsonResult = serviceFactory.HttpClientGetJSON(authBean, params,
	  				ServiceMethodNames.CREATE_ADVERTISEMENT);
	  		
	  		ServiceResult sr = (ServiceResult) serviceFactory.getMapper(
	  		  		ServiceResult.class, jsonResult.toString());
	  		
	  		advertisement = (Advertisement) serviceFactory.getMapper(Advertisement.class, sr.getObj().toString());
    		
    	}catch(Exception e){
    		e.printStackTrace();
    	}
		
		return advertisement;
	}
	
}
