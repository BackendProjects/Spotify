package com.ivandavid.spotify.exception;

import com.ivandavid.spotify.enums.EntityName;
import com.ivandavid.spotify.enums.SearchParamType;
import lombok.Data;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class ResourceNotFoundException extends RuntimeException {

	private final HttpStatus status = HttpStatus.NOT_FOUND;
	private String message;
	private ErrorMessageResponse errorMessageResponse;

	public ResourceNotFoundException(EntityName entityName, SearchParamType fieldName, Object fieldValue) {
		super();
		this.message = String.format("%s not found with %s: '%s'", entityName, fieldName, fieldValue);
		setErrorMessageResponse(message);
	}

	public ResourceNotFoundException(EntityName entityName, SearchParamType fieldName) {
		super();
		this.message = String.format("%s not found with %s", entityName, fieldName);
		setErrorMessageResponse(message);
	}

	private void setErrorMessageResponse(String message) {
		errorMessageResponse = new ErrorMessageResponse(
				LocalDateTime.now(),
				this.status.value(),
				this.status.name(),
				message
		);
	}
}
