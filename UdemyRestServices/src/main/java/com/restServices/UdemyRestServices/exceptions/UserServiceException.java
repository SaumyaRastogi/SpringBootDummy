package com.restServices.UdemyRestServices.exceptions;

public class UserServiceException extends RuntimeException {
  
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3513497108511834642L;

	public UserServiceException(String message)
	{
		super(message);
	}
}
