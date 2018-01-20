package com.fan.estore.demo;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.fan.estore.bean.Line;
import com.fan.estore.bean.Order;
import com.fan.estore.common.MyBatisSqlSessionFactory;
import com.fan.estore.dao.ILineDao;
import com.fan.estore.dao.IOrderDao;
import com.fan.estore.mapper.LineMapper;


//测试数据库的类
public class Testfindlines {
	@Test
	public void fun1() {
		// 通过order的id查询订单项
		SqlSession session = MyBatisSqlSessionFactory.getSqlSession();
		List<Line> lines = null;
		try {
			ILineDao mapper = session.getMapper(ILineDao.class);
			lines = mapper.findLineByOId(15L);
			System.out.println(lines + "-----------");
			session.commit();
		} catch (Exception e) {
			session.rollback();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	@Test
	public void fun2() {
		// 通过order的id查询订单项
		SqlSession session = MyBatisSqlSessionFactory.getSqlSession();
		try {
			IOrderDao mapper = session.getMapper(IOrderDao.class);
			Order order = mapper.findOrderById(21L);
			System.out.println(order + "-----------");
			session.commit();
		} catch (Exception e) {
			session.rollback();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	@Test
	public void fun3() {
		// 通过order的id删除订单项
		SqlSession session = MyBatisSqlSessionFactory.getSqlSession();
		try {
			ILineDao mapper = session.getMapper(ILineDao.class);
			mapper.deleteLineByOId(15L);
			session.commit();
		} catch (Exception e) {
			session.rollback();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
	
	@Test
	public void fun4() {
		// 通过order的id删除订单项
		SqlSession session = MyBatisSqlSessionFactory.getSqlSession();
		try {
			ILineDao mapper = session.getMapper(ILineDao.class);
			List<Order> orderWithBook = mapper.findOrderWithBookByOId(28L);
			for (Order order : orderWithBook) {
				System.out.println(order);
			}
			session.commit();
		} catch (Exception e) {
			session.rollback();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
	
	@Test
	public void fun5() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		List<Line> lines = null;
		LineMapper mapper = ac.getBean(LineMapper.class);
		lines = mapper.findLineByOId(34L);
		for (Line line : lines) {
			System.out.println(line.getId());
		}
	}
}
