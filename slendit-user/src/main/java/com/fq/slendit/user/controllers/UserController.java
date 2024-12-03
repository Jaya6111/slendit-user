package com.fq.slendit.user.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fq.slendit.user.request.RegistrationRequest;
import com.fq.slendit.user.response.RegistrationResponse;
import com.fq.slendit.user.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/registration")
	public RegistrationResponse save(@RequestBody @Valid RegistrationRequest request) {
		
		return userService.registration(request);
		
	}
}
