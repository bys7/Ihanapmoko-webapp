package com.ihanapmoko.servlet;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
import com.ihanapmoko.bean.Pictures;
import com.ihanapmoko.bean.PicturesServices;
import com.ihanapmoko.bean.User;
import com.ihanapmoko.helper.Configurator;
import com.ihanapmoko.helper.ConstantsUtil;
import com.ihanapmoko.helper.IhanapmokoAdvertisementHelper;
import com.ihanapmoko.helper.IhanapmokoCategoryHelper;
import com.ihanapmoko.helper.IhanapmokoEmailHelper;
import com.ihanapmoko.helper.IhanapmokoFilterLookupHelper;
import com.ihanapmoko.helper.IhanapmokoLocationHelper;
import com.ihanapmoko.helper.IhanapmokoPicturesHelper;
import com.ihanapmoko.helper.IhanapmokoPicturesServicesHelper;
import com.ihanapmoko.helper.IhanapmokoUserHelper;

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
		
		String strAdvertisementBean 	= request.getParameter("advertisementBean");
		if(strAdvertisementBean!=null){
			System.out.println("advertisementBean:" + strAdvertisementBean);
		}
		
		IhanapmokoCategoryHelper categoryHelper = new IhanapmokoCategoryHelper();
		IhanapmokoLocationHelper locationHelper = new IhanapmokoLocationHelper();
		IhanapmokoUserHelper 	 userHelper		= new IhanapmokoUserHelper();
				
		List<Category> allCategory 				= new ArrayList<Category>();
		List<Location> allLocation 				= new ArrayList<Location>();
		Advertisement 	advertisementBean 		= new Advertisement();
		
		boolean firstPage						= true;
		
		
		List<Advertisement> advertisementIterate 	= new ArrayList<Advertisement>();
		List<String> 		conditionList			= new ArrayList<String>();
		
		allCategory = categoryHelper.fetchAllCategory();
		allLocation = locationHelper.fetchAllLocation();
		
		conditionList.add("Brand New");
		conditionList.add("Used");
		
		advertisementBean.setDate_created(new Date());		
		
		advertisementIterate.add(advertisementBean);
		
		userHelper.checkUser(request,response);
		
		request.setAttribute("allCategory", allCategory);
		request.setAttribute("allLocation", allLocation);
		request.setAttribute("advertisementIterate", advertisementIterate);
		request.setAttribute("firstPage", firstPage);
		request.setAttribute("conditionList", conditionList);
		
		request.getRequestDispatcher("/WEB-INF/jsp/advertisement.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String imagesPathTemp		= null;
		String imagesPathPermanent	= null;
		String errorMessage			= null;
		String fieldName			= null;
		String fileName				= null;
		
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
		String strPictures			= null;
		String displayPhoto			= null;
		
		String cateogoryName		= null;
		String loationName			= null;
		
		String[] splitPictures		= null;
		
		DateFormat dateFormat		= new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss");
		Date currentDate 			= new Date();
				
		IhanapmokoAdvertisementHelper 		advertisementHelper 	= new IhanapmokoAdvertisementHelper();
		IhanapmokoFilterLookupHelper 		filterLookupHelper 		= new IhanapmokoFilterLookupHelper();
		IhanapmokoCategoryHelper			categoryHelper			= new IhanapmokoCategoryHelper();
		IhanapmokoLocationHelper			locationHelper			= new IhanapmokoLocationHelper();
		IhanapmokoPicturesHelper			picturesHelper			= new IhanapmokoPicturesHelper();
		IhanapmokoPicturesServicesHelper 	picturesServicesHelper	= new IhanapmokoPicturesServicesHelper();
		IhanapmokoEmailHelper 				emailHelper 			= new IhanapmokoEmailHelper();
		IhanapmokoUserHelper 				userHelper				= new IhanapmokoUserHelper();
				
		Advertisement 		advertisementBean 		= new Advertisement();
		Advertisement 		ad_picture_id			= new Advertisement();
		Category			category				= new Category();
		Location			location				= new Location();		
		List<FilterLookup> 	filterLookupList		= new ArrayList<FilterLookup>();
		List<Advertisement> advertisementIterate 	= new ArrayList<Advertisement>();
		List<String> 		lstFileName				= new ArrayList<String>();
		List<Category> 		allCategory 			= new ArrayList<Category>();
		List<Location>		allLocation 			= new ArrayList<Location>();
		List<String> 		conditionList			= new ArrayList<String>();
		
		Pictures			picturesBean			= null;
		PicturesServices	picturesServicesBean	= null;
		
		filterLookupList = filterLookupHelper.fetchAllFilterLookup();
		
		conditionList.add("Brand New");
		conditionList.add("Used");
		
		if(ServletFileUpload.isMultipartContent(request)){
			try{
				System.out.println("Configurator.getConfig(IMAGES_FILE_PATH)" + Configurator.getConfig("IMAGES_FILE_PATH"));
				imagesPathTemp = Configurator.getConfig("IMAGES_FILE_PATH_TEMP");
				imagesPathPermanent	= Configurator.getConfig("IMAGES_FILE_PATH_PERMANENT");
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
						if(fieldName.equals("delimiterFileName")){
							strPictures = item.getString();
						}
						if(fieldName.equals("displayPhoto")){
							displayPhoto = item.getString();
						}
					}
					if(item!=null){
						if(item.getSize() < 8242880){
							if(!item.isFormField()){
								String name = new File(item.getName()).getName();
								item.write(new File(imagesPathTemp + File.separator + email_address + "_" + dateFormat.format(currentDate) + "_" + name));
								System.out.println("File name : " + name);
								fileName =  email_address + "_" +  dateFormat.format(currentDate) +  "_" + name;
								if(name!=null && !name.equals("")){
									lstFileName.add(fileName);
								}							
							}
						}else{
							System.out.println( " File is too large ");
							errorMessage = errorMessage + " File is too large. ";
						}
					}					
					
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		if(category_Id !=null && !category_Id.equals("")){
			
		}
		
		for(FilterLookup filter : filterLookupList){
			if(advertisement!=null && !advertisement.equals("")){
				if(advertisement.toLowerCase().contains(filter.getWords())){
					if(errorMessage==null){
						errorMessage = "";
					}
					errorMessage = errorMessage + filter.getWords() + ", ";
				}
			}				
			if(description!=null && !description.equals("")){
				if(description.toLowerCase().contains(filter.getWords())){
					if(errorMessage==null){
						errorMessage = "";
					}
					errorMessage = errorMessage + filter.getWords() + ", ";
				}
			}
		}
		
		if(number_is_private==null){
			number_is_private = ConstantsUtil.NUMBER_IS_PUBLIC;
		}else{
			number_is_private = ConstantsUtil.NUMBER_IS_PRIVATE;
		}
		
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
		
		advertisementIterate.add(advertisementBean);
		
		if(errorMessage==null){			
			
			if(password!=null && !password.equals("")){
				try{				
					if(strPictures!=null && !strPictures.equals("")){
						splitPictures = strPictures.split("\\|");
						
						for(String transferFile : splitPictures){
							
							File tempFile = new File(imagesPathTemp + "/" + transferFile);
							System.out.println("FILE FROM:" + imagesPathTemp + transferFile);
							if(tempFile.renameTo(new File(imagesPathPermanent + "/" + tempFile.getName()))){
								System.out.println("FILES HAS BEEN TRANSFERED");
								picturesBean = new Pictures();
								picturesBean.setPicture_destination(tempFile.getName());
								picturesHelper.createPictures(picturesBean);
							}else{
								System.out.println("FAILED TO TRANSFER FILES");
							}
						}
						if(displayPhoto!=null){
							picturesBean = picturesHelper.fetchPictureByName(displayPhoto);
							
						}else{
							picturesBean = picturesHelper.fetchPictureByName(splitPictures[0]);
						}
						advertisementBean.setPicture_id(picturesBean.getId());
						
					}else{
						advertisementBean.setPicture_id(1);
					}
					advertisementHelper.createAdvertisement(advertisementBean);							
					
					if(picturesBean!=null && !picturesBean.equals("")){
						ad_picture_id = advertisementHelper.fetchAdvertismentByPictureId(String.valueOf(picturesBean.getId()));
						
						for(String pictureServices : splitPictures){
							picturesServicesBean 	= new PicturesServices();
							picturesBean		 	= new Pictures();
							
							picturesBean = picturesHelper.fetchPictureByName(pictureServices);
							picturesServicesBean.setAdvertisement_id(ad_picture_id.getId());
							picturesServicesBean.setPictures_id(picturesBean.getId());
							picturesServicesHelper.createPictures(picturesServicesBean);
						}					
					}
					
					advertisementBean = advertisementHelper.fetchAdvertisementByNameEmailContactNumberPassword(
							advertisementBean.getAdvertisement(), advertisementBean.getEmail_address(), 
							advertisementBean.getContact_number(), advertisementBean.getPassword());
					/*SEND EMAIL*/
					String emailBody = Configurator.getConfig("EMAIL_ADVERTISEMENT_NOTIF")
							.replaceAll("<id>", Long.toString(advertisementBean.getId()))
							.replace("<password>", advertisementBean.getPassword());						
					String subject   = Configurator.getConfig("MAIL_SUBJECT");
					emailHelper.sendMail(new String[]{advertisementBean.getEmail_address()}, subject, emailBody);

				}catch(Exception e){
					e.printStackTrace();
				}
				
				
			}else{
				category 	= categoryHelper.fetchCategoryById(category_Id);
				cateogoryName = category.getCategory();
				
				location	= locationHelper.fetchLocationById(location_id);
				loationName = location.getLocation();
				
			}	
		}else{					
			allCategory = categoryHelper.fetchAllCategory();
			allLocation = locationHelper.fetchAllLocation();
			
		}
		
		userHelper.checkUser(request,response);
		
		request.setAttribute("errorMessage", errorMessage);
		request.setAttribute("advertisementIterate", advertisementIterate);
		request.setAttribute("lstFileName", lstFileName);
		request.setAttribute("lstFileNameSize", lstFileName.size());
		request.setAttribute("cateogoryName", cateogoryName);
		request.setAttribute("loationName", loationName);
		request.setAttribute("dashboardId", advertisementBean.getId());
		request.setAttribute("allCategory", allCategory);
		request.setAttribute("allLocation", allLocation);
		request.setAttribute("conditionList", conditionList);
								
		request.getRequestDispatcher("/WEB-INF/jsp/advertisement.jsp").forward(request, response);
//		request.getRequestDispatcher("/AdsConfirmationServlet").forward(request, response);
//		doGet(request,response);
		
	}

}
