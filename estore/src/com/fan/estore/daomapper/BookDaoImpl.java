package com.fan.estore.daomapper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fan.estore.bean.Book;
import com.fan.estore.dao.IBookDao;
import com.fan.estore.mapper.BookMapper;

@Repository("bookDao")
public class BookDaoImpl implements IBookDao {
	@Autowired   //自动转配，直接操作属性
	private BookMapper mapper;

	@Override
	public List<Book> queryAll() {
		List<Book> allBooks = null;
		allBooks = mapper.queryAll();
		return allBooks;
	}

	@Override
	public Book queryById(Long id) {
		Book book = null;
		book = mapper.queryById(id);
		return book;
	}

}
