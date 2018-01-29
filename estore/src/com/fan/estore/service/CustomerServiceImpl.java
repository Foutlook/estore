package com.fan.estore.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fan.estore.bean.Customer;
import com.fan.estore.dao.ICustomerDao;
import com.fan.estore.daomapper.CustomerDaoImpl;
import com.fan.estore.myexception.CustomerException;

@Service("customerService")
//@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
public class CustomerServiceImpl implements ICustomerService {
	@Resource(name="customerDao")
	private ICustomerDao cusdao;
	
	@Override
	public void saveRegister(Customer customer) throws CustomerException {
		// 注册
		try {
			//判断是否已经注册
			Customer findcus = cusdao.findByName(customer.getName());
			if(findcus!=null){
				throw new CustomerException("用户名已经注册!");
			}
			cusdao.saveCustomer(customer);
			//int i = 1/0;
		} catch (Exception e) {
			throw new CustomerException("注册失败");
		}
	}

	//@Transactional(readOnly=true)
	@Override
	public Customer getLogin(String name, String password) throws CustomerException {
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
	
	//依赖注入时要用，通过set方式注入,在属性上使用注解，就可以不再使用set方法
	/*public void setCusdao(ICustomerDao cusdao) {
		this.cusdao = cusdao;
	}*/
}
