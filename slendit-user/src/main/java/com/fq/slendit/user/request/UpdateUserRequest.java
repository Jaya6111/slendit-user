package com.fq.slendit.user.request;

import javax.persistence.Column;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserRequest {
	
	@NotNull(message = "UserId is required")
	@Min(value = 1, message = "UserId must be a positive number")
	private int userId;

	@NotBlank(message = "First name is required")
	private String firstName;
	@Column(name = "middleName")
	private String middleName;
	@NotBlank(message = "Last name is required")
	private String lastName;

	@NotBlank(message = "Display name is required")
	@Size(max = 50, message = "Display name must not exceed 50 characters")
	private String userName;
	
	@Column
	private String homeAddress;
	@Column
	private String workAddress;

	@Size(max = 255, message = "Home street address must not exceed 255 characters")
	private String homeStreetAddress;

	@Size(max = 30, message = "Home city must not exceed 30 characters")
	private String homeCity;

	@Size(max = 30, message = "Home state must not exceed 30 characters")
	private String homeState;

	@Pattern(regexp = "^[0-9]{6}$", message = "Home ZIP code must be 6 digits")
	private String homeZip;

	@Size(max = 30, message = "Home country must not exceed 30 characters")
	private String homeCountry;

	@Column
	private String workStreetAddress;
	@Column
	private String workCity;
	@Column
	private String workState;
	@Column
	private String workZip;
	@Column
	private String workCountry;

	@NotBlank(message = "Mobile number is required")
	@Pattern(regexp = "^[0-9]{10}$", message = "Please provide a valid mobile number")
	private String mobile;
	@NotBlank(message = "Please mention about")
	private String aboutMe;
}
