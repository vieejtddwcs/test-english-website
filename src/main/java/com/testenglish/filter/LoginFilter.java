package com.testenglish.filter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.testenglish.dao.impl.LoginRecordDAOImpl;
import com.testenglish.dao.impl.UserDAOImpl;
import com.testenglish.model.user.LoginRecord;
import com.testenglish.model.user.User;
import com.testenglish.util.CookieHelper;

@WebFilter("/*")
public class LoginFilter implements Filter {
	
	private LoginRecordDAOImpl loginRecordDAO;
	private UserDAOImpl userDAO;
	private Set<String> guestsOnlyResources = new HashSet<>();
	private Map<String, String> URLs;
	private Map<String, String> webpages;

	@SuppressWarnings("unchecked")
	@Override
	public void init(FilterConfig config) throws ServletException {
		loginRecordDAO = new LoginRecordDAOImpl();
		userDAO = new UserDAOImpl();
		URLs = (HashMap<String, String>)config.getServletContext().getAttribute("URLs");
		webpages = (HashMap<String, String>)config.getServletContext().getAttribute("webpages");
		
		guestsOnlyResources.add(URLs.get("LOGIN"));
		guestsOnlyResources.add(URLs.get("REGISTER"));
	}
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)res;
		String requestURI = request.getRequestURI();
		
		boolean loggedIn = request.getSession().getAttribute("user") != null;
		boolean staticResourcesRequested = requestURI.contains("/css/")
				|| requestURI.contains("/js/");
		boolean guestsResourcesRequested = guestsOnlyResources.contains(requestURI);
		
		// Allow all clients to access to static resources
		if (staticResourcesRequested) {
			chain.doFilter(req, res);
		}
		
		// Logged-in users cannot access guests only resources
		else if (loggedIn && guestsResourcesRequested) {
			response.sendRedirect(URLs.get("INDEX"));
		}
		
		// Logged-in users or guests access their own resources
		else if (loggedIn || guestsResourcesRequested) {
			chain.doFilter(req, res);
		}
		
		// Guests try to access users only resources
		// Implement auto-login
		else {
			Cookie loginCookie = CookieHelper.getCookie(request, "LOGIN_UUID");
			
			if (loginCookie != null) { // Login cookie represents
				LoginRecord loginRecord = loginRecordDAO.select(loginCookie.getValue());
				
				if (loginRecord != null) { // Login cookie found in database
					User user = userDAO.select(loginRecord.getUserId());
					request.getSession().setAttribute("user", user);
					
					loginRecordDAO.delete(user.getId());
					CookieHelper.removeCookie(response, "LOGIN_UUID");
					
					String newUuid = UUID.randomUUID().toString();
					int maxAge = Integer.parseInt(req.getServletContext().getInitParameter("login_cookie_max_age"));
					loginRecordDAO.insert(new LoginRecord(newUuid, user.getId(), LocalDateTime.now()));
					CookieHelper.addCookie(response, new Cookie("LOGIN_UUID", newUuid), maxAge);
					
					chain.doFilter(req, res);
				}
				else {
					CookieHelper.removeCookie(response, "LOGIN_UUID");
					request.getRequestDispatcher(webpages.get("LOGIN_WARNING")).forward(request, response);
				}
			}
			else response.sendRedirect(URLs.get("LOGIN"));
		}
	}
	
	@Override
	public void destroy() {}

}