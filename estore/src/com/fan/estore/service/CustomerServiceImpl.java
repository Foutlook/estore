package com.fan.estore.service;

import com.fan.estore.bean.Customer;
import com.fan.estore.dao.CustomerDaoImpl;
import com.fan.estore.dao.ICustomerDao;
import com.fan.estore.myexception.CustomerException;

public class CustomerServiceImpl implements ICustomerService {
	ICustomerDao cusdao = new CustomerDaoImpl();

	@Override
	public void register(Customer customer) throws CustomerException {
		// 注册
		try {
			//判断是否已经注册
			Customer findcus = cusdao.findByName(customer.getName());
			if(findcus!=null){
				throw new CustomerException("用户名已经注册!");
			}
			cusdao.saveCustomer(customer);
		} catch (Exception e) {
			throw new CustomerException("注册失败");
		}
	}

	@Override
	public Customer login(String name, String password) throws CustomerException {
		Customer findcustomer = cusdao.findByName(name);
		if(findcustomer==null){
			throw new CustomerException("用户不存在!");
		}
		if(findcustomer!=null && !password.equals(findcustomer.getPassword())){
			throw new CustomerException("用户名或密码错误!");
		}
		
		return findcustomer;
	}

	@Override
	public void updateCustomer(Customer customer) throws CustomerException {
		try {
			cusdao.updateCustomer(customer);
		} catch (Exception e) {
			throw new CustomerException("更新出错");
		}
	}

}
