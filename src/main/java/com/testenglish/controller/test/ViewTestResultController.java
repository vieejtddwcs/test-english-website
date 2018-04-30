package com.testenglish.controller.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.testenglish.dao.impl.TestDAOImpl;
import com.testenglish.dao.impl.TestRecordDAOImpl;
import com.testenglish.dao.impl.UserDAOImpl;
import com.testenglish.model.test.Test;
import com.testenglish.model.test.TestRecord;

@WebServlet("/viewresult")
public class ViewTestResultController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private TestRecordDAOImpl testRecordDAO;
	private TestDAOImpl testDAO;
	private UserDAOImpl userDAO;
	private Map<String, String> URLs;
	private Map<String, String> webpages;
	
	public ViewTestResultController() {
		super();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void init() throws ServletException {
		testRecordDAO = new TestRecordDAOImpl();
		testDAO = new TestDAOImpl();
		userDAO = new UserDAOImpl();
		URLs = (HashMap<String, String>)getServletContext().getAttribute("URLs");
		webpages = (HashMap<String, String>)getServletContext().getAttribute("webpages");
	}
	
	/*
	 * If testRecordId is invalid, redirect to /testlist
	 * Else forward to /testhistory
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String strId = request.getParameter("id");
		try {
			int id = Integer.parseInt(strId);
			TestRecord testRecord = testRecordDAO.select(id);
			Test test = testDAO.select(testRecord.getTestId());
			String userName = userDAO.select(testRecord.getUserId()).getName();
			
			request.setAttribute("testRecord", testRecord);
			request.setAttribute("test", test);
			request.setAttribute("userName", userName);
			
			request.getRequestDispatcher(webpages.get("VIEW_RESULT")).forward(request, response);
			
		} catch (NumberFormatException e) {
			response.sendRedirect(URLs.get("TEST_HISTORY"));
		}
		
	}
	
}