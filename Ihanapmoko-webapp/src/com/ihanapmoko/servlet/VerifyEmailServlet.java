package com.ihanapmoko.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ihanapmoko.bean.User;
import com.ihanapmoko.helper.ConstantsUtil;
import com.ihanapmoko.helper.IhanapmokoUserHelper;


public class VerifyEmailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public VerifyEmailServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		verify(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    verify(request, response);
	}


	private void verify(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id = request.getParameter("id");
		IhanapmokoUserHelper helper = new IhanapmokoUserHelper();
		User user = helper.getUserById(Integer.parseInt(id));
		user.setActivated(ConstantsUtil.USER_ACTIVATED);
		helper.updateUser(user);
		response.sendRedirect("/Ihanapmoko-webapp/verification_success");
	}
	
	

}
