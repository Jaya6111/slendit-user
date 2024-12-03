package com.fq.slendit.user.request;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationRequest {
	
	@NotBlank(message = "firstName is required")
	private String firstName;
	@Column(name = "middleName")
	private String middleName;
	@NotBlank(message = "lastName is required")
	private String lastName;

	@NotBlank(message = "Email is required")
	@Email(message = "Please provide valid email")
	@Column(unique = true)
	private String email;
	@NotBlank(message = "Mobile number is required")
	@Pattern(
			regexp = "^[0-9]{10}$",
			message = "Please provide valid mobile number"
			)
	private String mobile;
	
	@NotBlank(message = "UserName is required")
	private String userName;
	@NotBlank(message = "Password is required")
	@Pattern(
		        regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
		        message = "Password must be at least 8 characters long, include one uppercase letter, one lowercase letter, one number, and one special character"
		    )
	private String password;

    @NotBlank(message = "Please mention about")
	private String aboutMe;
    @NotBlank(message = "Country is required")
	private String country;
    @NotBlank(message = "Role is Required")
    private String role;
	
}
