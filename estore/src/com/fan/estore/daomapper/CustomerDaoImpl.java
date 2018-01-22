package com.fan.estore.daomapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fan.estore.bean.Customer;
import com.fan.estore.dao.ICustomerDao;
import com.fan.estore.mapper.CustomerMapper;

@Repository("customerDao")
public class CustomerDaoImpl implements ICustomerDao {

	@Autowired
	private CustomerMapper mapper;

	@Override
	public Customer findByName(String name) {
		Customer customer = null;
		customer = mapper.findByName(name);
		return customer;
	}

	@Override
	public void saveCustomer(Customer customer) {
		mapper.saveCustomer(customer);
		/*System.out.println("注册用户...抛出异常");
		int i = 1/0;*/
	}

	@Override
	public void updateCustomer(Customer customer) {
		mapper.updateCustomer(customer);
	}

}
