package com.fq.slendit.user.response;

import java.util.Set;

import org.springframework.http.HttpStatus;

public class ExceptionResponse extends AbstractResponse {

	public ExceptionResponse(HttpStatus status, String statusCode, String message, Set<String> errorMessages) {
		super(status, statusCode, message, errorMessages);
	}

}
