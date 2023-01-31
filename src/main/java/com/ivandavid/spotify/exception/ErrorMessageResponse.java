package com.ivandavid.spotify.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class ErrorMessageResponse {
	private final HttpStatus error;
	private final Integer status;
	private final String message;
	private final LocalDateTime timestamp;

	public ErrorMessageResponse(String message) {
		this.error = HttpStatus.NOT_FOUND;
		this.status = 404;
		this.message = message;
		this.timestamp = LocalDateTime.now();
	}
}
