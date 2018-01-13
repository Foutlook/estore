package com.fan.estore.bean;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
// 顾客类
public class Customer implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private String password;
	//邮编
	private String zip;
	private String address;
	private String telephone;
	private String email;
	
	//一对多：一个用户可以有多个订单
	private Set<Order> orders = new HashSet<>();
	
	public Customer(String name, String password, String zip, String address, String telephone, String email) {
		super();
		this.name = name;
		this.password = password;
		this.zip = zip;
		this.address = address;
		this.telephone = telephone;
		this.email = email;
	}

	public Customer(){
		
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Set<Order> getOrders() {
		return orders;
	}
	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}
}
