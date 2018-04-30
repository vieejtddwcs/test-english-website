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
import com.testenglish.util.MyStringUtils;

@WebServlet("/usersecurity")
public class UserSecurityController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private UserDAOImpl userDAO;
	private Map<String, String> webpages;
	private Map<String, String> messages;
	
	@SuppressWarnings("unchecked")
	public void init() throws ServletException {
		userDAO = new UserDAOImpl();
		webpages = (HashMap<String, String>)getServletContext().getAttribute("webpages");
		messages = (HashMap<String, String>)getServletContext().getAttribute("messages");
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher(webpages.get("USER_SECURITY")).forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = (User)request.getSession().getAttribute("user");
		String newEmail = request.getParameter("new_email");
		String confirmPassword = request.getParameter("confirm_password");
		String oldPassword = request.getParameter("old_password");
		String newPassword = request.getParameter("new_password");
		
		boolean changeEmailRequested = oldPassword == null && newPassword == null;
		boolean changePasswordRequested = newEmail == null && confirmPassword == null;
		
		if (changeEmailRequested) {
			confirmPassword = MyStringUtils.hash256(confirmPassword);
			
			if (confirmPassword.equals(user.getPassword())) {
				user.setEmail(newEmail);
				userDAO.update(user);
				request.setAttribute("message", messages.get("EMAIL_CHANGED"));
			}
			else request.setAttribute("message", messages.get("PASSWORD_INCORRECT"));
			
		}
		
		if (changePasswordRequested) {
			oldPassword = MyStringUtils.hash256(oldPassword);
			newPassword = MyStringUtils.hash256(newPassword);
			
			if (oldPassword.equals(user.getPassword())) {
				user.setPassword(newPassword);
				userDAO.update(user);
				request.setAttribute("message", messages.get("PASSWORD_CHANGED"));
			}
			else request.setAttribute("message", messages.get("PASSWORD_INCORRECT"));
			
		}
		
		request.getRequestDispatcher(webpages.get("USER_SECURITY")).forward(request, response);
	}
	
}