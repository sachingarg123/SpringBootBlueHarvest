package com.sample.test.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sample.test.constants.AppConstants;
import com.sample.test.dto.CustomError;
import com.sample.test.dto.Customer;
import com.sample.test.dto.CustomerDetailsResponse;
import com.sample.test.dto.CustomerRequest;
import com.sample.test.service.AppService;

@RestController
@RequestMapping("/api")
public class CustomerController {

	public static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

	@Autowired
	AppService appService;

	// -------------------Create a Customer-------------------------------------------

	@RequestMapping(value = "/customer", method = RequestMethod.POST)
	public ResponseEntity<?> createUser(@RequestBody CustomerRequest customer) {
		logger.info("Creating Customer : {}", customer);

		Customer newCustomer = appService.createUser(customer);
		if (null != newCustomer) {
			CustomerDetailsResponse customerResponse = new CustomerDetailsResponse();
			customerResponse.setCustomerId(newCustomer.getCustomerId());
			return new ResponseEntity<CustomerDetailsResponse>(customerResponse, HttpStatus.OK);
		} else {
			CustomError customError = new CustomError();
			customError.setErrorCode(AppConstants.CUSTOMER_ERROR_CODE);
			customError.setErrorMessage(AppConstants.CUSTOMER_ERROR_MSG);
			return new ResponseEntity<CustomError>(customError, HttpStatus.BAD_REQUEST);
		}

	}
	
	// -------------------Create a Customer-------------------------------------------

		@RequestMapping(value = "/customer/{customerId}", method = RequestMethod.GET)
		public ResponseEntity<?> getUserDetails(@PathVariable("customerId") long customerId) {
			logger.info("get Customer details for customer ID : {}", customerId);

			Customer newCustomer = appService.getCustomerDetails(customerId);
			if (null != newCustomer) {
				return new ResponseEntity<Customer>(newCustomer, HttpStatus.OK);
			} else {
				CustomError customError = new CustomError();
				customError.setErrorCode(AppConstants.CUST_NOT_EXIST_CODE);
				customError.setErrorMessage(AppConstants.CUST_NOT_EXIST_MSG);
				return new ResponseEntity<CustomError>(customError, HttpStatus.BAD_REQUEST);
			}

		}
}
