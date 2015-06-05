package com.ihanapmoko.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ihanapmoko.bean.SearchResult;
import com.ihanapmoko.helper.IhanapmokoSearchHelper;

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

//		
//		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/search.jsp");
//		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("SERVLET CALL: IHANAP MOKO");
		
		String searchParameter 	= request.getParameter("searchParameter");
		Date currentDate 		= new Date();
		
		System.out.println("IHANAP MOKO SEARCH PARAMETER:" + searchParameter);
		
		List<SearchResult> lstSearchResult = new ArrayList<SearchResult>();
		IhanapmokoSearchHelper searchHelper = new IhanapmokoSearchHelper();
		
		lstSearchResult = searchHelper.searchAdvertisement(searchParameter);
		
		for(SearchResult objSearch : lstSearchResult){
			System.out.println("SEARCH RESULT ID:" + objSearch.getAdvertisementId());
			System.out.println("SEARCH RESULT ADVERTISEMENT:" + objSearch.getAdvertisement());
			System.out.println("SEARCH RESULT DATE CREATED:" + objSearch.getDate_created());
			System.out.println("SEARCH RESULT LOCATION:" + objSearch.getLocation());
			System.out.println("SEARCH RESULT PICTURE:" + objSearch.getPicture_destination());
		}
		
		request.setAttribute("lstFileNameSize", lstSearchResult.size());
		request.setAttribute("searchParameter", searchParameter);
		request.setAttribute("currentDate", currentDate);
		request.setAttribute("lstSearchResult", lstSearchResult);
		
		request.getRequestDispatcher("/WEB-INF/jsp/searchresult.jsp").forward(request, response);
		
	}

}
