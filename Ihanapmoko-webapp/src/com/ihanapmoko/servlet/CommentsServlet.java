package com.ihanapmoko.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ihanapmoko.bean.Comments;
import com.ihanapmoko.helper.IhanapmokoCommentsHelper;

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
		
		
//		RequestDispatcher view = request.getRequestDispatcher("/jsp/sendcomments.jsp");
//		view.forward(request, response);
		
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
		
		IhanapmokoCommentsHelper commentsHelper = new IhanapmokoCommentsHelper();
		
//		Comments comments = commentsHelper.addNewComment(fullname, email_address, message);
		Comments comments = new Comments();
		
		comments.setId(1);
		comments.setFullname("Bryan Serrano");
		comments.setEmail_address("bys_7@yahoo.com");
		comments.setMessage("Ang Bagal talaga");
		comments.setRead_marker(0);
		
		request.setAttribute("comments", comments);
		request.getRequestDispatcher("/WEB-INF/jsp/sendcomments.jsp").forward(request, response);
	}

}
