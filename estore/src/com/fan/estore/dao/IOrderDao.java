package com.fan.estore.dao;

import java.util.List;

import com.fan.estore.bean.Order;

public interface IOrderDao  {
	public void saveOrder(Order order);
	public Order findOrderById(Long id);
	public void deleteOrder(Long id);
	List<Order> findAllOrder();
	List<Order> findAllOrderByCusId(Long id);
}
