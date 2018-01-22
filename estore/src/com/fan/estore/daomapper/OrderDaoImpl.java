package com.fan.estore.daomapper;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fan.estore.bean.Order;
import com.fan.estore.dao.IOrderDao;
import com.fan.estore.mapper.OrderMapper;

@Repository("orderDao") //标识
public class OrderDaoImpl implements IOrderDao {

	@Autowired
	private OrderMapper mapper;

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
