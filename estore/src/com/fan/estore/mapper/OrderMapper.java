package com.fan.estore.mapper;

import java.util.List;

import com.fan.estore.bean.Order;

public interface OrderMapper {
	void saveOrder(Order order);
	Order findOrderById(Long id);
	void deleteOrder(Long id);
	List<Order> findAllOrder();
	List<Order> findAllOrderByCusId(Long id);
}
