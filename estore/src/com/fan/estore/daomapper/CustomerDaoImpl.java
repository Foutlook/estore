package com.fan.estore.daomapper;

import org.springframework.beans.factory.annotation.Autowired;
import com.fan.estore.bean.Customer;
import com.fan.estore.dao.ICustomerDao;
import com.fan.estore.mapper.CustomerMapper;

public class CustomerDaoImpl implements ICustomerDao {

	@Autowired
	CustomerMapper mapper;

	@Override
	public Customer findByName(String name) {
		Customer customer = null;
		customer = mapper.findByName(name);
		return customer;
	}

	@Override
	public void saveCustomer(Customer customer) {
		mapper.saveCustomer(customer);
	}

	@Override
	public void updateCustomer(Customer customer) {
		mapper.updateCustomer(customer);
	}

}
