/**
 * Feb 25, 2011 
 * AuthorityFilter.java 
 */
package com.mrb.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Administrator 5:02:38 PM
 */
public class AuthorityFilter implements Filter {

	public void destroy() {
	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		String uri = request.getRequestURI();

		// System.out.println("uri:"+uri);
		// || uri.contains("html") || uri.contains("htm") || uri.endsWith("/")
		if (uri.endsWith("index.jsp") || uri.endsWith("/")) {
			HttpSession session = request.getSession();
			response.sendRedirect(request.getContextPath() + "/index.do");
			return;
			
			/*
			 * if (session.getAttribute(AuthorityInterceptor.LOGINED_FLAG) ==
			 * null) { response.sendRedirect(request.getContextPath() +
			 * "/admin/jumpLoginPage.action"); return; }
			 */
		}
		chain.doFilter(req, res);
		return;
	}

	// @see javax.servlet.Filterinit(javax.servlet.FilterConfig)
	public void init(FilterConfig paramConfig) throws ServletException {
	}

}
