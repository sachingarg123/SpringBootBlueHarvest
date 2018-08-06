package com.sample.test.dto;

import java.util.List;

public class Customer {
	private String name;
	private String surName;
	private String email;
	private long customerId;
	private List<Accounts> accounts;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurName() {
		return surName;
	}
	public void setSurName(String surName) {
		this.surName = surName;
	}
	public long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}
	public List<Accounts> getAccounts() {
		return accounts;
	}
	public void setAccounts(List<Accounts> accounts) {
		this.accounts = accounts;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	

}
