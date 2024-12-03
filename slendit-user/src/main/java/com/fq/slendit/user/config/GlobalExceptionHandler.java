package com.fq.slendit.user.config;

import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fq.slendit.user.response.ExceptionResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ExceptionResponse validationExceptionHandler(MethodArgumentNotValidException ex) {

		Set<String> errorMessages = ex.getBindingResult().getFieldErrors().stream()
				.map(error -> error.getField() + ": " + error.getDefaultMessage()).collect(Collectors.toSet());

		return buildExceptionResponse(HttpStatus.BAD_REQUEST, "400", "Validation failed", errorMessages);
	}

	@ExceptionHandler(EntityNotFoundException.class)
	public ExceptionResponse entityNotFoundHandler(EntityNotFoundException ex) {
		return buildExceptionResponse(HttpStatus.NOT_FOUND, "404", ex.getMessage(), null);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ExceptionResponse illegalArgumentHandler(IllegalArgumentException ex) {
		return buildExceptionResponse(HttpStatus.BAD_REQUEST, "400", ex.getMessage(), null);
	}

	@ExceptionHandler(Exception.class)
	public ExceptionResponse generalExceptionHandler(Exception ex) {
		return buildExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR, "500", "An unexpected error occured", null);
	}

	private ExceptionResponse buildExceptionResponse(HttpStatus status, String statusCode, String message,
			Set<String> errorMessages) {
		return new ExceptionResponse(status, statusCode, message, errorMessages);
	}

}
