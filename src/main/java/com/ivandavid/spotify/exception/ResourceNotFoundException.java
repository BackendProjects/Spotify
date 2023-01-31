package com.ivandavid.spotify.exception;

import com.ivandavid.spotify.enums.EntityName;
import com.ivandavid.spotify.enums.SearchParamType;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Data
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
	private EntityName resourceName;
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
	}
}
