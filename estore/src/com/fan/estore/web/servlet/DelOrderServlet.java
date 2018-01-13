package com.fan.estore.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fan.estore.bean.Customer;
import com.fan.estore.bean.Order;
import com.fan.estore.service.ILineService;
import com.fan.estore.service.IOrderService;
import com.fan.estore.service.LineServiceImpl;
import com.fan.estore.service.OrderServiceImpl;

@WebServlet("/delOrderServlet")
public class DelOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    IOrderService orderService = new OrderServiceImpl();
    ILineService lineService = new LineServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Customer customer = (Customer) session.getAttribute("customer");
		String orderid = request.getParameter("orderid");
		long oid = Long.parseLong(orderid);
		try {
			//删除订单之前，首先删除订单项
			lineService.deleteLineByOId(oid);
			//删除订单
			orderService.deleteOrder(oid);
			System.out.println("删除order成功");
			//重新查询order
			List<Order> orderByCusId = orderService.findAllOrderByCusId(customer.getId());
			session.setAttribute("orderByCusId", orderByCusId);
			//request.getRequestDispatcher("/user/order.jsp").forward(request, response);
			response.sendRedirect(request.getContextPath()+"/user/order.jsp");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		} 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
