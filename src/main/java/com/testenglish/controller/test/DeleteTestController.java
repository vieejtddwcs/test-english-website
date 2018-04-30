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
import com.testenglish.model.user.User;

@WebServlet("/deletetest")
public class DeleteTestController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private TestDAOImpl testDAO;
	private Map<String, String> URLs;
	
	public DeleteTestController() {
		super();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void init() throws ServletException {
		testDAO = new TestDAOImpl();
		URLs = (HashMap<String, String>)getServletContext().getAttribute("URLs");
	}
	
	/*
	 * Delete only if the test is found and uploaderId equals userId
	 * Redirect to /viewtest if uploaderId differs userId
	 * Any other case, redirect to /testlist
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = (User)request.getSession().getAttribute("user");
		String strId = request.getParameter("id");
		
		try {
			int testId = Integer.parseInt(strId);
			int uploaderId = testDAO.select(testId).getUploaderId();
			
			if (uploaderId == user.getId()) {
				synchronized (request.getSession()) {
					testDAO.delete(testId);
				}
			}
			else {
				response.sendRedirect(URLs.get("VIEW_TEST") + "?id=" + testId);
				return;
			}
		} catch (NullPointerException | NumberFormatException e) {}
		
		response.sendRedirect(URLs.get("TEST_LIST"));
	}

}