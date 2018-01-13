package com.fan.estore.service;

import com.fan.estore.bean.Customer;
import com.fan.estore.myexception.CustomerException;

public interface ICustomerService {
	void register(Customer customer) throws CustomerException;
	Customer login(String name,String password) throws CustomerException;
	void updateCustomer(Customer customer) throws CustomerException;
}
