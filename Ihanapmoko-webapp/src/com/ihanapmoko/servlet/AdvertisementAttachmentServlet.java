package com.ihanapmoko.servlet;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.ihanapmoko.bean.Pictures;
import com.ihanapmoko.helper.Configurator;

/**
 * Servlet implementation class AdvertisementAttachmentServlet
 */
public class AdvertisementAttachmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("image/jpeg");
		OutputStream out = response.getOutputStream();
		FileInputStream in = new FileInputStream("C:/images/temp/DSC_0068.jpg");
		int size = in.available();
		byte[] content = new byte[size];
				
		in.read(content);
		out.write(content);
		in.close();
		out.close();
		
//		request.getRequestDispatcher("/WEB-INF/jsp/imageAttachment.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	
	@SuppressWarnings("null")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String imagesPath				= null;
		String errorMessage				= null;
		String fullPathImage			= null;
		
		Pictures pictures				= null;
		List<Pictures> lstImages		= null;
		
		if(ServletFileUpload.isMultipartContent(request)){
			
			try{
				System.out.println("Configurator.getConfig(IMAGES_FILE_PATH)" + Configurator.getConfig("IMAGES_FILE_PATH"));
				imagesPath = Configurator.getConfig("IMAGES_FILE_PATH");
				
				
				List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
				
				lstImages	= new ArrayList<Pictures>();
				
				for(FileItem item : multiparts){
					if(item.getSize() < 8242880){
						if(!item.isFormField()){
							
							pictures 	= new Pictures();
							
							String name = new File(item.getName()).getName();
							
							item.write(new File(imagesPath + File.separator + name));							
							fullPathImage = imagesPath + File.separator + name;
							System.out.println("ATTACHMENT FULL PATH:" + fullPathImage);
							pictures.setPicture_destination(name);
							lstImages.add(pictures);
						}
					}else{
						System.out.println( " File is too large ");
						errorMessage = errorMessage + " File is too large ";
					}					
					
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			request.setAttribute("lstImages", lstImages);
			
			request.getRequestDispatcher("/WEB-INF/jsp/imageAttachment.jsp").forward(request, response);
		}
	}
	
	private static void close(Closeable resource) {
        if (resource != null) {
            try {
                resource.close();
            } catch (IOException e) {
                // Do your thing with the exception. Print it, log it or mail it.
                e.printStackTrace();
            }
        }
    }

}
