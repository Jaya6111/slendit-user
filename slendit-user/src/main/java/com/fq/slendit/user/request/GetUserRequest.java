package com.fq.slendit.user.request;

import javax.validation.constraints.Email;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetUserRequest {

	@Email(message = "Please provide a valid email")
	private String email;
}
