package com.capgemini.icicibank.test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.capgemini.icicibank.controller.CustomerController;
import com.capgemini.icicibank.entities.BankAccount;
import com.capgemini.icicibank.entities.Customer;
import com.capgemini.icicibank.service.CustomerService;

public class CustomerControllerTest {
	
	@Mock
	private CustomerService customerService;
	
	
	@InjectMocks
	private CustomerController customercontroller;
	
	private MockMvc mockMvc;
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(customercontroller).build();
	}

	@Test
	public void testAuthenticate() throws Exception {
		Customer customer1 = new Customer(12346, null, "bindu1", null, null, null, null);
		Customer customer = new Customer(12346, "swathi", "bindu1", "swathi@gmail.com", "hyderabad", LocalDate.now(),
				new BankAccount());

		when(customerService.authenticate(customer1)).thenReturn(customer);

		mockMvc.perform(post("/login").param("custId","12346").param("password", customer.getPassword())).andExpect(view().name("index"))
				.andExpect(status().isOk());
	}
}

