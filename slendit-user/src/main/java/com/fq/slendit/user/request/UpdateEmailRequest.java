package com.fq.slendit.user.request;

import javax.persistence.Column;
import javax.validation.constraints.Email;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateEmailRequest {
	@Email(message = "Please provide valid email")
	@Column(unique = true)
	private String currentEmail;
	@Email(message = "Please provide valid email")
	@Column(unique = true)
	private String newEmail;
}
