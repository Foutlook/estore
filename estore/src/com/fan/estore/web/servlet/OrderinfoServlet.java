package com.fan.estore.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.fan.estore.bean.Line;
import com.fan.estore.bean.Order;
import com.fan.estore.service.ILineService;
import com.fan.estore.service.IOrderService;

@WebServlet("/orderinfoServlet")
public class OrderinfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/*private ILineService lineService;
	private IOrderService orderService;*/
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获得容器，从监听器中获得，获得spring容器，从application域中获得即可
		// 1获得servletContext对象
		ServletContext sc = request.getServletContext();
		// 2.从sc中获得ac容器
		WebApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(sc);
		// 3.从容器中获得bookService，customerService
		ILineService lineService =  (ILineService) ac.getBean("lineService");
		IOrderService orderService =  (IOrderService) ac.getBean("orderService");
		//-----------------------------------------------
		
		HttpSession session = request.getSession();
		String orderid = request.getParameter("orderid");
		try {
			//通过orderid来查询订单项
			List<Line> linesByOId = lineService.findLineByOrderId(Long.parseLong(orderid));
			//查询orderid的order信息
			Order order = orderService.findById(Long.parseLong(orderid));
			session.setAttribute("linesByOId",linesByOId);
			session.setAttribute("order", order);
			//重定向
			response.sendRedirect(request.getContextPath()+"/user/orderinfo.jsp");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		} 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
