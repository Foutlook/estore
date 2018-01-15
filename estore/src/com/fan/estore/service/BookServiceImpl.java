package com.fan.estore.service;

import java.util.List;

import com.fan.estore.bean.Book;
import com.fan.estore.dao.BookDaoImpl;
import com.fan.estore.dao.IBookDao;
import com.fan.estore.myexception.BookException;

public class BookServiceImpl implements IBookService {
	private IBookDao bookdao;

	@Override
	public List<Book> listAllBooks() throws BookException {
		List<Book> allbooks = null;
		try {
			allbooks = bookdao.queryAll();
			if(allbooks==null){
				throw new BookException("未查询到图书信息");
			}
		} catch (Exception e) {
			throw new BookException("查询图书错误");
		}
		return allbooks;
	}

	@Override
	public Book findById(Long id) throws BookException {
		Book book = null;
		try {
			book = bookdao.queryById(id);
			if(book==null){
				throw new BookException("未查到书籍信息");
			}
		} catch (Exception e) {
			throw new BookException("查询书籍出错");
		}
		return book;
	}
	//set方法，注入时使用
	public void setBookdao(IBookDao bookdao) {
		this.bookdao = bookdao;
	}
}
