package com.kirana.register.payload;

import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * @author Nidhi Rani
 * @created 02/01/24 2:59 am
 */

@Data
public class CustomApiResponse<T> {

    private final LocalDateTime timestamp;

    private T data;

    private String message;

    public CustomApiResponse() {
        timestamp = LocalDateTime.now();
    }


    public CustomApiResponse(final String message) {
        this();
        this.message = message;
    }

    public CustomApiResponse(final T data) {
        this();
        this.data = data;
    }

    public CustomApiResponse(final String message, final T data) {
        this();
        this.message = message;
        this.data = data;
    }
}
