package com.ivandavid.spotify.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class RestControllerExceptionHandler {

    /*@ResponseBody
    @ExceptionHandler({ResourceNotFoundException.class
            //GenreNotFoundException.class, TrackNotFoundException.class, TrackListNotFoundException.class
    })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage resolveException(Exception ex) {
        return new ErrorMessage(ex.getMessage());
    }*/

    @ResponseBody
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorMessageResponse> resolveException(ResourceNotFoundException ex) {
        ex.setApiResponse();
        ErrorMessageResponse apiResponse = ex.getApiResponse();
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }
}