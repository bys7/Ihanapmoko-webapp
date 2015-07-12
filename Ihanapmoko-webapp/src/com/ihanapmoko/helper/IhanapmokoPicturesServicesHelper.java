package com.ihanapmoko.helper;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.NameValuePair;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import com.ihanapmoko.bean.PictureGallery;
import com.ihanapmoko.bean.PicturesServices;
import com.ihanapmoko.bean.SearchResult;

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
	
	public List<PictureGallery> fetchPictureGalleryByAdId(String advertisementId){
		
		List<PictureGallery> fetchResult = new ArrayList<PictureGallery>();
		
		ServiceFactory serviceFactory = new ServiceFactory();
		JSONObject jsonResult = new JSONObject();
		
		try{
			NameValuePair[] params = new NameValuePair[2];
	  		params[0] = new NameValuePair();
	  		params[0].setName("serviceMethod");
	  		params[0].setValue(ServiceMethodNames.FETCH_PICTURE_GALLERY_BY_AD_ID);
	  		params[1] = new NameValuePair();
			params[1].setName("advertisementId");
			params[1].setValue(advertisementId);
			
			AuthBean authBean = new AuthBean(
	  				Configurator.getConfig("AUTH_USERNAME"),
	  				Configurator.getConfig("AUTH_PASSWORD"),
	  				Configurator.getConfig("SECRET_KEY"));
	  		
	  		jsonResult = serviceFactory.HttpClientGetJSON(authBean, params,
	  				ServiceMethodNames.FETCH_PICTURE_GALLERY_BY_AD_ID);
	  		
	  		ServiceResult sr = (ServiceResult) serviceFactory.getMapper(
	  		  		ServiceResult.class, jsonResult.toString());

	  		System.out.println("SR MESSAGE RESPONSE: " + sr.getDescription());
	  		
	  		if (sr.getStatus() == ConstantsUtil.SR_STATUS_OK) {
	  			
		  		JSONArray jArray = new JSONArray(sr.getObj().toString());
		  		
		  		if (jArray != null) {
			  		for (int i = 0; i < jArray.length(); i++) {
			  			PictureGallery pictureGalleryRes = (PictureGallery) serviceFactory.getMapper(
			  			PictureGallery.class, jArray.get(i).toString());
			  			fetchResult.add(pictureGalleryRes);
			  		}
		  		}
	  		}
	  		
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return fetchResult;
		
	}
	
}
