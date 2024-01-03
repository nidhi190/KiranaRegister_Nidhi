package com.kirana.register.error;

import com.kirana.register.exception.CustomBadRequestException;
import com.kirana.register.exception.CustomForbiddenException;
import com.kirana.register.exception.CustomInternalServerException;
import com.kirana.register.payload.CustomApiResponse;
import com.kirana.register.payload.ResponseGenerator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author Nidhi Rani
 * @created 02/01/24 2:52 am
 */

@ControllerAdvice
public class CustomControllerAdvice {

    @ExceptionHandler(CustomBadRequestException.class)
    public ResponseEntity<CustomApiResponse<Object>> handleCustomBadRequestExceptions(final Exception e) {
        return new ResponseGenerator<>(HttpStatus.BAD_REQUEST, e.getMessage()).getResponse();
    }

    @ExceptionHandler(CustomInternalServerException.class)
    public ResponseEntity<CustomApiResponse<Object>> handleCustomInternalServerExceptions(final Exception e) {
        return new ResponseGenerator<>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()).getResponse();
    }

    @ExceptionHandler(CustomForbiddenException.class)
    public ResponseEntity<CustomApiResponse<Object>> handleCustomForbiddenExceptions(final Exception e) {
        return new ResponseGenerator<>(HttpStatus.FORBIDDEN, e.getMessage()).getResponse();
    }
}
