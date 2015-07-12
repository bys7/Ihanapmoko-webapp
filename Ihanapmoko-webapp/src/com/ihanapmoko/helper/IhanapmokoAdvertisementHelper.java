package com.ihanapmoko.helper;

import org.apache.commons.httpclient.NameValuePair;
import org.codehaus.jettison.json.JSONObject;

import com.ihanapmoko.bean.Advertisement;
import com.ihanapmoko.bean.Category;

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
	
	public Advertisement fetchAdvertismentById(String id){
		Advertisement advertisement = new Advertisement();
		
		ServiceFactory serviceFactory = new ServiceFactory();
		JSONObject jsonResult = new JSONObject();
		
		try{
			NameValuePair[] params = new NameValuePair[2];
	  		params[0] = new NameValuePair();
	  		params[0].setName("serviceMethod");
	  		params[0].setValue(ServiceMethodNames.FETCH_ADVERTISEMENT_BY_ID);
	  		params[1] = new NameValuePair();
	  		params[1].setName("id");
	  		params[1].setValue(id);
	  		
	  		AuthBean authBean = new AuthBean(
	  				Configurator.getConfig("AUTH_USERNAME"),
	  				Configurator.getConfig("AUTH_PASSWORD"),
	  				Configurator.getConfig("SECRET_KEY"));
	  		
	  		jsonResult = serviceFactory.HttpClientGetJSON(authBean, params,
	  				ServiceMethodNames.FETCH_ADVERTISEMENT_BY_ID);
	  		
	  		ServiceResult sr = (ServiceResult) serviceFactory.getMapper(
	  		  		ServiceResult.class, jsonResult.toString());

	  		System.out.println("SR MESSAGE RESPONSE: " + sr.getDescription());
	  		
	  		if (sr.getStatus() == ConstantsUtil.SR_STATUS_OK) {
	  			
	  			advertisement = (Advertisement) serviceFactory.getMapper(Advertisement.class, sr.getObj().toString());
	  			
	  		}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return advertisement;
	}
	
	public Advertisement fetchAdvertismentByPictureId(String id){
		Advertisement advertisement = new Advertisement();
		
		ServiceFactory serviceFactory = new ServiceFactory();
		JSONObject jsonResult = new JSONObject();
		
		try{
			NameValuePair[] params = new NameValuePair[2];
	  		params[0] = new NameValuePair();
	  		params[0].setName("serviceMethod");
	  		params[0].setValue(ServiceMethodNames.FETCH_ADVERTISEMENT_BY_PICTURE_ID);
	  		params[1] = new NameValuePair();
	  		params[1].setName("id");
	  		params[1].setValue(id);
	  		
	  		AuthBean authBean = new AuthBean(
	  				Configurator.getConfig("AUTH_USERNAME"),
	  				Configurator.getConfig("AUTH_PASSWORD"),
	  				Configurator.getConfig("SECRET_KEY"));
	  		
	  		jsonResult = serviceFactory.HttpClientGetJSON(authBean, params,
	  				ServiceMethodNames.FETCH_ADVERTISEMENT_BY_PICTURE_ID);
	  		
	  		ServiceResult sr = (ServiceResult) serviceFactory.getMapper(
	  		  		ServiceResult.class, jsonResult.toString());

	  		System.out.println("SR MESSAGE RESPONSE: " + sr.getDescription());
	  		
	  		if (sr.getStatus() == ConstantsUtil.SR_STATUS_OK) {
	  			
	  			advertisement = (Advertisement) serviceFactory.getMapper(Advertisement.class, sr.getObj().toString());
	  			
	  		}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return advertisement;
	}
	
	public Advertisement fetchAdvertisementByNameEmailContactNumberPassword(String name, String email_address, String contact_number, String password){
		Advertisement advertisement = new Advertisement();
		
		ServiceFactory serviceFactory = new ServiceFactory();
		JSONObject jsonResult = new JSONObject();
		
		try{
			NameValuePair[] params = new NameValuePair[5];
	  		params[0] = new NameValuePair();
	  		params[0].setName("serviceMethod");
	  		params[0].setValue(ServiceMethodNames.FETCH_ADVERTISEMENT_BY_PARAMS);
	  		params[1] = new NameValuePair();
	  		params[1].setName("name");
	  		params[1].setValue(name);
	  		params[2] = new NameValuePair();
	  		params[2].setName("email_address");
	  		params[2].setValue(email_address);
	  		params[3] = new NameValuePair();
	  		params[3].setName("contact_number");
	  		params[3].setValue(contact_number);
	  		params[4] = new NameValuePair();
	  		params[4].setName("password");
	  		params[4].setValue(password);
	  		
	  		
	  		AuthBean authBean = new AuthBean(
	  				Configurator.getConfig("AUTH_USERNAME"),
	  				Configurator.getConfig("AUTH_PASSWORD"),
	  				Configurator.getConfig("SECRET_KEY"));
	  		
	  		jsonResult = serviceFactory.HttpClientGetJSON(authBean, params,
	  				ServiceMethodNames.FETCH_ADVERTISEMENT_BY_PARAMS);
	  		
	  		ServiceResult sr = (ServiceResult) serviceFactory.getMapper(
	  		  		ServiceResult.class, jsonResult.toString());

	  		System.out.println("SR MESSAGE RESPONSE: " + sr.getDescription());
	  		
	  		if (sr.getStatus() == ConstantsUtil.SR_STATUS_OK) {
	  			
	  			advertisement = (Advertisement) serviceFactory.getMapper(Advertisement.class, sr.getObj().toString());
	  			
	  		}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return advertisement;
	}
	
	public Advertisement updateAdvertisement(Advertisement advertisementBean){
		
		Advertisement advertisement = null;
		
		ServiceFactory serviceFactory = new ServiceFactory();
    	JSONObject jsonResult = new JSONObject();
    	
    	try{
    		advertisement = new Advertisement();
    		
    		String strAdvertisement = serviceFactory.parseObject(advertisementBean);
    		
    		NameValuePair[] params = new NameValuePair[2];
	  		params[0] = new NameValuePair();
	  		params[0].setName("serviceMethod");
	  		params[0].setValue(ServiceMethodNames.UPDATE_ADVERTISEMENT);
	  		params[1] = new NameValuePair();
			params[1].setName("strAdvertisement");
			params[1].setValue(strAdvertisement);
			
			AuthBean authBean = new AuthBean(
	  				Configurator.getConfig("AUTH_USERNAME"),
	  				Configurator.getConfig("AUTH_PASSWORD"),
	  				Configurator.getConfig("SECRET_KEY"));
	  		
	  		jsonResult = serviceFactory.HttpClientGetJSON(authBean, params,
	  				ServiceMethodNames.UPDATE_ADVERTISEMENT);
	  		
	  		ServiceResult sr = (ServiceResult) serviceFactory.getMapper(
	  		  		ServiceResult.class, jsonResult.toString());
	  		
	  		advertisement = (Advertisement) serviceFactory.getMapper(Advertisement.class, sr.getObj().toString());
    		
    	}catch(Exception e){
    		e.printStackTrace();
    	}
		
		return advertisement;
	}
	
	public Advertisement deleteAdvertisement(Advertisement advertisementBean){
		
		Advertisement advertisement = null;
		
		ServiceFactory serviceFactory = new ServiceFactory();
    	JSONObject jsonResult = new JSONObject();
    	
    	try{
    		advertisement = new Advertisement();
    		
    		String strAdvertisement = serviceFactory.parseObject(advertisementBean);
    		
    		NameValuePair[] params = new NameValuePair[2];
	  		params[0] = new NameValuePair();
	  		params[0].setName("serviceMethod");
	  		params[0].setValue(ServiceMethodNames.DELETE_ADVERTISEMENT);
	  		params[1] = new NameValuePair();
			params[1].setName("strAdvertisement");
			params[1].setValue(strAdvertisement);
			
			AuthBean authBean = new AuthBean(
	  				Configurator.getConfig("AUTH_USERNAME"),
	  				Configurator.getConfig("AUTH_PASSWORD"),
	  				Configurator.getConfig("SECRET_KEY"));
	  		
	  		jsonResult = serviceFactory.HttpClientGetJSON(authBean, params,
	  				ServiceMethodNames.DELETE_ADVERTISEMENT);
	  		
	  		ServiceResult sr = (ServiceResult) serviceFactory.getMapper(
	  		  		ServiceResult.class, jsonResult.toString());
	  		
	  		advertisement = (Advertisement) serviceFactory.getMapper(Advertisement.class, sr.getObj().toString());
    		
    	}catch(Exception e){
    		e.printStackTrace();
    	}
		
		return advertisement;
	}
}
