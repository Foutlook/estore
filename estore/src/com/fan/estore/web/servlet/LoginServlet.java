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
import com.fan.estore.bean.Order;
import com.fan.estore.service.BookServiceImpl;
import com.fan.estore.service.CustomerServiceImpl;
import com.fan.estore.service.IBookService;
import com.fan.estore.service.ICustomerService;
import com.fan.estore.service.IOrderService;
import com.fan.estore.service.OrderServiceImpl;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ICustomerService customerService = new CustomerServiceImpl();
	IBookService bookService = new BookServiceImpl();
	IOrderService orderService = new OrderServiceImpl();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("userid");
		String pwd = request.getParameter("password");
		//判断username和pwd是通过页面来控制判断
		try {
			Customer customer = customerService.login(username, pwd);
			//用户保存到session中
			HttpSession session = request.getSession();
			session.setAttribute("customer", customer);
			//跳转首页之前对book进行查询
			List<Book> allBooks = bookService.listAllBooks();
			session.setAttribute("allBooks", allBooks);
			//跳转之前通过customer id对order查询
			List<Order> allOrders = orderService.findAllOrderByCusId(customer.getId());
			session.setAttribute("orderByCusId", allOrders);
			response.sendRedirect(request.getContextPath()+"/index.jsp");
		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
