package com.userinfoservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class ValidationException extends Exception{

	private static final long serialVersionUID = -6722994478103747595L;

	public ValidationException(String message) {
		super(message);
	}
}
