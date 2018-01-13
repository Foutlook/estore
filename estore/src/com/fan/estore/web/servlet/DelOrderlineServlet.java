package com.fan.estore.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fan.estore.bean.ShoppingCar;

@WebServlet("/delOrderlineServlet")
public class DelOrderlineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bookid = request.getParameter("bookid");
		System.out.println(bookid+"-=-=-=-=-=-=-=");
		HttpSession session = request.getSession();
		ShoppingCar sc = (ShoppingCar) session.getAttribute("shoppingCar");
		sc.delete(Long.parseLong(bookid));
		//跳转到shopcat.jsp
		request.getRequestDispatcher("/shopcart.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
