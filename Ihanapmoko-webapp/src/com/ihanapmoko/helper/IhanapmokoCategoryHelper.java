package com.ihanapmoko.helper;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.NameValuePair;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import com.ihanapmoko.bean.Category;
import com.ihanapmoko.bean.SearchResult;

public class IhanapmokoCategoryHelper {

	public List<Category> fetchAllCategory(){
		List<Category> allCategory = new ArrayList<Category>();
		
		ServiceFactory serviceFactory = new ServiceFactory();
		JSONObject jsonResult = new JSONObject();
		
		try{
			NameValuePair[] params = new NameValuePair[1];
	  		params[0] = new NameValuePair();
	  		params[0].setName("serviceMethod");
	  		params[0].setValue(ServiceMethodNames.FETCH_ALL_CATEGORY);
	  		
	  		AuthBean authBean = new AuthBean(
	  				Configurator.getConfig("AUTH_USERNAME"),
	  				Configurator.getConfig("AUTH_PASSWORD"),
	  				Configurator.getConfig("SECRET_KEY"));
	  		
	  		jsonResult = serviceFactory.HttpClientGetJSON(authBean, params,
	  				ServiceMethodNames.FETCH_ALL_CATEGORY);
	  		
	  		ServiceResult sr = (ServiceResult) serviceFactory.getMapper(
	  		  		ServiceResult.class, jsonResult.toString());

	  		System.out.println("SR MESSAGE RESPONSE: " + sr.getDescription());
	  		
	  		if (sr.getStatus() == ConstantsUtil.SR_STATUS_OK) {
	  			
		  		JSONArray jArray = new JSONArray(sr.getObj().toString());
		  		
		  		if (jArray != null) {
			  		for (int i = 0; i < jArray.length(); i++) {
			  			Category categoryRes = (Category) serviceFactory.getMapper(
			  			Category.class, jArray.get(i).toString());
			  			allCategory.add(categoryRes);
			  		}
		  		}
	  		}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return allCategory;
	}
	
}
