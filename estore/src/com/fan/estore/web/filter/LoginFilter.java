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
import javax.servlet.http.HttpSession;
import com.fan.estore.bean.Customer;

public class LoginFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//对编码进行控制
		HttpServletRequest requ = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		//判断session中是否有值
		HttpSession session = requ.getSession();
		Customer customer = (Customer) session.getAttribute("customer");
		if(customer!=null){
			chain.doFilter(requ, resp);
		}else{
			if(requ.getRequestURI().equals("/estore/login.jsp")||requ.getRequestURI().equals("/estore/register.jsp")){
				String[] split = requ.getRequestURI().split("/");
				requ.getRequestDispatcher("/"+split[2]).forward(requ, resp);
			}else{
				//requ.getRequestDispatcher("/login.jsp").forward(requ, resp);
				resp.sendRedirect(requ.getContextPath()+"/login.jsp");
				System.out.println("跳转登录界面");
			}
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("EncondingFilter-init");
	}

	@Override
	public void destroy() {
		System.out.println("EncondingFilter-destory");
	}
}

