package com.fan.estore.web.servlet;

import java.io.IOException;
import java.util.Map;

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
import com.fan.estore.bean.Line;
import com.fan.estore.bean.ShoppingCar;
import com.fan.estore.service.BookServiceImpl;
import com.fan.estore.service.IBookService;
import com.fan.estore.service.ILineService;
import com.fan.estore.service.IOrderService;
import com.fan.estore.service.OrderServiceImpl;

@WebServlet("/addOrderlineServlet")
public class AddOrderlineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/*
	 * IBookService bookService = new BookServiceImpl(); IOrderService
	 * orderService = new OrderServiceImpl();
	 */
	ShoppingCar sc = new ShoppingCar();

	// 把商品添加到购物清单中
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获得容器，从监听器中获得，获得spring容器，从application域中获得即可
		// 1获得servletContext对象
		ServletContext serCon = request.getServletContext();
		// 2.从sc中获得ac容器
		WebApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(serCon);
		// 3.从容器中获得bookService，customerService
		IBookService bookService = (IBookService) ac.getBean("bookService");
		IOrderService orderService = (IOrderService) ac.getBean("orderService");
		// -----------------------------------------------

		String bookid = request.getParameter("bookid");
		try {
			Line line = new Line();

			// 根据id查询书的信息
			Book book = bookService.findById(Long.parseLong(bookid));
			System.out.println("----" + book);
			HttpSession session = request.getSession();
			// book 保存到line中
			line.setBook(book);
			// 保存到shopcat
			sc.add(line);
			Map<Long, Line> lines = sc.getLines();
			session.setAttribute("lines", lines);
			session.setAttribute("shoppingCar", sc);
			// 跳转到shopingcat
			request.getRequestDispatcher("/shopcart.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
