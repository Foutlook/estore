package com.fan.estore.web.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EcondingFilter implements Filter {

	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest requ = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		requ.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		/*//增强对象   
		EnhanceRequest enhanceRequest = new EnhanceRequest(requ);*/
		chain.doFilter(requ, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}

