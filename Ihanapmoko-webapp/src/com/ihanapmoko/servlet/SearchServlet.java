package com.ihanapmoko.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ihanapmoko.bean.Category;
import com.ihanapmoko.bean.Location;
import com.ihanapmoko.bean.SearchResult;
import com.ihanapmoko.bean.SearchSize;
import com.ihanapmoko.bean.User;
import com.ihanapmoko.helper.Configurator;
import com.ihanapmoko.helper.IhanapmokoCategoryHelper;
import com.ihanapmoko.helper.IhanapmokoLocationHelper;
import com.ihanapmoko.helper.IhanapmokoSearchHelper;
import com.ihanapmoko.helper.IhanapmokoUserHelper;
import com.ihanapmoko.util.SearchPagination;

/**
 * Servlet implementation class SearchServlet
 */
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doProcess(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doProcess(request,response);
		
	}
	
	public void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		System.out.println("SERVLET CALL: IHANAP MOKO");
		
		String searchParameter 		= request.getParameter("searchParameter");
		String location_id			= request.getParameter("location_id");
		String category_id			= request.getParameter("category_id");
		String page					= request.getParameter("_pg");
		
		String startRow				= null;
		String pageLink				= null;
		
		Date currentDate 			= new Date();
		
		List<Category> allCategory 	= new ArrayList<Category>();
		List<Location> allLocation 	= new ArrayList<Location>();
		
		
		IhanapmokoCategoryHelper categoryHelper = new IhanapmokoCategoryHelper();
		IhanapmokoLocationHelper locationHelper = new IhanapmokoLocationHelper();
		List<SearchResult> lstSearchResult 		= new ArrayList<SearchResult>();
		IhanapmokoSearchHelper searchHelper 	= new IhanapmokoSearchHelper();
		IhanapmokoUserHelper userHelper			= new IhanapmokoUserHelper();
		
		SearchPagination searchPagination 	= new SearchPagination();
		SearchSize searchSize				= new SearchSize();
		
		if(page==null){
			page = "1";
		}
		
		if(location_id==null){
			location_id = "";
		}
		if(category_id==null){
			category_id = "";
		}
		
		startRow = searchHelper.startPage(Integer.valueOf(page));
		
		lstSearchResult = searchHelper.searchAdvertisement(searchParameter, location_id, category_id, startRow);
		searchSize		= searchHelper.searchAdvertisementSize(searchParameter, location_id, category_id);
		
		allCategory = categoryHelper.fetchAllCategory();
		allLocation = locationHelper.fetchAllLocation();
		
		for(SearchResult objSearch : lstSearchResult){
			System.out.println("SEARCH RESULT ID:" + objSearch.getAdvertisementId());
			System.out.println("SEARCH RESULT ADVERTISEMENT:" + objSearch.getAdvertisement());
			System.out.println("SEARCH RESULT DATE CREATED:" + objSearch.getDate_created());
			System.out.println("SEARCH RESULT LOCATION:" + objSearch.getLocation());
			System.out.println("SEARCH RESULT PICTURE:" + objSearch.getPicture_destination());
		}
		
		pageLink = Configurator.getConfig("PAGINATION_LINK") + "?" + "searchParameter=" + searchParameter
				+ "&" + "location_id" + "=" + location_id + "&" + "category_id" + "=" + category_id;
		
		searchPagination.setLink(pageLink);
		searchPagination.setRecordsPerPage(12);
		searchPagination.setPageCountPerSet(12);
		searchPagination.setPageFrom(page);
		searchPagination.setTotalRecords(searchSize.getSearchTotalSize());
		
		userHelper.checkUser(request,response);		
		
		request.setAttribute("lstFileNameSize", lstSearchResult.size());
		request.setAttribute("searchParameter", searchParameter);
		request.setAttribute("currentDate", currentDate);
		request.setAttribute("lstSearchResult", lstSearchResult);
		request.setAttribute("allCategory", allCategory);
		request.setAttribute("allLocation", allLocation);
		request.setAttribute("selectedLocation", location_id);
		request.setAttribute("selectedCategory", category_id);
		request.setAttribute("searchPagination", searchPagination.pageNav());
		
		
		request.getRequestDispatcher("/WEB-INF/jsp/searchresult.jsp").forward(request, response);
		
	}

}
