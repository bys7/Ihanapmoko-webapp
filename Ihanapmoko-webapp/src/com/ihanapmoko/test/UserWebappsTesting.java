package com.ihanapmoko.test;

import com.ihanapmoko.bean.User;
import com.ihanapmoko.helper.IhanapmokoUserHelper;

public class UserWebappsTesting {
	
	public static void main(String[] args){
		
		User user = new User();
		IhanapmokoUserHelper userHelper = new IhanapmokoUserHelper();
		
		user = userHelper.getUserViaEmailAndPassword("bryanyabutserrano@gmail.com", "akocbry");
		
		System.out.println("GET USER ID :" + user.getId());
		System.out.println("GET FIRSTNAME :" + user.getFirstname());
		System.out.println("GET LASTNAME :" + user.getLastname());
		
		
	}

}
