package com.fan.estore.dao;

import java.util.List;

import com.fan.estore.bean.Book;

public interface IBookDao  {
	public List<Book> queryAll();
	public Book queryById(Long id);
}
