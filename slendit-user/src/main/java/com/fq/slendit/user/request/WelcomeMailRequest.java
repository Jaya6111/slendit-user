package com.fq.slendit.user.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WelcomeMailRequest {

    @NotBlank(message = "Recipient email is required")
    @Email(message = "Please give a valid email")
    private String recipientEmail;

    @NotBlank(message = "Recipient name is required")
    private String recipientName;

    @NotBlank(message = "Email subject is required")
    private String subject;
    
}
