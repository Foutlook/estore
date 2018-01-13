package com.fan.estore.service;

import java.util.Collection;
import java.util.List;

import com.fan.estore.bean.Customer;
import com.fan.estore.bean.Line;
import com.fan.estore.bean.Order;
import com.fan.estore.myexception.OrderException;


public interface IOrderService {
	void confirmOrder(Customer customer,Order order,Collection<Line> lines) throws OrderException;
	void deleteOrder(Long id) throws OrderException;
	Order findById(Long id) throws OrderException;
	List<Order> findAllOrder() throws OrderException;
	List<Order> findAllOrderByCusId(Long id) throws OrderException;
}
