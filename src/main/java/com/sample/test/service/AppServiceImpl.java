package com.sample.test.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.sample.test.constants.AppConstants;
import com.sample.test.dto.Accounts;
import com.sample.test.dto.Customer;
import com.sample.test.dto.CustomerRequest;
import com.sample.test.dto.Transactions;

@Service("appService")
public class AppServiceImpl implements AppService {

	List<Customer> customerList = new ArrayList<Customer>();
	Map<Long, Customer> customerData = new HashMap<Long, Customer>();
	boolean isDuplicate = false;

	public Customer createUser(CustomerRequest request) {
		Customer newCustomer = null;
		if (!CollectionUtils.isEmpty(customerList)) {
			for (Customer customer : customerList) {
				if (customer.getEmail().equalsIgnoreCase(request.getEmail())) {
					isDuplicate = true;
					break;
				}
			}
			if (!isDuplicate) {
				newCustomer = createNewCustomer(request);
			}
		} else {
			newCustomer = createNewCustomer(request);

		}
		return newCustomer;
	}

	public Accounts createAccount(long customerId, long intialCredit) {
		Accounts account=null;
		if (customerData.containsKey(customerId)) {
			Customer customer = customerData.get(customerId);

			if (CollectionUtils.isEmpty(customer.getAccounts())) {
				account = new Accounts();
				createAccount(customerId, intialCredit, customer, account, true);

			} else {

				 account = new Accounts();
				createAccount(customerId, intialCredit, customer, account, false);
			}
		}
		return account;

	}
	
	public Customer getCustomerDetails(long customerId) {
		if (customerData.containsKey(customerId)) {
			return customerData.get(customerId);
		}
		return null;
	}

	private void createAccount(long customerId, long intialCredit, Customer customer, Accounts account,
			boolean isPrimary) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		account.setPrimary(isPrimary);
		account.setBalance(intialCredit);
		long rand_int1 = ThreadLocalRandom.current().nextLong(0,1000000);
		account.setAccountId(rand_int1);
		Transactions transaction = new Transactions();
		transaction.setAmount(intialCredit);
		transaction.setType(AppConstants.CREDIT);
		Date currentDate  = new Date();
		transaction.setDate(formatter.format(currentDate));
		List<Transactions> transactionList = new LinkedList<Transactions>();
		transactionList.add(transaction);
		account.setTransaction(transactionList);
		List<Accounts> accounts = null;
		if (isPrimary)
			accounts = new ArrayList<Accounts>();
		else {
			accounts = customer.getAccounts();
		}
		accounts.add(account);
		customer.setAccounts(accounts);
		customerData.put(customerId, customer);
		Iterator<Customer> itr = customerList.iterator();
		while (itr.hasNext()) {
			Customer x = (Customer) itr.next();
			if (x.getCustomerId() == customer.getCustomerId())
				itr.remove();
		}
		
	}

	private Customer createNewCustomer(CustomerRequest request) {
		Customer newCustomer = new Customer();
		newCustomer.setName(request.getName());
		newCustomer.setSurName(request.getSurName());
		newCustomer.setEmail(request.getEmail());
		long rand_int1 = ThreadLocalRandom.current().nextLong(20000000,30000000);
		newCustomer.setCustomerId(rand_int1);
		customerData.put(newCustomer.getCustomerId(), newCustomer);
		customerList.add(newCustomer);
		return newCustomer;
	}



}
