package com.fq.slendit.user.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetUserRequest {

	@NotNull(message = "UserId is required")
	@Min(value = 1, message = "UserId must be a positive number")
	private String userId;
}
