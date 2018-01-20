package com.fan.estore.daomapper;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.fan.estore.bean.Order;
import com.fan.estore.dao.IOrderDao;
import com.fan.estore.mapper.OrderMapper;

public class OrderDaoImpl implements IOrderDao {

	@Autowired
	OrderMapper mapper;

	@Override
	public void saveOrder(Order order) {
		mapper.saveOrder(order);
	}

	@Override
	public Order findOrderById(Long id) {
		Order order = null;
		order = mapper.findOrderById(id);
		return order;
	}

	@Override
	public void deleteOrder(Long id) {
		mapper.deleteOrder(id);
	}

	// 查询所有的顾客订单
	@Override
	public List<Order> findAllOrder() {
		List<Order> allOrder = null;
		allOrder = mapper.findAllOrder();
		return allOrder;
	}

	@Override
	public List<Order> findAllOrderByCusId(Long id) {
		List<Order> orders = null;
		orders = mapper.findAllOrderByCusId(id);
		return orders;
	}

}
