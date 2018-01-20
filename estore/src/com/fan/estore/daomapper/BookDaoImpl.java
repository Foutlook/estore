package com.fan.estore.daomapper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import com.fan.estore.bean.Book;
import com.fan.estore.dao.IBookDao;
import com.fan.estore.mapper.BookMapper;

public class BookDaoImpl implements IBookDao {
	@Autowired
	BookMapper mapper;

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
