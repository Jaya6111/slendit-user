package com.fq.slendit.user.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fq.slendit.user.request.GetUserRequest;
import com.fq.slendit.user.request.RegistrationRequest;
import com.fq.slendit.user.request.ResetPasswordRequest;
import com.fq.slendit.user.request.UpdateEmailRequest;
import com.fq.slendit.user.request.UpdateUserRequest;
import com.fq.slendit.user.response.GetUserResponse;
import com.fq.slendit.user.response.RegistrationResponse;
import com.fq.slendit.user.response.ResetPasswordResponse;
import com.fq.slendit.user.response.UpdateEmailResponse;
import com.fq.slendit.user.response.UpdateUserResponse;
import com.fq.slendit.user.response.VerificationToken;
import com.fq.slendit.user.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/registration")
	public RegistrationResponse save(@RequestBody @Valid RegistrationRequest request) {
		request.setRole("USER");
		return userService.registration(request);
		
	}
	
	@PutMapping("/updateUser")
	public UpdateUserResponse updateUser(@RequestBody @Valid UpdateUserRequest request) {
		
		return userService.updateUser(request);
	}
	
	@GetMapping("/getUser")
	public GetUserResponse getUser(@RequestBody @Valid GetUserRequest request) {
		
		return userService.getUser(request);
	}
	
	@PutMapping("/resetPassword")
	public ResetPasswordResponse resetPassword(@RequestBody @Valid ResetPasswordRequest request) {
		
		if(request.getNewPassword().equals(request.getConformPassword())) {
			return userService.resetPassword(request);
		}
		return new ResetPasswordResponse(HttpStatus.PRECONDITION_FAILED, "412", "Password not matching", null);
	}
	
	@GetMapping("/get-token/{token}")
	public VerificationToken getVerifivcationToken(@PathVariable String token) {
		
		return userService.getVerifivcationToken(token);
	}
	
	@DeleteMapping("/delete-token/{token}")
	public String deleteToken(@PathVariable String token) {
		return userService.deleteToken(token);
	}
	
	@PutMapping("/update-email")
	public UpdateEmailResponse updateEmail(@RequestBody @Valid UpdateEmailRequest request) {
		return userService.updateEmail(request);
	}
}
