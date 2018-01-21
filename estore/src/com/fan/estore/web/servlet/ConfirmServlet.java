package com.fan.estore.web.servlet;

import java.io.IOException;
import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.fan.estore.bean.Customer;
import com.fan.estore.bean.Line;
import com.fan.estore.bean.Order;
import com.fan.estore.bean.ShoppingCar;
import com.fan.estore.myexception.OrderException;
import com.fan.estore.service.IOrderService;

@WebServlet("/confirmServlet")
public class ConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// IOrderService orderService = new OrderServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获得容器，从监听器中获得，获得spring容器，从application域中获得即可
		// 1获得servletContext对象
		ServletContext serCon = request.getServletContext();
		// 2.从sc中获得ac容器
		WebApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(serCon);
		// 3.从容器中获得orderService
		IOrderService orderService = (IOrderService) ac.getBean("orderService");
		// -----------------------------------------------

		HttpSession session = request.getSession();
		Customer customer = (Customer) session.getAttribute("customer");
		ShoppingCar sc = (ShoppingCar) session.getAttribute("shoppingCar");
		Order order = new Order();
		order.setCost(sc.getCost());
		order.setOrderDate(new Date(System.currentTimeMillis()));
		// 遍历sc中的map集合
		Set<Entry<Long, Line>> entrySet = sc.getLines().entrySet();
		Set<Line> lines = new HashSet<>();
		for (Entry<Long, Line> entry : entrySet) {
			lines.add(entry.getValue());
		}
		order.setLines(lines);
		// 订单保存到数据库
		try {
			orderService.saveConfirmOrder(customer, order, lines);
			// 查询本顾客ID的订单
			List<Order> orderByCusId = orderService.findAllOrderByCusId(customer.getId());
			System.out.println(orderByCusId);
			// 把订单保存到session中
			session.setAttribute("orderByCusId", orderByCusId);
			// 保存成功跳转到index.jsp
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		} catch (OrderException e) {
			e.printStackTrace();
			session.setAttribute("orderError", e.getMessage());
			request.getRequestDispatcher("/confirmOrder.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
