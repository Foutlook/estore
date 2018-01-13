package com.fan.estore.dao;

import org.apache.ibatis.session.SqlSession;
import com.fan.estore.bean.Customer;
import com.fan.estore.common.MyBatisSqlSessionFactory;

public class CustomerDaoImpl implements ICustomerDao {

	@Override
	public Customer findByName(String name) {
		SqlSession session = MyBatisSqlSessionFactory.getSqlSession();
		Customer customer = null;
		try {
			ICustomerDao mapper = session.getMapper(ICustomerDao.class);
			customer = mapper.findByName(name);
			session.commit();
		} catch (Exception e) {
			session.rollback();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return customer;
	}

	@Override
	public void saveCustomer(Customer customer) {
		SqlSession session = MyBatisSqlSessionFactory.getSqlSession();
		try {
			ICustomerDao mapper = session.getMapper(ICustomerDao.class);
			mapper.saveCustomer(customer);
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
	public void updateCustomer(Customer customer) {
		SqlSession session = MyBatisSqlSessionFactory.getSqlSession();
		try {
			ICustomerDao mapper = session.getMapper(ICustomerDao.class);
			mapper.updateCustomer(customer);
			session.commit();
		} catch (Exception e) {
			session.rollback();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

}
