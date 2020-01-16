package com.userinfoservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public class DatabaseException extends Exception{

	public DatabaseException(String message) {
		super(message);
	}
	
}
