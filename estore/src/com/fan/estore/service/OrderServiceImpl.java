package com.fan.estore.service;

import java.util.Collection;
import java.util.List;

import com.fan.estore.bean.Customer;
import com.fan.estore.bean.Line;
import com.fan.estore.bean.Order;
import com.fan.estore.dao.ILineDao;
import com.fan.estore.dao.IOrderDao;
import com.fan.estore.dao.LineDaoImpl;
import com.fan.estore.dao.OrderDaoImpl;
import com.fan.estore.myexception.OrderException;

public class OrderServiceImpl implements IOrderService {
	private IOrderDao orderDao;
	private ILineDao lineDao;

	@Override
	public void confirmOrder(Customer customer, Order order, Collection<Line> lines) throws OrderException {
		order.setCustomer(customer);
		try {
			// 对保存订单
			orderDao.saveOrder(order);
		} catch (Exception e) {
			throw new OrderException("保存订单错误");
		}
		try {
			// 遍历出集合中line的信息
			for (Line line : lines) {
				line.setOrder(order);
				// 保存订单项
				lineDao.saveLine(line);
			}
		} catch (Exception e) {
			throw new OrderException("保存订单项错误");
		}
	}

	@Override
	public void deleteOrder(Long id) throws OrderException {
		try {
			orderDao.deleteOrder(id);
		} catch (Exception e) {
			throw new OrderException("删除order出错");
		}
	}

	@Override
	public Order findById(Long id) throws OrderException {
		Order order = null;
		try {
			order = orderDao.findOrderById(id);
		} catch (Exception e) {
			throw new OrderException("通过id查询Order出错");
		}
		return order;
	}

	@Override
	public List<Order> findAllOrder() throws OrderException {
		List<Order> allOrder = null;
		try {
			// 查询所有的订单
			allOrder = orderDao.findAllOrder();
		} catch (Exception e) {
			throw new OrderException("查询订单出错");
		}
		return allOrder;
	}

	// 通过customer的id查询
	@Override
	public List<Order> findAllOrderByCusId(Long id) throws OrderException {
		List<Order> orders = null;
		try {
			orders = orderDao.findAllOrderByCusId(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new OrderException("通过顾客id查询订单出错");
		}

		return orders;
	}

	public void setOrderDao(IOrderDao orderDao) {
		this.orderDao = orderDao;
	}

	public void setLineDao(ILineDao lineDao) {
		this.lineDao = lineDao;
	}
	
}
