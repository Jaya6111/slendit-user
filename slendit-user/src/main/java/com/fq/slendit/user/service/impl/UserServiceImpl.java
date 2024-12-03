package com.fq.slendit.user.service.impl;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fq.slendit.user.repository.UserRepository;
import com.fq.slendit.user.request.RegistrationRequest;
import com.fq.slendit.user.response.RegistrationResponse;
import com.fq.slendit.user.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public RegistrationResponse registration(@Valid RegistrationRequest request) {
		return null;
	}

}
