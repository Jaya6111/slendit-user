package com.fq.slendit.user.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class VerificationToken {

	private String token;
	private String email;
	private LocalDateTime expiryDate;

}
