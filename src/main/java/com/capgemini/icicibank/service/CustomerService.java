package com.capgemini.icicibank.service;

import org.springframework.dao.DataAccessException;

import com.capgemini.icicibank.entities.Customer;
import com.capgemini.icicibank.exceptions.CustomerNotFoundException;
import com.capgemini.icicibank.exceptions.InvalidDataException;


public interface CustomerService {
	public Customer authenticate(Customer customer) throws CustomerNotFoundException;
	public Customer updateProfile(Customer customer) throws InvalidDataException;
	public boolean updatePassword(Customer customer, String oldPassword, String newPassword);
	
}
