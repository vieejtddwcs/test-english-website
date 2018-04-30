package com.testenglish.controller.user;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.testenglish.dao.impl.UserDAOImpl;
import com.testenglish.model.user.User;

@WebServlet("/profile")
public class ViewProfileController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private UserDAOImpl userDAO;
	private Map<String, String> URLs;
	private Map<String, String> webpages;
	
	public ViewProfileController() {
		super();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void init() throws ServletException {
		userDAO = new UserDAOImpl();
		URLs = (HashMap<String, String>)getServletContext().getAttribute("URLs");
		webpages = (HashMap<String, String>)getServletContext().getAttribute("webpages");
	}
	
	/*
	 * If userId is invalid, redirect to the user's profile page.
	 * Else forward to the viewing user's profile page.
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = (User)request.getSession().getAttribute("user");
		String strId = request.getParameter("id");
		try {
			User viewingUser = userDAO.select(Integer.parseInt(strId));
			request.setAttribute("viewingUser", viewingUser);
			request.getRequestDispatcher(webpages.get("VIEW_PROFILE")).forward(request, response);
		} catch (NumberFormatException e) {
			response.sendRedirect(URLs.get("VIEW_PROFILE") + "?id=" + user.getId());
		}
	}
	
}