package com.fan.estore.web.servlet;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fan.estore.bean.Book;
import com.fan.estore.bean.Line;
import com.fan.estore.bean.ShoppingCar;
import com.fan.estore.service.BookServiceImpl;
import com.fan.estore.service.IBookService;

@WebServlet("/editOrderlineServlet")
public class EditOrderlineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    IBookService bookService = new BookServiceImpl();
	
	//更新购物清单商品的数量
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		ShoppingCar sc = (ShoppingCar) session.getAttribute("shoppingCar");
		System.out.println(sc+"=-==================");
		String bookid = request.getParameter("productid");
		String newnum = request.getParameter("num");
		Map<Long, Line> lines = sc.getLines();
		Set<Entry<Long,Line>> entrySet = lines.entrySet();
		Line line =null;
		for (Entry<Long, Line> entry : entrySet) {
			if(entry.getKey().equals(Long.parseLong(bookid))){
				line = entry.getValue();
			}
		}
		//删除，在添加
		sc.delete(Long.parseLong(bookid));
		//查询book信息
		try {
			Book book = bookService.findById(Long.parseLong(bookid));
			line.setBook(book);
		} catch (Exception e) {
			e.printStackTrace();
		}
		for(int i=0;i<Long.parseLong(newnum);i++){
			sc.add(line);
		}
		System.out.println("商品数量修改成功");
		session.setAttribute("shoppingCar", sc);
		request.getRequestDispatcher("/shopcart.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
