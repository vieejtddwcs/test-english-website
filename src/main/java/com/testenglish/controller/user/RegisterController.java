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
import com.testenglish.util.MyStringUtils;

@WebServlet("/register")
public class RegisterController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private UserDAOImpl userDAO;
	private Map<String, String> webpages;
	private Map<String, String> messages;
	
	public RegisterController() {
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
		request.getRequestDispatcher(webpages.get("REGISTER")).forward(request, response);
	}
	
	/*
	 * If email already exists, forward to /register.
	 * Else redirect to /login.
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = MyStringUtils.hash256(request.getParameter("password"));
		String name = request.getParameter("name");
		Gender gender = Gender.valueOf(request.getParameter("gender"));
		
		if (!userDAO.emailExists(email)) {
			User user = new User();
			user.setRole(User.Role.USER);
			user.setEmail(email);
			user.setPassword(password);
			user.setName(name);
			user.setGender(gender);
			
			synchronized (getServletContext()) {
				user.setId(userDAO.count() + 1);
				userDAO.insert(user);
			}
			
			request.setAttribute("message", messages.get("PLEASE_LOGIN"));
			request.getRequestDispatcher(webpages.get("LOGIN")).forward(request, response);
		}
		else {
			request.setAttribute("message", messages.get("EMAIL_EXISTS"));
			doGet(request, response);
		}
	}

}