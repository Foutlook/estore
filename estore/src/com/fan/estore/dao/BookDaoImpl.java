package com.fan.estore.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.fan.estore.bean.Book;
import com.fan.estore.common.MyBatisSqlSessionFactory;

public class BookDaoImpl implements IBookDao {

	@Override
	public List<Book> queryAll() {
		SqlSession session = MyBatisSqlSessionFactory.getSqlSession();
		List<Book> allBooks = null;
		try {
			IBookDao mapper = session.getMapper(IBookDao.class);
			allBooks = mapper.queryAll();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return allBooks;
	}

	@Override
	public Book queryById(Long id) {
		SqlSession session = MyBatisSqlSessionFactory.getSqlSession();
		Book book = null;
		try {
			IBookDao mapper = session.getMapper(IBookDao.class);
			book = mapper.queryById(id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return book;
	}

}
