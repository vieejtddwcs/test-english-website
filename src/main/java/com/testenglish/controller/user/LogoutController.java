package com.testenglish.controller.user;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.testenglish.dao.impl.LoginRecordDAOImpl;
import com.testenglish.model.user.User;
import com.testenglish.util.CookieHelper;

@WebServlet("/logout")
public class LogoutController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private LoginRecordDAOImpl loginRecordDAO;
	private Map<String, String> URLs;

	public LogoutController() {
		super();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void init() throws ServletException {
		loginRecordDAO = new LoginRecordDAOImpl();
		URLs = (HashMap<String, String>)getServletContext().getAttribute("URLs");
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = (User)request.getSession().getAttribute("user");
		loginRecordDAO.delete(user.getId());
		CookieHelper.removeCookie(response, "LOGIN_UUID");
		
		request.getSession().invalidate();
		response.sendRedirect(URLs.get("INDEX"));
	}

}