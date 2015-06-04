package com.ihanapmoko.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ihanapmoko.bean.User;
import com.ihanapmoko.helper.IhanapmokoUserHelper;


public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public LoginServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		IhanapmokoUserHelper helper = new IhanapmokoUserHelper();
		User user = helper.getUserViaEmailAndPassword(username, password);
		if(user==null){
			response.sendRedirect("/Ihanapmoko-webapp/login_error");
		}else{
			response.sendRedirect("/Ihanapmoko-webapp/AdvertisementServlet");
			
		}
	}

}
