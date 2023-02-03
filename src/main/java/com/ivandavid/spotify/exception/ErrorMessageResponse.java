package com.ivandavid.spotify.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
public class ErrorMessageResponse {

    private final HttpStatus statusName;
    private final Integer statusCode;
    private final String message;
    private final LocalDateTime timestamp;

    public ErrorMessageResponse(String statusName, Integer statusCode, String message, LocalDateTime timestamp) {
        this.statusName = HttpStatus.valueOf(statusName);
        this.statusCode = statusCode;
        this.message = message;
        this.timestamp = timestamp;
    }

}
