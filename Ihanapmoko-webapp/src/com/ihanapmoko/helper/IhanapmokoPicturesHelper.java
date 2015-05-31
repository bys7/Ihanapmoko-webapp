package com.ihanapmoko.helper;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.NameValuePair;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import com.ihanapmoko.bean.Pictures;

public class IhanapmokoPicturesHelper {

	public Pictures fetchPictureById(String id){
		Pictures picture = new Pictures();
		
		ServiceFactory serviceFactory = new ServiceFactory();
		JSONObject jsonResult = new JSONObject();
		
		try{
			NameValuePair[] params = new NameValuePair[2];
	  		params[0] = new NameValuePair();
	  		params[0].setName("serviceMethod");
	  		params[0].setValue(ServiceMethodNames.FETCH_PICTURE_BY_ID);
	  		params[1] = new NameValuePair();
	  		params[1].setName("id");
	  		params[1].setValue(id);
	  		
	  		AuthBean authBean = new AuthBean(
	  				Configurator.getConfig("AUTH_USERNAME"),
	  				Configurator.getConfig("AUTH_PASSWORD"),
	  				Configurator.getConfig("SECRET_KEY"));
	  		
	  		jsonResult = serviceFactory.HttpClientGetJSON(authBean, params,
	  				ServiceMethodNames.FETCH_PICTURE_BY_ID);
	  		
	  		ServiceResult sr = (ServiceResult) serviceFactory.getMapper(
	  		  		ServiceResult.class, jsonResult.toString());

	  		System.out.println("SR MESSAGE RESPONSE: " + sr.getDescription());
	  		
	  		if (sr.getStatus() == ConstantsUtil.SR_STATUS_OK) {
	  			
	  			picture = (Pictures) serviceFactory.getMapper(Pictures.class, sr.getObj().toString());
	  			
	  		}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return picture;
	}
	
	public Pictures fetchPictureByName(String name){
		Pictures picture = new Pictures();
		
		ServiceFactory serviceFactory = new ServiceFactory();
		JSONObject jsonResult = new JSONObject();
		
		try{
			NameValuePair[] params = new NameValuePair[2];
	  		params[0] = new NameValuePair();
	  		params[0].setName("serviceMethod");
	  		params[0].setValue(ServiceMethodNames.FETCH_PICTURE_BY_NAME);
	  		params[1] = new NameValuePair();
	  		params[1].setName("name");
	  		params[1].setValue(name);
	  		
	  		AuthBean authBean = new AuthBean(
	  				Configurator.getConfig("AUTH_USERNAME"),
	  				Configurator.getConfig("AUTH_PASSWORD"),
	  				Configurator.getConfig("SECRET_KEY"));
	  		
	  		jsonResult = serviceFactory.HttpClientGetJSON(authBean, params,
	  				ServiceMethodNames.FETCH_PICTURE_BY_NAME);
	  		
	  		ServiceResult sr = (ServiceResult) serviceFactory.getMapper(
	  		  		ServiceResult.class, jsonResult.toString());

	  		System.out.println("SR MESSAGE RESPONSE: " + sr.getDescription());
	  		
	  		if (sr.getStatus() == ConstantsUtil.SR_STATUS_OK) {
	  			
	  			picture = (Pictures) serviceFactory.getMapper(Pictures.class, sr.getObj().toString());
	  			
	  		}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return picture;
	}
	
	public List<Pictures> fetchPictureByAdvertisementId(String advertisemntId){
		List<Pictures> lstPicture = new ArrayList<Pictures>();
		
		ServiceFactory serviceFactory = new ServiceFactory();
		JSONObject jsonResult = new JSONObject();
		
		try{
			NameValuePair[] params = new NameValuePair[2];
	  		params[0] = new NameValuePair();
	  		params[0].setName("serviceMethod");
	  		params[0].setValue(ServiceMethodNames.FETCH_PICTURE_BY_AD_ID);
	  		params[1] = new NameValuePair();
	  		params[1].setName("advertisemntId");
	  		params[1].setValue(advertisemntId);
	  		
	  		AuthBean authBean = new AuthBean(
	  				Configurator.getConfig("AUTH_USERNAME"),
	  				Configurator.getConfig("AUTH_PASSWORD"),
	  				Configurator.getConfig("SECRET_KEY"));
	  		
	  		jsonResult = serviceFactory.HttpClientGetJSON(authBean, params,
	  				ServiceMethodNames.FETCH_PICTURE_BY_AD_ID);
	  		
	  		ServiceResult sr = (ServiceResult) serviceFactory.getMapper(
	  		  		ServiceResult.class, jsonResult.toString());

	  		System.out.println("SR MESSAGE RESPONSE: " + sr.getDescription());
	  		
	  		if (sr.getStatus() == ConstantsUtil.SR_STATUS_OK) {
	  			
	  			
	  			JSONArray jArray = new JSONArray(sr.getObj().toString());
	  			
	  			if (jArray != null) {
			  		for (int i = 0; i < jArray.length(); i++) {
			  			Pictures picturesRes = (Pictures) serviceFactory.getMapper(
			  			Pictures.class, jArray.get(i).toString());
			  			lstPicture.add(picturesRes);
			  		}
		  		}
	  			
	  		}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return lstPicture;
	}
	
	public Pictures createPictures(Pictures picturesBean){
		
		Pictures pictures = null;
		
		ServiceFactory serviceFactory = new ServiceFactory();
    	JSONObject jsonResult = new JSONObject();
    	
    	try{
    		pictures = new Pictures();
    		
    		String strPictures = serviceFactory.parseObject(picturesBean);
    		
    		NameValuePair[] params = new NameValuePair[2];
	  		params[0] = new NameValuePair();
	  		params[0].setName("serviceMethod");
	  		params[0].setValue(ServiceMethodNames.CREATE_PICTURES);
	  		params[1] = new NameValuePair();
			params[1].setName("strPictures");
			params[1].setValue(strPictures);
			
			AuthBean authBean = new AuthBean(
	  				Configurator.getConfig("AUTH_USERNAME"),
	  				Configurator.getConfig("AUTH_PASSWORD"),
	  				Configurator.getConfig("SECRET_KEY"));
	  		
	  		jsonResult = serviceFactory.HttpClientGetJSON(authBean, params,
	  				ServiceMethodNames.CREATE_PICTURES);
	  		
	  		ServiceResult sr = (ServiceResult) serviceFactory.getMapper(
	  		  		ServiceResult.class, jsonResult.toString());
	  		
	  		pictures = (Pictures) serviceFactory.getMapper(Pictures.class, sr.getObj().toString());
    		
    	}catch(Exception e){
    		e.printStackTrace();
    	}
		
		return pictures;
	}
	
}
