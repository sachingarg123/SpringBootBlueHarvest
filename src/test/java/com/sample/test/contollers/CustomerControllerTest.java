package com.sample.test.contollers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CustomerControllerTest {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext wac;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();

	}

	@Test
	public void verifyCreateCustomer() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/api/customer").contentType(MediaType.APPLICATION_JSON)
				.content("{\"name\" : \"Sachin\", \"surName\" : \"Garg\",\"email\" : \"sachin@gmail.com\" }")
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andDo(print());
	}
	
	@Test
	public void verifyCustomerDetails() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/customer/123")
				.accept(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$.errorCode").value("CUST_10002")).andExpect(status().isBadRequest()).andDo(print());
	}
	
	
	@Test
	public void verifyAccountDetails() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/api/account/customer/123?intialCredit=100")
				.accept(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$.errorCode").value("ACCT_20001")).andExpect(status().isBadRequest()).andDo(print());
	}

}
