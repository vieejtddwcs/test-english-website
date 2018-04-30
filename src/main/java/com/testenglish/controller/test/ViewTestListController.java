package com.testenglish.controller.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.testenglish.dao.impl.TestDAOImpl;
import com.testenglish.dao.impl.UserDAOImpl;
import com.testenglish.model.test.Test;
import com.testenglish.model.test.TestComparator;

@WebServlet("/testlist")
public class ViewTestListController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private TestDAOImpl testDAO;
	private UserDAOImpl userDAO;
	private TestComparator comparator;
	private Map<String, String> URLs;
	private Map<String, String> webpages;
	
	public ViewTestListController() {
		super();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void init() throws ServletException {
		testDAO = new TestDAOImpl();
		userDAO = new UserDAOImpl();
		comparator = new TestComparator();
		URLs = (HashMap<String, String>)getServletContext().getAttribute("URLs");
		webpages = (HashMap<String, String>)getServletContext().getAttribute("webpages");
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Test> testList = new ArrayList<>();
		String strUploaderId = request.getParameter("uploader_id");
		String uploadDateOrder = request.getParameter("upload_date");
		String takenCountOrder = request.getParameter("taken_count");
		String redirectURL = URLs.get("TEST_LIST") + "?uploader_id=$1&upload_date=$2&taken_count=$3";
		
		// Redirect non-params request
		if (strUploaderId == null || uploadDateOrder == null || takenCountOrder == null) {
			response.sendRedirect(redirectURL.replace("$1", "all")
											.replace("$2", "newest")
											.replace("$3", "most"));
			return;
		}
		
		// Select testList
		try {
			int uploaderId = Integer.parseInt(strUploaderId);
			testList = testDAO.selectByUploader(uploaderId);
			request.setAttribute("uploaderId", userDAO.select(uploaderId).getId());
		} catch (NumberFormatException | NullPointerException e) {
			testList = testDAO.selectAll();
			request.setAttribute("uploaderId", "all");
		}
		
		// Sort testList by uploadDate
		if (uploadDateOrder.equals("oldest")) {
			Collections.sort(testList, comparator.sortBy("OLDEST_UPLOAD_DATE"));
			request.setAttribute("uploadDateOrder", "oldest");
		}
		else {
			Collections.sort(testList, comparator.sortBy("NEWEST_UPLOAD_DATE"));
			request.setAttribute("uploadDateOrder", "newest");
		}
		
		// Sort testList by takenCount
		if (takenCountOrder.equals("least")) {
			Collections.sort(testList, comparator.sortBy("LEAST_TAKEN_COUNT"));
			request.setAttribute("takenCountOrder", "least");
		}
		else {
			Collections.sort(testList, comparator.sortBy("MOST_TAKEN_COUNT"));
			request.setAttribute("takenCountOrder", "most");
		}
		
		request.setAttribute("testList", testList);
		request.getRequestDispatcher(webpages.get("TEST_LIST")).forward(request, response);
	}

}