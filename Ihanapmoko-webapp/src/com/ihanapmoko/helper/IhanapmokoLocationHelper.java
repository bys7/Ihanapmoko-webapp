package com.ihanapmoko.helper;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.NameValuePair;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import com.ihanapmoko.bean.Category;
import com.ihanapmoko.bean.Location;

public class IhanapmokoLocationHelper {
	
	public List<Location> fetchAllLocation(){
		
		List<Location> allLocation = new ArrayList<Location>();
		
		ServiceFactory serviceFactory = new ServiceFactory();
		JSONObject jsonResult = new JSONObject();
		
		try{

			NameValuePair[] params = new NameValuePair[1];
	  		params[0] = new NameValuePair();
	  		params[0].setName("serviceMethod");
	  		params[0].setValue(ServiceMethodNames.FETCH_ALL_LOCATION);
			
	  		AuthBean authBean = new AuthBean(
	  				Configurator.getConfig("AUTH_USERNAME"),
	  				Configurator.getConfig("AUTH_PASSWORD"),
	  				Configurator.getConfig("SECRET_KEY"));
	  		
	  		jsonResult = serviceFactory.HttpClientGetJSON(authBean, params,
	  				ServiceMethodNames.FETCH_ALL_LOCATION);
	  		
	  		ServiceResult sr = (ServiceResult) serviceFactory.getMapper(
	  		  		ServiceResult.class, jsonResult.toString());

	  		System.out.println("SR MESSAGE RESPONSE: " + sr.getDescription());
	  		
	  		if (sr.getStatus() == ConstantsUtil.SR_STATUS_OK) {
	  			JSONArray jArray = new JSONArray(sr.getObj().toString());
	  			
	  			if (jArray != null) {
			  		for (int i = 0; i < jArray.length(); i++) {
			  			Location locationRes = (Location) serviceFactory.getMapper(
			  			Location.class, jArray.get(i).toString());
			  			allLocation.add(locationRes);
			  		}
		  		}
	  		}
		}catch(Exception e){
			
		}
		
  		
		return allLocation;
		
	}

}
