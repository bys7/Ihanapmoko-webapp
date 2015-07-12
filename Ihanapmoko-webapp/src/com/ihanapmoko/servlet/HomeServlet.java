package com.ihanapmoko.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ihanapmoko.bean.User;
import com.ihanapmoko.helper.IhanapmokoUserHelper;

/**
 * Servlet implementation class AdsConfirmationServlet
 */
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private HttpSession userSession;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		IhanapmokoUserHelper userHelper = new IhanapmokoUserHelper();
		
		userHelper.checkUser(request,response);		
		
		request.getRequestDispatcher("/index.jsp").forward(request, response);
		
	}	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		IhanapmokoUserHelper userHelper = new IhanapmokoUserHelper();
		
		userHelper.checkUser(request,response);		
	}

}
