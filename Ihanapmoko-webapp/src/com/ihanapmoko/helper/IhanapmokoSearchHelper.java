package com.ihanapmoko.helper;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.NameValuePair;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import com.ihanapmoko.bean.SearchResult;
import com.ihanapmoko.bean.SearchSize;

public class IhanapmokoSearchHelper {

	public List<SearchResult> searchAdvertisement(String searchParameter, String locationId, String categoryId, String startRow){
		
		List<SearchResult> searchResult = new ArrayList<SearchResult>();
		
		ServiceFactory serviceFactory = new ServiceFactory();
		JSONObject jsonResult = new JSONObject();
		
		try{
			NameValuePair[] params = new NameValuePair[5];
	  		params[0] = new NameValuePair();
	  		params[0].setName("serviceMethod");
	  		params[0].setValue(ServiceMethodNames.SEARCH_ADVERTISEMENT);
	  		params[1] = new NameValuePair();
			params[1].setName("searchParameter");
			params[1].setValue(searchParameter);
			params[2] = new NameValuePair();
			params[2].setName("locationId");
			params[2].setValue(locationId);
			params[3] = new NameValuePair();
			params[3].setName("categoryId");
			params[3].setValue(categoryId);
			params[4] = new NameValuePair();
			params[4].setName("startRow");
			params[4].setValue(startRow);
			
			AuthBean authBean = new AuthBean(
	  				Configurator.getConfig("AUTH_USERNAME"),
	  				Configurator.getConfig("AUTH_PASSWORD"),
	  				Configurator.getConfig("SECRET_KEY"));
	  		
	  		jsonResult = serviceFactory.HttpClientGetJSON(authBean, params,
	  				ServiceMethodNames.SEARCH_ADVERTISEMENT);
	  		
	  		ServiceResult sr = (ServiceResult) serviceFactory.getMapper(
	  		  		ServiceResult.class, jsonResult.toString());

	  		System.out.println("SR MESSAGE RESPONSE: " + sr.getDescription());
	  		
	  		if (sr.getStatus() == ConstantsUtil.SR_STATUS_OK) {
	  			
		  		JSONArray jArray = new JSONArray(sr.getObj().toString());
		  		
		  		if (jArray != null) {
			  		for (int i = 0; i < jArray.length(); i++) {
			  			SearchResult searchRes = (SearchResult) serviceFactory.getMapper(
			  			SearchResult.class, jArray.get(i).toString());
			  			searchResult.add(searchRes);
			  		}
		  		}
	  		}
	  		
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return searchResult;
		
	}
	
	public String startPage(int startRow){
		String page = null;
		int intPage = 0;
		
		try{
			intPage = startRow * 12;
			intPage = intPage - 12;
			page = String.valueOf(intPage);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return page;
	}
	
	public SearchSize searchAdvertisementSize(String searchParameter, String locationId, String categoryId){
		
		SearchSize searchSize			= new SearchSize();
		
		ServiceFactory serviceFactory 	= new ServiceFactory();
		JSONObject jsonResult 			= new JSONObject();
				
		try{
			NameValuePair[] params = new NameValuePair[4];
	  		params[0] = new NameValuePair();
	  		params[0].setName("serviceMethod");
	  		params[0].setValue(ServiceMethodNames.SEARCH_ADVERTISEMENT_SIZE);
	  		params[1] = new NameValuePair();
			params[1].setName("searchParameter");
			params[1].setValue(searchParameter);
			params[2] = new NameValuePair();
			params[2].setName("locationId");
			params[2].setValue(locationId);
			params[3] = new NameValuePair();
			params[3].setName("categoryId");
			params[3].setValue(categoryId);
			
			AuthBean authBean = new AuthBean(
	  				Configurator.getConfig("AUTH_USERNAME"),
	  				Configurator.getConfig("AUTH_PASSWORD"),
	  				Configurator.getConfig("SECRET_KEY"));
	  		
	  		jsonResult = serviceFactory.HttpClientGetJSON(authBean, params,
	  				ServiceMethodNames.SEARCH_ADVERTISEMENT_SIZE);
	  		
	  		ServiceResult sr = (ServiceResult) serviceFactory.getMapper(
	  		  		ServiceResult.class, jsonResult.toString());

	  		System.out.println("SR MESSAGE RESPONSE: " + sr.getDescription());
	  		
	  		if (sr.getStatus() == ConstantsUtil.SR_STATUS_OK) {
	  				  			
	  			searchSize = (SearchSize) serviceFactory.getMapper(SearchSize.class, sr.getObj().toString());
	  			
	  		}
	  		
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return searchSize;
		
	}
	
}
