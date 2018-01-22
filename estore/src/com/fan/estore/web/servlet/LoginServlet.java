package com.fan.estore.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.fan.estore.bean.Book;
import com.fan.estore.bean.Customer;
import com.fan.estore.bean.Order;
import com.fan.estore.service.IBookService;
import com.fan.estore.service.ICustomerService;
import com.fan.estore.service.IOrderService;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//在Spring的自动注入中普通的POJO类都可以使用@Autowired进行自动注入，但是除了两类：Filter和Servlet无法使用自动注入属性。
	//（因为这两个归tomcat容器管理）可以用init(集承自HttpServlet后重写init方法)方法中实例化对象，
	/*private ICustomerService customerService;
	private IBookService bookService;
	private IOrderService orderService;*/
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获得容器，从监听器中获得，获得spring容器，从application域中获得即可
		//1获得servletContext对象
		ServletContext sc = request.getServletContext();
		//2.从sc中获得ac容器
		WebApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(sc);
		//3.从容器中获得customerService,bookService,orderService
		ICustomerService customerService = (ICustomerService) ac.getBean("customerService");
		IBookService bookService = (IBookService) ac.getBean("bookService");
		IOrderService orderService = (IOrderService) ac.getBean("orderService");
		
		
		
		//-----------------------------------------------
		String username = request.getParameter("userid");
		String pwd = request.getParameter("password");
		//判断username和pwd是通过页面来控制判断
		try {
			Customer customer = customerService.getLogin(username, pwd);
			//用户保存到session中
			HttpSession session = request.getSession();
			session.setAttribute("customer", customer);
			//跳转首页之前对book进行查询
			List<Book> allBooks = bookService.getListAllBooks();
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
