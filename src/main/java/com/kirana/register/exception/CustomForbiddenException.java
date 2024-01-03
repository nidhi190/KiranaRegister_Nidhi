package com.kirana.register.exception;

/**
 * @author Nidhi Rani
 * @created 02/01/24 3:04 am
 */

public class CustomForbiddenException extends RuntimeException{

	public CustomForbiddenException() {
		super();
	}

	public CustomForbiddenException(final String message) {
		super(message);
	}
	
	public CustomForbiddenException(final String message, Throwable cause) {
		super(message, cause);
	}
}
