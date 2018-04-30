package com.testenglish.controller.user;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.testenglish.dao.impl.LoginRecordDAOImpl;
import com.testenglish.dao.impl.UserDAOImpl;
import com.testenglish.model.user.LoginRecord;
import com.testenglish.model.user.User;
import com.testenglish.util.CookieHelper;
import com.testenglish.util.MyStringUtils;

@WebServlet("/login")
public class LoginController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private UserDAOImpl userDAO;
	private LoginRecordDAOImpl loginRecordDAO;
	private Map<String, String> URLs;
	private Map<String, String> webpages;
	private Map<String, String> messages;
		
	public LoginController() {
		super();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void init() throws ServletException {
		userDAO = new UserDAOImpl();
		loginRecordDAO = new LoginRecordDAOImpl();
		URLs = (HashMap<String, String>)getServletContext().getAttribute("URLs");
		webpages = (HashMap<String, String>)getServletContext().getAttribute("webpages");
		messages = (HashMap<String, String>)getServletContext().getAttribute("messages");
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher(webpages.get("LOGIN")).forward(request, response);
	}
	
	/*
	 * If email and password is incorrect, forward to /login.
	 * Else redirect to index.
	 * If remember login is checked, create a new login cookie.
	 * Else remove login cookie.
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = MyStringUtils.hash256(request.getParameter("password"));
		boolean rememberLogin = "true".equals(request.getParameter("remember_login"));
		
		User user = userDAO.select(email, password);
		
		if (user != null) {
			request.getSession().setAttribute("user", user);
			
			if (rememberLogin) {
				String uuid = UUID.randomUUID().toString();
				int maxAge = Integer.parseInt(getServletContext().getInitParameter("login_cookie_max_age"));
				loginRecordDAO.insert(new LoginRecord(uuid, user.getId(), LocalDateTime.now()));
				CookieHelper.addCookie(response, new Cookie("LOGIN_UUID", uuid), maxAge);
			}
			else {
				loginRecordDAO.delete(user.getId());
				CookieHelper.removeCookie(response, "LOGIN_UUID");
			}
			
			response.sendRedirect(URLs.get("INDEX"));
		}
		else {
			request.setAttribute("message", messages.get("EMAIL_PASSWORD_INCORRECT"));
			doGet(request, response);
		}
	}

}