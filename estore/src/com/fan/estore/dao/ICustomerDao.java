package com.fan.estore.dao;

import com.fan.estore.bean.Customer;

public interface ICustomerDao  {
	public Customer findByName(String name);
	public void saveCustomer(Customer customer);
	public void updateCustomer(Customer customer);
}
