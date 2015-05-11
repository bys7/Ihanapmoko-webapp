package com.ihanapmoko.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ihanapmoko.bean.Advertisement;
import com.ihanapmoko.bean.FilterLookup;
import com.ihanapmoko.helper.IhanapmokoAdvertisementHelper;
import com.ihanapmoko.helper.IhanapmokoFilterLookupHelper;

/**
 * Servlet implementation class AdvertisementInformationServlet
 */
public class AdvertisementInformationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdvertisementInformationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String errorMessage			= null;
		
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
		String item_condition		= request.getParameter("item_condition");
		String picture				= request.getParameter("picture");
		
		IhanapmokoFilterLookupHelper filterLookupHelper 	= new IhanapmokoFilterLookupHelper();
		
		List<FilterLookup> filterLookupList	= new ArrayList<FilterLookup>();
		
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
		
		filterLookupList = filterLookupHelper.fetchAllFilterLookup();
		
		if(advertisement!=null){
			for(FilterLookup filter : filterLookupList){
				if(advertisement.toLowerCase().contains(filter.getWords()) || description.toLowerCase().contains(filter.getWords())){
					System.out.println("ILLIGAL WORDS");
					errorMessage = errorMessage + filter.getWords() + " ";
				}
			}
		}
	}

}
