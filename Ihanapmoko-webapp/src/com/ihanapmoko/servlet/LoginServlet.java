package com.ihanapmoko.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ihanapmoko.bean.Advertisement;
import com.ihanapmoko.bean.AdvertisementServices;
import com.ihanapmoko.bean.User;
import com.ihanapmoko.helper.ConstantsUtil;
import com.ihanapmoko.helper.IhanapmokoAdvertisementHelper;
import com.ihanapmoko.helper.IhanapmokoAdvertisementServicesHelper;
import com.ihanapmoko.helper.IhanapmokoUserHelper;


public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private HttpSession session;
   
    public LoginServlet() {
        super();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	String 		previousPage 		= request.getHeader("referer");
		    	
		request.setAttribute("previousPage", previousPage);
				
    	request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
    	
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String 		dispatcher		= null;
		String 		errorMessage	= null;
		String 		servletName		= null;
		String[]	spltValue		= null;
		
		String 		username 		= request.getParameter("username");
		String 		password 		= request.getParameter("password");	
		String 		previousPage 	= request.getParameter("previousPage");
		
		boolean 	error			= false;
		
		System.out.println("REFERER VALUE doPost:" + previousPage);
		
		Advertisement				advertisement	= null;		
		List<AdvertisementServices> adServiceList	= new ArrayList<AdvertisementServices>();
		List<Advertisement> advertisementList	 	= new ArrayList<Advertisement>();
		
		IhanapmokoAdvertisementServicesHelper 	adServiceHelper		= new IhanapmokoAdvertisementServicesHelper();
		IhanapmokoAdvertisementHelper			advertisementHelper	= new IhanapmokoAdvertisementHelper();
		IhanapmokoUserHelper 					userHelper 			= new IhanapmokoUserHelper();
		
		try{
			User user = userHelper.getUserViaEmailAndPassword(username, password);
			
			if(user==null){
				errorMessage	= "Incorrect login credentials";
				dispatcher		= "login.jsp";
				error 			= true;
			}else if(user!=null && user.getActivated().equals(ConstantsUtil.USER_ACTIVATED)){
				session = request.getSession(true);
				session.setAttribute("user_id", user.getId());						
				if(previousPage!=null){
					spltValue = previousPage.split("/");
					System.out.println("LENGTH OF VALUES:" + spltValue.length);
					if(spltValue.length<5){
						dispatcher 	= "userDashboard.jsp";
						error 		= true;
					}else{
						servletName = spltValue[4];
						dispatcher	= servletName;
//						dispatcher = userHelper.fetchJspByServlet(servletName);
					}					
				}else{
					
					
					dispatcher 	= "userDashboard.jsp";
					error 		= true;
				}	
				
				adServiceList = adServiceHelper.fetchAdvertisementByUserId(String.valueOf(user.getId()));
				
				for(AdvertisementServices adServResult : adServiceList){
					advertisement = new Advertisement();
					advertisement = advertisementHelper.fetchAdvertismentById(String.valueOf(adServResult.getAdvertisement_id()));
					advertisementList.add(advertisement);
				}
				
				userHelper.checkUser(request,response);
				
			}else if(user!=null && user.getActivated().equals(ConstantsUtil.USER_DEACTIVATED)){
				errorMessage	= "Please verify first your account";
				dispatcher		= "login.jsp";
				error 			= true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("DISPATCHER VALUE=" + dispatcher);
		request.setAttribute("previousPage", previousPage);
		request.setAttribute("errorMessage", errorMessage);
		request.setAttribute("advertisementList", advertisementList);
		
		if(error){
			request.getRequestDispatcher("/WEB-INF/jsp/" + dispatcher).forward(request, response);
		}else{
			response.sendRedirect(dispatcher);
		}
		
		
	}

}
