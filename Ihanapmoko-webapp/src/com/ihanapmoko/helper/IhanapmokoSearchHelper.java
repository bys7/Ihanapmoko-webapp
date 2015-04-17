package com.ihanapmoko.helper;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.NameValuePair;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import com.ihanapmoko.bean.Advertisement;
import com.ihanapmoko.bean.SearchResult;

public class IhanapmokoSearchHelper {

	public List<SearchResult> searchAdvertisement(String searchParameter){
		
		List<SearchResult> searchResult = new ArrayList<SearchResult>();
		
		ServiceFactory serviceFactory = new ServiceFactory();
		JSONObject jsonResult = new JSONObject();
		
		try{
			NameValuePair[] params = new NameValuePair[2];
	  		params[0] = new NameValuePair();
	  		params[0].setName("serviceMethod");
	  		params[0].setValue(ServiceMethodNames.SEARCH_ADVERTISEMENT);
	  		params[1] = new NameValuePair();
			params[1].setName("searchParameter");
			params[1].setValue(searchParameter);
			
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
	
}
