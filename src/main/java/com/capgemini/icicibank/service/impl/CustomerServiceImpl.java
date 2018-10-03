package com.capgemini.icicibank.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.capgemini.icicibank.entities.Customer;
import com.capgemini.icicibank.exceptions.CustomerNotFoundException;
import com.capgemini.icicibank.exceptions.UpdationFailException;
import com.capgemini.icicibank.exceptions.ChangePasswordException;
import com.capgemini.icicibank.repository.CustomerRepository;
import com.capgemini.icicibank.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	CustomerRepository customerRepository;

	@Override
	public Customer authenticate(Customer customer) throws CustomerNotFoundException {
		try {
			return customerRepository.authenticate(customer);
		} catch (DataAccessException exception) {
			CustomerNotFoundException c = new CustomerNotFoundException("Customer is not found");
			c.initCause(exception);
			throw c;
		}
	}

	@Override
	public Customer updateProfile(Customer customer)  throws UpdationFailException{
		try {
			return customerRepository.updateProfile(customer);
		}
		catch(DataAccessException exception) {
			UpdationFailException update=new UpdationFailException("Failed to update");
			update.initCause(exception);
			throw update;
		}
	}
		
	


	@Override
	public boolean updatePassword(Customer customer, String oldPassword, String newPassword) throws ChangePasswordException
		{
		try {

			return customerRepository.updatePassword(customer, oldPassword, newPassword);
		}
		catch(DataAccessException exception) {
			ChangePasswordException change=new ChangePasswordException("password not change");
			change.initCause(exception);
			throw change;
		}
	}
	
	
}
