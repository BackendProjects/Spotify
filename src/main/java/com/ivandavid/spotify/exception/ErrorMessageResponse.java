package com.ivandavid.spotify.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
public class ErrorMessageResponse {
    private final LocalDateTime timestamp;
    private final Integer statusCode;
    private final String statusName;
    private final String message;

    public ErrorMessageResponse(LocalDateTime timestamp, Integer statusCode, String statusName, String message) {
        this.timestamp = timestamp;
        this.statusCode = statusCode;
        this.statusName = statusName;
        this.message = message;
    }
}
