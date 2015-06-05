package com.ihanapmoko.servlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ihanapmoko.helper.Configurator;

/**
 * Servlet implementation class SearchImageServlet
 */
public class SearchImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchImageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String imageName = request.getParameter("imageName");
		String imagePath = Configurator.getConfig("IMAGES_FILE_PATH_PERMANENT");
		
		response.setContentType("image/jpeg");
		OutputStream out = response.getOutputStream();
		FileInputStream in = new FileInputStream(imagePath + "/" +  imageName);
		int size = in.available();
		byte[] content = new byte[size];
		
		System.out.println("SIZE OF IMAGE:" + content);
		
		in.read(content);
		out.write(content);
		
		
		in.close();
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
