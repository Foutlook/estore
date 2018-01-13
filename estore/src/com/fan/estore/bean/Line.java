package com.fan.estore.bean;

import java.io.Serializable;

/**
 * 订单项   一个订单对应多类书，一类书可以有多个订单，订单项相当于订单和书的桥表。
 * */
public class Line implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Integer num;
	/**
	 * 关联关系 -- 多对一  对应一个订单---书类型固定，可以对应多个可以对应多个订单，所以Line对应Order是多对一
	 * */
	private Order order;
	/**
	 * 关联关系 -- 多对一  对应一类书-----订单固定，可以对应多类书，所以Line对应BOOK是多对一
	 * */
	private Book book;
	
	public Line(){
		
	}
	public Line(Long id, Integer num) {
		this.id = id;
		this.num = num;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
}
