package com.example.demo.exception;

public class UserEmailNotFoundException extends RuntimeException{

	/**
	 * Documentation
	 */
	private static final long serialVersionUID = 1L;
	public UserEmailNotFoundException() {
		super();
	}
	
	public UserEmailNotFoundException(String message) {
		super(message);
	}
}
