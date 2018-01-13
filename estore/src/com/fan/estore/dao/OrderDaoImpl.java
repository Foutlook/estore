package com.fan.estore.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.fan.estore.bean.Order;
import com.fan.estore.common.MyBatisSqlSessionFactory;

public class OrderDaoImpl implements IOrderDao {

	@Override
	public void saveOrder(Order order) {
		SqlSession session = MyBatisSqlSessionFactory.getSqlSession();
		try {
			IOrderDao mapper = session.getMapper(IOrderDao.class);
			mapper.saveOrder(order);
			session.commit();
		} catch (Exception e) {
			session.rollback();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	@Override
	public Order findOrderById(Long id) {
		SqlSession session = MyBatisSqlSessionFactory.getSqlSession();
		Order order = null;
		try {
			IOrderDao mapper = session.getMapper(IOrderDao.class);
			order = mapper.findOrderById(id);
			session.commit();
		} catch (Exception e) {
			session.rollback();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return order;
	}

	@Override
	public void deleteOrder(Long id) {
		SqlSession session = MyBatisSqlSessionFactory.getSqlSession();
		try {
			IOrderDao mapper = session.getMapper(IOrderDao.class);
			mapper.deleteOrder(id);
			session.commit();
		} catch (Exception e) {
			session.rollback();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
	
	//查询所有的顾客订单
	@Override
	public List<Order> findAllOrder() {
		SqlSession session = MyBatisSqlSessionFactory.getSqlSession();
		List<Order> allOrder = null;
		try {
			IOrderDao mapper = session.getMapper(IOrderDao.class);
			allOrder = mapper.findAllOrder();
			session.commit();
		} catch (Exception e) {
			session.rollback();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return allOrder;
	}

	@Override
	public List<Order> findAllOrderByCusId(Long id) {
		SqlSession session = MyBatisSqlSessionFactory.getSqlSession();
		List<Order> orders = null;
		try {
			IOrderDao mapper = session.getMapper(IOrderDao.class);
			orders = mapper.findAllOrderByCusId(id);
			session.commit();
		} catch (Exception e) {
			session.rollback();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return orders;
	}

}
