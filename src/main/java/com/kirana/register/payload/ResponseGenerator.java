package com.kirana.register.payload;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * @author Nidhi Rani
 * @created 02/01/24 3:10 am
 */

@Getter
public class ResponseGenerator<T> {

    ResponseEntity<CustomApiResponse<T>> response;

    public ResponseGenerator(final HttpStatus httpStatus, final T data) {
        CustomApiResponse<T> customApiResponse = new CustomApiResponse<>(data);
        this.response = new ResponseEntity<>(customApiResponse, httpStatus);
    }

    public ResponseGenerator(final HttpStatus httpStatus, final String message) {
        CustomApiResponse<T> customApiResponse = new CustomApiResponse<>(message);
        this.response = new ResponseEntity<>(customApiResponse, httpStatus);
    }

    public ResponseGenerator(final HttpStatus httpStatus, final T data, final String message) {
        CustomApiResponse<T> customApiResponse = new CustomApiResponse<>(message, data);
        this.response = new ResponseEntity<>(customApiResponse, httpStatus);
    }
}
