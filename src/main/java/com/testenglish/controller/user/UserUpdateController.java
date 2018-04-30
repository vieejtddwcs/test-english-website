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
import com.testenglish.model.user.User.Gender;

@WebServlet("/updateprofile")
public class UserUpdateController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private UserDAOImpl userDAO;
	private Map<String, String> webpages;
	private Map<String, String> messages;
	
	public UserUpdateController() {
		super();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void init() throws ServletException {
		userDAO = new UserDAOImpl();
		webpages = (HashMap<String, String>)getServletContext().getAttribute("webpages");
		messages = (HashMap<String, String>)getServletContext().getAttribute("messages");
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher(webpages.get("UPDATE_PROFILE")).forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		Gender gender = Gender.valueOf(request.getParameter("gender"));
		
		User user = (User)request.getSession().getAttribute("user");
		user.setName(name);
		user.setGender(gender);
		userDAO.update(user);
		
		request.setAttribute("message", messages.get("PROFILE_UPDATED"));
		doGet(request, response);
	}

}