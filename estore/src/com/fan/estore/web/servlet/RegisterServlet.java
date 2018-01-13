package com.fan.estore.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fan.estore.bean.Book;
import com.fan.estore.bean.Customer;
import com.fan.estore.service.BookServiceImpl;
import com.fan.estore.service.CustomerServiceImpl;
import com.fan.estore.service.IBookService;
import com.fan.estore.service.ICustomerService;

@WebServlet("/registerServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	IBookService bookService = new BookServiceImpl();
       
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
			customerService.register(customer);
			//用户保存到session中
			HttpSession session = request.getSession();
			session.setAttribute("customer", customer);
			//查询书籍信息
			List<Book> allBooks = bookService.listAllBooks();
			session.setAttribute("allBooks", allBooks);
			response.sendRedirect(request.getContextPath()+"/index.jsp");
		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			System.out.println(e.getMessage());
			request.getRequestDispatcher("/register.jsp").forward(request, response);
		} 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
