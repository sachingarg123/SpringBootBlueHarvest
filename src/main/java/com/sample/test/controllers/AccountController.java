package com.sample.test.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sample.test.constants.AppConstants;
import com.sample.test.dto.AccountDetailResponse;
import com.sample.test.dto.Accounts;
import com.sample.test.dto.CustomError;
import com.sample.test.service.AppService;

@RestController
@RequestMapping("/api")
public class AccountController {

	public static final Logger logger = LoggerFactory.getLogger(AccountController.class);

	@Autowired
	AppService appService;

	// -------------------Create an account-------------------------------------------

	@RequestMapping(value = "/account/customer/{customerId}", method = RequestMethod.POST)
	public ResponseEntity<?> createAccount(@PathVariable("customerId") long customerId,
			@RequestParam("intialCredit") long intialCredit) {
		logger.info("Creating account for Customer : {}", customerId);

		Accounts accounts = appService.createAccount(customerId,intialCredit);
		if (null != accounts) {
			AccountDetailResponse response = new AccountDetailResponse();
			response.setAccountId(accounts.getAccountId());
			response.setCustomerId(customerId);
			return new ResponseEntity<AccountDetailResponse>(response, HttpStatus.OK);
		} else {
			CustomError customError = new CustomError();
			customError.setErrorCode(AppConstants.ACCOUNT_ERROR_CODE);
			customError.setErrorMessage(AppConstants.ACCOUNT_ERROR_MSG);
			return new ResponseEntity<CustomError>(customError, HttpStatus.BAD_REQUEST);
		}

	}

}
