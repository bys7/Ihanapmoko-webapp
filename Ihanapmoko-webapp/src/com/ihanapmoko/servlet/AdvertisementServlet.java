package com.ihanapmoko.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.ihanapmoko.bean.Advertisement;
import com.ihanapmoko.bean.Category;
import com.ihanapmoko.bean.FilterLookup;
import com.ihanapmoko.bean.Location;
import com.ihanapmoko.helper.Configurator;
import com.ihanapmoko.helper.ConstantsUtil;
import com.ihanapmoko.helper.IhanapmokoAdvertisementHelper;
import com.ihanapmoko.helper.IhanapmokoCategoryHelper;
import com.ihanapmoko.helper.IhanapmokoFilterLookupHelper;
import com.ihanapmoko.helper.IhanapmokoLocationHelper;

/**
 * Servlet implementation class AdvertisementServlet
 */
public class AdvertisementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdvertisementServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IhanapmokoCategoryHelper categoryHelper = new IhanapmokoCategoryHelper();
		IhanapmokoLocationHelper locationHelper = new IhanapmokoLocationHelper();
		
		List<Category> allCategory = new ArrayList<Category>();
		List<Location> allLocation = new ArrayList<Location>();
		
		allCategory = categoryHelper.fetchAllCategory();
		allLocation = locationHelper.fetchAllLocation();
		
		request.setAttribute("allCategory", allCategory);
		request.setAttribute("allLocation", allLocation);		
		
		request.getRequestDispatcher("/WEB-INF/jsp/advertisement.jsp").forward(request, response);
		

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String imagesPath			= null;
		String errorMessage			= null;
		String fieldName			= null;
		
		String advertisement 		= null;
		String category_Id			= null;
		String contact_number		= null;
		String number_is_private	= null;
		String email_address		= null;
		String location_id			= null;
		String budget				= null;
		String password				= null;
		String confirmPassword		= null;
		String description			= null;
		String item_condition		= null;
		String picture				= null;
		
		
		IhanapmokoAdvertisementHelper advertisementHelper 	= new IhanapmokoAdvertisementHelper();
		IhanapmokoFilterLookupHelper filterLookupHelper 	= new IhanapmokoFilterLookupHelper();
		
		Advertisement advertisementBean = new Advertisement();
		List<FilterLookup> filterLookupList	= new ArrayList<FilterLookup>();
		
		
		filterLookupList = filterLookupHelper.fetchAllFilterLookup();
		
		
		if(ServletFileUpload.isMultipartContent(request)){
			try{
				System.out.println("Configurator.getConfig(IMAGES_FILE_PATH)" + Configurator.getConfig("IMAGES_FILE_PATH"));
				imagesPath = Configurator.getConfig("IMAGES_FILE_PATH");
				List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
				
				for(FileItem item : multiparts){
					if(item.isFormField()){
						fieldName = item.getFieldName();
						if(fieldName.equals("advertisement")){
							advertisement = item.getString();
						}
						if(fieldName.equals("category_Id")){
							category_Id = item.getString();
						}
						if(fieldName.equals("contact_number")){
							contact_number = item.getString();
						}
						if(fieldName.equals("number_is_private")){
							number_is_private = item.getString();
						}
						if(fieldName.equals("email_address")){
							email_address = item.getString();
						}
						if(fieldName.equals("location_id")){
							location_id = item.getString();
						}
						if(fieldName.equals("budget")){
							budget = item.getString();
						}
						if(fieldName.equals("password")){
							password = item.getString();
						}
						if(fieldName.equals("description")){
							description = item.getString();
						}
						if(fieldName.equals("item_condition")){
							item_condition = item.getString();
						}
					}
					if(item.getSize() < 8242880){
						if(!item.isFormField()){
							String name = new File(item.getName()).getName();
							item.write(new File(imagesPath + File.separator + name));
						}
					}else{
						System.out.println( " File is too large ");
						errorMessage = errorMessage + " File is too large ";
					}					
					
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		if(advertisement!=null){
			for(FilterLookup filter : filterLookupList){
				if(advertisement.toLowerCase().contains(filter.getWords()) || description.toLowerCase().contains(filter.getWords())){
					System.out.println("ILLIGAL WORDS");
					errorMessage = errorMessage + filter.getWords() + " ";
				}
			}
		}
		
		if(number_is_private==null){
			number_is_private = ConstantsUtil.NUMBER_IS_PUBLIC;
		}else{
			number_is_private = ConstantsUtil.NUMBER_IS_PRIVATE;
		}
		
		System.out.println("LOOKING FOR:" + advertisement);
		System.out.println("CATEGORY ID:" + category_Id);
		System.out.println("CONTACT NUMBER:" + contact_number);
		System.out.println("IS PRIVATE:" + number_is_private);
		System.out.println("EMAIL ADDRESS:" + email_address);
		System.out.println("LOCATION ID:" + location_id);
		System.out.println("BUDGET:" + budget);
		System.out.println("PASSWORD:" + password);
		System.out.println("CONFIRM PASSWORD:" + confirmPassword);
		System.out.println("DESCRIPTION:" + description);
		System.out.println("CONDITION:" + item_condition);
		System.out.println("PICTURE:" + picture);
		
		advertisementBean.setAdvertisement(advertisement);
		advertisementBean.setCategory_id(Integer.parseInt(category_Id));
		advertisementBean.setContact_number(contact_number);
		advertisementBean.setNumber_is_private(number_is_private);
		advertisementBean.setEmail_address(email_address);
		advertisementBean.setDate_created(new Date());
		advertisementBean.setLocation_id(Integer.parseInt(location_id));
		advertisementBean.setBudget(budget);
		advertisementBean.setPassword(password);
		advertisementBean.setDescription(description);
		advertisementBean.setActive_status(ConstantsUtil.ADVERTISEMENT_ACTIVE);
		advertisementBean.setItem_condition(item_condition);
		
		//advertisementHelper.createAdvertisement(advertisementBean);
		
		request.setAttribute("errorMessage", errorMessage);
		
		request.getRequestDispatcher("/WEB-INF/jsp/advertisement.jsp").forward(request, response);
		
	}

}
