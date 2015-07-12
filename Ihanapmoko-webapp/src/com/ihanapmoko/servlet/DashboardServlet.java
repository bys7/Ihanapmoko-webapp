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
import com.ihanapmoko.bean.Bidding;
import com.ihanapmoko.bean.BiddingResult;
import com.ihanapmoko.bean.Category;
import com.ihanapmoko.bean.Dashboard;
import com.ihanapmoko.bean.Location;
import com.ihanapmoko.bean.PictureGallery;
import com.ihanapmoko.bean.Pictures;
import com.ihanapmoko.bean.User;
import com.ihanapmoko.helper.Configurator;
import com.ihanapmoko.helper.ConstantsUtil;
import com.ihanapmoko.helper.IhanapmokoAdvertisementHelper;
import com.ihanapmoko.helper.IhanapmokoBiddingHelper;
import com.ihanapmoko.helper.IhanapmokoCategoryHelper;
import com.ihanapmoko.helper.IhanapmokoDashboardHelper;
import com.ihanapmoko.helper.IhanapmokoLocationHelper;
import com.ihanapmoko.helper.IhanapmokoPicturesHelper;
import com.ihanapmoko.helper.IhanapmokoPicturesServicesHelper;
import com.ihanapmoko.helper.IhanapmokoUserHelper;

/**
 * Servlet implementation class DashboardServlet
 */
