package com.fq.slendit.user.response;

import java.util.Set;

import org.springframework.http.HttpStatus;

import com.fq.slendit.user.entity.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserResponse extends AbstractResponse{

	private User user;
	
	public UpdateUserResponse(HttpStatus status, String statusCode, String message, Set<String> errorMessages) {
		
		super(status, statusCode, message, errorMessages);
		this.status = status;
		this.statusCode = statusCode;
		this.message = message;
		this.errorMessages = errorMessages;
	}
}
