package com.fq.slendit.user.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResetPasswordRequest {

	@NotNull(message = "UserId is required")
	@Min(value = 1, message = "UserId must be a positive number")
	private int userId;
	@NotBlank(message = "Please enter the current password")
	private String currentPassword;
	@NotBlank(message = "Please enter the new password")
	@Pattern(
			regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", 
			message = "Password must be at least 8 characters long, include one uppercase letter, one lowercase letter, one number, and one special character"
			)
	private String newPassword;

}
