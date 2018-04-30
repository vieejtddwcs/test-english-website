package com.testenglish.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/*
 * Filter pages which contain input tag that can have non-English characters.
 */
@WebFilter({"/register", "/updateprofile", "/uploadtest"})
public class CharacterEncodingFilter implements Filter {

	@Override
	public void init(FilterConfig config) throws ServletException {}
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		req.setCharacterEncoding("UTF-8");
		chain.doFilter(req, res);
	}
	
	@Override
	public void destroy() {}

}