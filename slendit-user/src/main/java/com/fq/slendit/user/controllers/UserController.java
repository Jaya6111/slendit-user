package com.fq.slendit.user.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@PostMapping("/register")
	public String save(@RequestParam String s) {
		return "Registered successfully";
		
	}
}
