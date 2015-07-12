package com.ihanapmoko.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ihanapmoko.bean.Comments;
import com.ihanapmoko.bean.User;
import com.ihanapmoko.helper.IhanapmokoCommentsHelper;
import com.ihanapmoko.helper.IhanapmokoUserHelper;

/**
 * Servlet implementation class CommentsServlet
 */
public class CommentsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		IhanapmokoUserHelper userHelper			= new IhanapmokoUserHelper();
				
		userHelper.checkUser(request,response);
		
		request.getRequestDispatcher("/WEB-INF/jsp/sendcomments.jsp").forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("IHANAP MOKO ADD COMMENTS");
		
		String fullname 	= request.getParameter("fullname");
		String email_address = request.getParameter("email_address");
		String message		= request.getParameter("message");
		
		Comments comments = null;
		
		IhanapmokoCommentsHelper commentsHelper = new IhanapmokoCommentsHelper();
		IhanapmokoUserHelper userHelper			= new IhanapmokoUserHelper();
		
		try{
			comments = new Comments();
			comments = commentsHelper.addNewComment(fullname, email_address, message);
		}catch(Exception e){
			e.printStackTrace();
		}		
		
		userHelper.checkUser(request,response);
		
		request.setAttribute("comments", comments);	
		
		request.getRequestDispatcher("/WEB-INF/jsp/sendcomments.jsp").forward(request, response);
	}
	
	
}
