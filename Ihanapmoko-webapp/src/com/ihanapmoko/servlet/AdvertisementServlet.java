package com.ihanapmoko.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ihanapmoko.bean.Advertisement;
import com.ihanapmoko.bean.Category;
import com.ihanapmoko.bean.Location;
import com.ihanapmoko.helper.ConstantsUtil;
import com.ihanapmoko.helper.IhanapmokoAdvertisementHelper;
import com.ihanapmoko.helper.IhanapmokoCategoryHelper;
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String advertisement 		= request.getParameter("advertisement");
		String category_Id			= request.getParameter("category_Id");
		String contact_number		= request.getParameter("contact_number");
		String number_is_private	= request.getParameter("number_is_private");
		String email_address		= request.getParameter("email_address");
		String location_id			= request.getParameter("location_id");
		String budget				= request.getParameter("budget");
		String password				= request.getParameter("password");
		String confirmPassword		= request.getParameter("confirmPassword");
		String description			= request.getParameter("description");
		String condition			= request.getParameter("condition");
		String picture				= request.getParameter("picture");
		
		
		IhanapmokoAdvertisementHelper advertisementHelper = new IhanapmokoAdvertisementHelper();
		
		Advertisement advertisementBean = new Advertisement();
		
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
		System.out.println("CONDITION:" + condition);
		System.out.println("PICTURE:" + picture);
		
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
		advertisementBean.setCondition(condition);
		
		advertisementHelper.createAdvertisement(advertisementBean);
		
	}

}
