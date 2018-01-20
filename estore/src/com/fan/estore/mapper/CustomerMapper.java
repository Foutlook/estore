package com.fan.estore.mapper;

import com.fan.estore.bean.Customer;

public interface CustomerMapper {
	public Customer findByName(String name);
	public void saveCustomer(Customer customer);
	public void updateCustomer(Customer customer);
}
