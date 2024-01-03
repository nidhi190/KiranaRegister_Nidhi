package com.kirana.register.exception;

/**
 * @author Nidhi Rani
 * @created 02/01/24 2:54 am
 */

public class CustomBadRequestException extends RuntimeException {

    public CustomBadRequestException() {
        super();
    }

    public CustomBadRequestException(final String message) {
        super(message);
    }

    public CustomBadRequestException(final String message, Throwable cause) {
        super(message, cause);
    }
}
