package com.ivandavid.spotify.exception;

import com.ivandavid.spotify.enums.EntityName;
import com.ivandavid.spotify.enums.SearchParamType;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;
import java.util.Optional;

@Data
public class ResourceNotFoundException extends RuntimeException {
	/*private EntityName resourceName;
	private SearchParamType fieldName;
	private Object fieldValue;
	private ErrorMessageResponse apiResponse;

	public ResourceNotFoundException(EntityName entityName, SearchParamType fieldName, Object fieldValue) {
		super();
		this.resourceName = entityName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}

	public ResourceNotFoundException(EntityName resourceName, SearchParamType fieldName) {
		super();
		this.resourceName = resourceName;
		this.fieldName = fieldName;
	}

	public void setApiResponse() {
		String message = String.format("%s not found with %s: '%s'", resourceName, fieldName, fieldValue);
		apiResponse = new ErrorMessageResponse(message);
	}*/


	private EntityName entityName;
	private SearchParamType fieldName;
	private Object fieldValue;

	private final HttpStatus status = HttpStatus.NOT_FOUND;
	private Integer statusCode;
	private String message;
	private LocalDateTime timestamp;

	private ErrorResponse errorResponse;

	public ResourceNotFoundException(EntityName entityName, SearchParamType fieldName, Object fieldValue) {
		super();
		this.entityName = entityName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
		String message = String.format("%s not found with %s: '%s'", entityName, fieldName, fieldValue);
		ErrorResponse(message);
	}

	public ResourceNotFoundException(EntityName entityName, SearchParamType fieldName) {
		super();
		this.entityName = entityName;
		this.fieldName = fieldName;
		String message = String.format("%s not found with %s", entityName, fieldName);
		ErrorResponse(message);
	}

	private void ErrorResponse(String message) {
		errorResponse = new ErrorResponse(
				status.name(),
				status.value(),
				message,
				LocalDateTime.now()
		);
	}
}
