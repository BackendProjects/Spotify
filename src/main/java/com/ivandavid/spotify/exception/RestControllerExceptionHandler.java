package com.ivandavid.spotify.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class RestControllerExceptionHandler {

    @ResponseBody
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorMessageResponse> resolveException(ResourceNotFoundException ex) {
        return new ResponseEntity<>(ex.getErrorMessageResponse(), HttpStatus.NOT_FOUND);
    }

    @ResponseBody
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorMessageResponse> resolveException(BadRequestException ex) {
        return new ResponseEntity<>(ex.getErrorMessageResponse(), HttpStatus.BAD_REQUEST);
    }
}