package com.sample.test.service;

import com.sample.test.dto.Accounts;
import com.sample.test.dto.Customer;
import com.sample.test.dto.CustomerRequest;

public interface AppService {
	
	public Customer createUser(CustomerRequest request);
	public Accounts createAccount(long customerId,long intialCredit);
	public Customer getCustomerDetails(long customerId);
	

}
