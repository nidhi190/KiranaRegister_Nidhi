package com.kirana.register.exception;
/**
 * @author Nidhi Rani
 * @created 02/01/24 2:59 am
 */

public class CustomInternalServerException extends RuntimeException {

	public CustomInternalServerException() {
		super();
	}

	public CustomInternalServerException(final String message) {
		super(message);
	}

	public CustomInternalServerException(final String message, Throwable cause) {
		super(message, cause);
	}
}
