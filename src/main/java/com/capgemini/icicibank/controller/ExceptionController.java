package com.capgemini.icicibank.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.capgemini.icicibank.exceptions.CustomerNotFoundException;
import com.capgemini.icicibank.exceptions.InvalidDataException;
import com.capgemini.icicibank.exceptions.LowBalanceException;

@ControllerAdvice
public class ExceptionController {
	
	
	@ExceptionHandler(value= CustomerNotFoundException.class)
	public String handleException(HttpServletRequest request, CustomerNotFoundException exception ) {

		request.setAttribute("success", exception.toString());
		return "success";
		
	}
	/*@ExceptionHandler(value=InvalidDataException.class)
	public String handleException(HttpServletRequest request,InvalidDataException exception )
	{
		request.setAttribute("success", exception.toString());
		return "success";
	}*/
	@ExceptionHandler(value=LowBalanceException.class)
	public String handleException(HttpServletRequest request,LowBalanceException exception )
	{
		request.setAttribute("success", exception.toString());
		return "success";
	}
}
