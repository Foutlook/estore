package com.fan.estore.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fan.estore.bean.Customer;
import com.fan.estore.myexception.CustomerException;
import com.fan.estore.service.CustomerServiceImpl;
import com.fan.estore.service.ICustomerService;

@WebServlet("/updateServlet")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("userid");
		String pwd = request.getParameter("password");
		String country = request.getParameter("country");
		String province = request.getParameter("province");
		String city = request.getParameter("city");
		String street1 = request.getParameter("street1");
		StringBuilder sb = new StringBuilder();
		sb.append(country);
		sb.append(province);
		sb.append(city);
		sb.append(street1);
		String address = sb.toString();
		String zip = request.getParameter("zip"); //邮编
		String cellphone = request.getParameter("cellphone");//手机
		String email = request.getParameter("email");
		Customer customer = new Customer(username, pwd, zip, address, cellphone, email);
		ICustomerService customerService = new CustomerServiceImpl();
		System.out.println(username);
		try {
			//更新
			customerService.updateCustomer(customer);
			//重新登录，加入session中
			Customer nextLogin = customerService.login(username, pwd);
			HttpSession session = request.getSession();
			session.setAttribute("customer", nextLogin);
			response.sendRedirect(request.getContextPath()+"/index.jsp");
		} catch (CustomerException e) {
			request.setAttribute("error", e.getMessage());
			request.getRequestDispatcher("/user/userinfo.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
