package com.fan.estore.service;

import java.util.List;

import com.fan.estore.bean.Book;
import com.fan.estore.myexception.BookException;


public interface IBookService {
	
	List<Book> listAllBooks() throws BookException ;
	Book findById(Long id) throws BookException;
}
