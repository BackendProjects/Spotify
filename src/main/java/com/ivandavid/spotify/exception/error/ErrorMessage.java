package com.ivandavid.spotify.exception.error;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class ErrorMessage {
    private final String message;
    private final HttpStatus httpStatusName;
    private final Integer httpStatusCode;
    private final LocalDateTime timestamp;

    public ErrorMessage(String message) {
        this.message = message;
        this.httpStatusName = HttpStatus.NOT_FOUND;
        this.httpStatusCode = 404;
        this.timestamp = LocalDateTime.now();
    }
}
