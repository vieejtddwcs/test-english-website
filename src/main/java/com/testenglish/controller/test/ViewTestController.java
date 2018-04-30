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
import com.testenglish.dao.impl.TestTypeDAOImpl;
import com.testenglish.dao.impl.UserDAOImpl;
import com.testenglish.model.test.Test;
import com.testenglish.model.test.TestType;

@WebServlet({"/viewtest", "/edittest", "/taketest"})
public class ViewTestController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private TestDAOImpl testDAO;
	private TestTypeDAOImpl testTypeDAO;
	private UserDAOImpl userDAO;
	private Map<String, String> URLs;
	private Map<String, String> webpages;
	
	public ViewTestController() {
		super();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void init() throws ServletException {
		testDAO = new TestDAOImpl();
		testTypeDAO = new TestTypeDAOImpl();
		userDAO = new UserDAOImpl();
		URLs = (HashMap<String, String>)getServletContext().getAttribute("URLs");
		webpages = (HashMap<String, String>)getServletContext().getAttribute("webpages");
	}
	
	/*
	 * If testId is invalid or test is not found, redirect to /testlist
	 * Else forward to the request address
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String requestURI = request.getRequestURI();
		String strId = request.getParameter("id");
		try {
			Test test = testDAO.select(Integer.parseInt(strId));
			request.setAttribute("test", test);
			
			if (requestURI.contains("/viewtest")) {
				String uploaderName = userDAO.select(test.getUploaderId()).getName();
				request.setAttribute("uploaderName", uploaderName);
				request.getRequestDispatcher(webpages.get("VIEW_TEST")).forward(request, response);
			}
			if (requestURI.contains("/edittest")) {
				request.getRequestDispatcher(webpages.get("EDIT_TEST")).forward(request, response);
			}
			if (requestURI.contains("/taketest")) {
				TestType testType = testTypeDAO.selectByType(test.getType());
				request.setAttribute("testType", testType);
				request.getRequestDispatcher(webpages.get("TAKE_TEST")).forward(request, response);
			}
		} catch (NullPointerException | NumberFormatException e) {
			response.sendRedirect(URLs.get("TEST_LIST"));
		}
	}

}