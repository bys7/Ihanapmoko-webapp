package com.ihanapmoko.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ihanapmoko.bean.User;
import com.ihanapmoko.helper.Configurator;
import com.ihanapmoko.helper.ConstantsUtil;
import com.ihanapmoko.helper.IhanapmokoEmailHelper;
import com.ihanapmoko.helper.IhanapmokoUserHelper;


public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public RegistrationServlet() {
        super();
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String firstName 		= request.getParameter("regFirstName"); 
		String middleName 		= request.getParameter("regMiddleName"); 
		String lastName 		= request.getParameter("regLastName"); 
		String email 			= request.getParameter("regEmail"); 
		String contactnumber 	= request.getParameter("regContactNumber"); 
		String password 		= request.getParameter("regConfirmPassword"); 
		
		String errorMessage		= null;
		
		User validateUser		= null;
		
		boolean error			= false;
		
		IhanapmokoUserHelper userHelper = new IhanapmokoUserHelper();
		
		if(email!=null && !email.equals("")){
			validateUser 	= userHelper.validateUserViaEmail(email);
			if(validateUser==null){
				User user = new User();
				user.setFirstname(firstName);
				user.setMiddlename(middleName);
				user.setLastname(lastName);
				user.setEmail_address(email);
				user.setContact_number(contactnumber);
				user.setPassword(password);
				user.setRole_id(ConstantsUtil.REGULAR_USER);
				user.setDate_created(new Date());
				user.setActivated(ConstantsUtil.USER_DEACTIVATED);
				System.out.println(user);
				IhanapmokoUserHelper helper = new IhanapmokoUserHelper();
				user = helper.saveUser(user);
				System.out.println("USER ID:" + user.getId());
				IhanapmokoEmailHelper emailHelper = new IhanapmokoEmailHelper();
				String emailBody = Configurator.getConfig("EMAIL_NOTIF")
						.replaceAll("<id>", Long.toString(user.getId()))
						.replaceAll("<firstName>", user.getFirstname())
						.replaceAll("<lastName>", user.getLastname());
				String subject   = Configurator.getConfig("MAIL_SUBJECT"); 
				emailHelper.sendMail(new String[]{user.getEmail_address()}, subject, emailBody);
				
				error = false;
			}else{
				errorMessage	= "Email already exist";
				error = true;
			}
		}
		
		request.setAttribute("registrationErrorMessage", errorMessage);
		
		if(error){
			request.getRequestDispatcher("login").forward(request, response);
		}else{
			response.sendRedirect("/request_success");
		}
			
		
		
		
//		request.getRequestDispatcher(dispatcher).forward(request, response);
		
	}

}