public class DashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DashboardServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String dashboardId 						= request.getParameter("dashboardId");
		
		int biddingPictureSize					= 0;
		int viewCount							= 0;
		
		Dashboard dashboard 					= new Dashboard();
		List<PictureGallery> pictureGallery 	= new ArrayList<PictureGallery>();
		List<BiddingResult> biddingResultList	= new ArrayList<BiddingResult>();
		Pictures pictures						= new Pictures();
		Advertisement advertisement				= new Advertisement();
		List<Category> allCategory 				= new ArrayList<Category>();
		List<Location> allLocation				= new ArrayList<Location>();	
		List<String> conditionList				= new ArrayList<String>();
		
		IhanapmokoDashboardHelper dashboardHelper 					= new IhanapmokoDashboardHelper();
		IhanapmokoPicturesServicesHelper  picturesServicesHelper	= new IhanapmokoPicturesServicesHelper();
		IhanapmokoBiddingHelper biddingHelper						= new IhanapmokoBiddingHelper();
		IhanapmokoPicturesHelper picturesHelper						= new IhanapmokoPicturesHelper();
		IhanapmokoAdvertisementHelper advertisementHelper			= new IhanapmokoAdvertisementHelper();
		IhanapmokoCategoryHelper categoryHelper 					= new IhanapmokoCategoryHelper();
		IhanapmokoLocationHelper locationHelper 					= new IhanapmokoLocationHelper();
		IhanapmokoUserHelper userHelper								= new IhanapmokoUserHelper();
		
		try{		
			
			dashboard 				= dashboardHelper.fetchDashboardAdvertismentById(dashboardId);
			pictureGallery 			= picturesServicesHelper.fetchPictureGalleryByAdId(dashboardId);
			biddingResultList		= biddingHelper.fetchBiddingByAdvertisementId(dashboardId);
			advertisement			= advertisementHelper.fetchAdvertismentById(dashboardId);
			allCategory 			= categoryHelper.fetchAllCategory();
			allLocation 			= locationHelper.fetchAllLocation();
			
			viewCount 				= advertisement.getViews();
			viewCount++;
			advertisement.setViews(viewCount);
			dashboard.setViews(viewCount);
			
			advertisementHelper.updateAdvertisement(advertisement);
			
			conditionList.add("Brand New");
			conditionList.add("Used");			
			
			for(BiddingResult result : biddingResultList){
				if(result.getPicture_id()!=0){
					pictures = picturesHelper.fetchPictureById(String.valueOf(result.getPicture_id()));					
					result.setPicture_destination(pictures.getPicture_destination());
					biddingPictureSize++;
				}			
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		userHelper.checkUser(request,response);
				
		request.setAttribute("dashboard", dashboard);
		request.setAttribute("pictureGallery", pictureGallery);
		request.setAttribute("biddingList", biddingResultList);
		request.setAttribute("biddingPictureSize", biddingPictureSize);
		request.setAttribute("allCategory", allCategory);
		request.setAttribute("allLocation", allLocation);
		request.setAttribute("conditionList", conditionList);
		
		request.getRequestDispatcher("/WEB-INF/jsp/dashboard.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String dashboardId			= null;
		String message		 		= null;
		String fieldName			= null;
		String passwordFromDB		= null;
		
		//Command to do
		String dashboardCommand		= null;
		
		//Dashboard to update fields
		String advertisement		= null;
		String category_id			= null;
		String budget				= null;
		String item_condition		= null;
		String location_id			= null;
		String contact_number		= null;
		String number_is_private	= null;
		String email_address		= null;
		String description			= null;
		String password				= null;
		
		String imagesPathPermanent	= null;
		String fileName				= null;
		String errorMessage			= null;
		
		DateFormat dateFormat		= new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss");
		Date currentDate 			= new Date();
		
		int biddingPictureSize					= 0;
		
		Dashboard dashboard 					= new Dashboard();
		List<PictureGallery> pictureGallery 	= new ArrayList<PictureGallery>();
		List<BiddingResult> biddingResultList	= new ArrayList<BiddingResult>();
		Pictures pictures						= new Pictures();
		Bidding bidding							= null;
		Advertisement advertisementBean			= null;
		List<Category> allCategory 				= new ArrayList<Category>();
		List<Location> allLocation				= new ArrayList<Location>();	
		List<String> conditionList				= new ArrayList<String>();
		
		IhanapmokoDashboardHelper dashboardHelper 					= new IhanapmokoDashboardHelper();
		IhanapmokoPicturesServicesHelper  picturesServicesHelper	= new IhanapmokoPicturesServicesHelper();
		IhanapmokoBiddingHelper biddingHelper						= new IhanapmokoBiddingHelper();
		IhanapmokoPicturesHelper picturesHelper						= new IhanapmokoPicturesHelper();
		IhanapmokoAdvertisementHelper advertisementHelper			= new IhanapmokoAdvertisementHelper();
		IhanapmokoCategoryHelper categoryHelper 					= new IhanapmokoCategoryHelper();
		IhanapmokoLocationHelper locationHelper 					= new IhanapmokoLocationHelper();	
		IhanapmokoUserHelper userHelper								= new IhanapmokoUserHelper();
		
		if(ServletFileUpload.isMultipartContent(request)){
			try{
				imagesPathPermanent	= Configurator.getConfig("IMAGES_FILE_PATH_PERMANENT");
				List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
				
				for(FileItem item : multiparts){
					if(item.isFormField()){
						fieldName = item.getFieldName();
						if(fieldName.equals("dashboardId")){
							dashboardId = item.getString();
						}
						if(fieldName.equals("message")){
							message = item.getString();
						}
						if(fieldName.equals("advertisement")){
							advertisement = item.getString();
						}
						if(fieldName.equals("category_id")){
							category_id = item.getString();
						}
						if(fieldName.equals("budget")){
							budget = item.getString();
						}
						if(fieldName.equals("item_condition")){
							item_condition = item.getString();
						}
						if(fieldName.equals("location_id")){
							location_id = item.getString();
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
						if(fieldName.equals("description")){
							description = item.getString();
						}
						if(fieldName.equals("password")){
							password = item.getString();
						}
						if(fieldName.equals("dashboardCommand")){
							dashboardCommand = item.getString();
						}
					}
					if(item!=null){
						if(item.getSize() < 8242880){
							if(!item.isFormField()){
								String name = new File(item.getName()).getName();
								if(name!=null && !name.equals("")){
									item.write(new File(imagesPathPermanent + File.separator + dateFormat.format(currentDate) + "_" + name));
									System.out.println("File name : " + name);
									fileName = dateFormat.format(currentDate) +  "_" + name;	
									pictures.setPicture_destination(fileName);
									picturesHelper.createPictures(pictures);
								}								
							}
						}else{
							System.out.println( " File is too large ");
							errorMessage = errorMessage + " File is too large ";
						}
					}
				}
				
				if(number_is_private==null){
					number_is_private = ConstantsUtil.NUMBER_IS_PUBLIC;
				}else{
					number_is_private = ConstantsUtil.NUMBER_IS_PRIVATE;
				}
				
				if(message!=null){
					pictures = picturesHelper.fetchPictureByName(fileName);
					bidding = new Bidding();
					bidding.setAdvertisement_id(Integer.valueOf(dashboardId));
					bidding.setDate_posted(currentDate);
					bidding.setMessage(message);
					if(pictures!=null){
						bidding.setPicture_id(pictures.getId());
					}					
					//get user in session
					try{
						bidding.setUser_id(userHelper.fetchUserIdBySession(request, response));
					}catch(Exception e){
						e.printStackTrace();
					}
					
					biddingHelper.createBidding(bidding);
				}else if(dashboardCommand.equals("edit")){
					advertisementBean = new Advertisement();
					advertisementBean = advertisementHelper.fetchAdvertismentById(dashboardId);
					if(advertisementBean.getPassword().equals(password)){						
						if(number_is_private==null){
							number_is_private = "0";
						}
						advertisementBean.setAdvertisement(advertisement);
						advertisementBean.setCategory_id(Integer.valueOf(category_id));
						advertisementBean.setBudget(budget);
						advertisementBean.setItem_condition(item_condition);
						advertisementBean.setLocation_id(Integer.valueOf(location_id));
						advertisementBean.setContact_number(contact_number);
						advertisementBean.setNumber_is_private(number_is_private);
						advertisementBean.setEmail_address(email_address);
						advertisementBean.setDescription(description);
						advertisementHelper.updateAdvertisement(advertisementBean);
					}else{
						errorMessage = "Wrong password";
					}
				}else if(dashboardCommand.equals("delete")){
					advertisementBean = new Advertisement();
					advertisementBean = advertisementHelper.fetchAdvertismentById(dashboardId);
					if(advertisementBean.getPassword().equals(password)){
						advertisementHelper.deleteAdvertisement(advertisementBean);
					}else{
						errorMessage = "Password did not match";
					}
				}
				
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
		
		try{
			dashboard 				= dashboardHelper.fetchDashboardAdvertismentById(dashboardId);
			pictureGallery 			= picturesServicesHelper.fetchPictureGalleryByAdId(dashboardId);
			biddingResultList		= biddingHelper.fetchBiddingByAdvertisementId(dashboardId);
			allCategory 			= categoryHelper.fetchAllCategory();
			allLocation 			= locationHelper.fetchAllLocation();
			
			conditionList.add("Brand New");
			conditionList.add("Used");
			
			for(BiddingResult result : biddingResultList){
				if(result.getPicture_id()!=0){
					pictures = picturesHelper.fetchPictureById(String.valueOf(result.getPicture_id()));					
					result.setPicture_destination(pictures.getPicture_destination());
					biddingPictureSize++;
				}			
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		System.out.println("DASHBOARD ID:" + dashboardId);
		System.out.println("DASHBOARD PICTURE:" + dashboard.getPicture_destination());
		
		userHelper.checkUser(request,response);
		
		request.setAttribute("dashboard", dashboard);
		request.setAttribute("pictureGallery", pictureGallery);
		request.setAttribute("biddingList", biddingResultList);
		request.setAttribute("biddingPictureSize", biddingPictureSize);		
		request.setAttribute("allCategory", allCategory);
		request.setAttribute("allLocation", allLocation);
		request.setAttribute("conditionList", conditionList);
		request.setAttribute("errorMessage", errorMessage);
		
		if(dashboardCommand!=null && dashboardCommand.equals("delete") && errorMessage.equals("")){
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}else{
			request.getRequestDispatcher("/WEB-INF/jsp/dashboard.jsp").forward(request, response);
		}
				
	}
	
}
