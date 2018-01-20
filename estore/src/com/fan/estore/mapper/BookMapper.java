package com.fan.estore.mapper;

import java.util.List;

import com.fan.estore.bean.Book;

public interface BookMapper {
	public List<Book> queryAll();
	public Book queryById(Long id);
}
