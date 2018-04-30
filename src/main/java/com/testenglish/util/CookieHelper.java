package com.testenglish.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieHelper {

	private CookieHelper() {
		throw new AssertionError();
	}
	
	/**
	 * Get the name-specified cookie from the HttpServletRequest object.
	 * 
	 * @param request - the request object of this HTTP request
	 * @param name - the name of the cookie
	 * @return a Cookie object, if not found return null
	 */
	public static Cookie getCookie(HttpServletRequest request, String name) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (name.equals(cookie.getName())) return cookie;
			}
		}
		return null;
	}
	
	
	/**
	 * Add this cookie to the HttpServletRespone object and set its max age.
	 * 
	 * @param response - the response object of this HTTP response
	 * @param cookie - the Cookie object to be added
	 * @param maxAge - the cookie's max age in seconds
	 */
	public static void addCookie(HttpServletResponse response, Cookie cookie, int maxAge) {
		cookie.setPath("/");
		cookie.setMaxAge(maxAge);
		response.addCookie(cookie);
	}
	
	
	/**
	 * Remove this name-specified cookie from the HttpServletResponse object.
	 * 
	 * @param response - the response object of this HTTP response
	 * @param name - the name of the cookie
	 */
	public static void removeCookie(HttpServletResponse response, String name) {
		addCookie(response, new Cookie(name, null), 0);
	}
	
}