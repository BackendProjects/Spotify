package com.ivandavid.spotify.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
public class BadRequestException extends RuntimeException {

    private final HttpStatus status = HttpStatus.BAD_REQUEST;
    private ErrorMessageResponse errorMessageResponse;

    public BadRequestException(String message) {
        super();
        setErrorMessageResponse(message);
    }

    private void setErrorMessageResponse(String message) {
        errorMessageResponse = new ErrorMessageResponse(
                status.name(),
                status.value(),
                message,
                LocalDateTime.now()
        );
    }
}
