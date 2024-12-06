package com.fq.slendit.user.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fq.slendit.user.entity.User;
import com.fq.slendit.user.repository.UserRepository;
import com.fq.slendit.user.request.GetUserRequest;
import com.fq.slendit.user.request.RegistrationRequest;
import com.fq.slendit.user.request.ResetPasswordRequest;
import com.fq.slendit.user.request.UpdateUserRequest;
import com.fq.slendit.user.response.GetUserResponse;
import com.fq.slendit.user.response.RegistrationResponse;
import com.fq.slendit.user.response.ResetPasswordResponse;
import com.fq.slendit.user.response.UpdateUserResponse;
import com.fq.slendit.user.service.UserService;
import com.fq.slendit.user.utils.UserUtil;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Override
	public RegistrationResponse registration(@Valid RegistrationRequest request) {

		logger.info("UserServiceImpl.registration(): entry point");
		RegistrationResponse response = null;
		Optional<User> users = userRepository.findByEmail(request.getEmail());
		
		if(users.isPresent()) {
			logger.info("UserServiceImpl.registration(): Given Email is already in use");
			return new RegistrationResponse(HttpStatus.PRECONDITION_FAILED, "412", "Given Email is already in use", null);
		}
		
		User user = UserUtil.setUser(request);
		user.setPassword(encodePassword(request.getPassword()));
		
		user = userRepository.save(user);
		
		if(user.getUserId() > 0) {
			logger.info("UserServiceImpl.registration(): Registration successfull");
			response = new RegistrationResponse(HttpStatus.CREATED, "201", "Registration successfull", null);
			response.setUser(user);
			//kafkaTemplate.sed(topic, verificatinAndWelcome);
			
		}else {
			logger.info("UserServiceImpl.registration(): Registration get failed");
			response = new RegistrationResponse(HttpStatus.INTERNAL_SERVER_ERROR, "500", "Registration is failed", null);
		}
		
		return response;
	}

	@Override
	@Transactional
	public UpdateUserResponse updateUser(@Valid UpdateUserRequest request) {

		
		Optional<User> opUser = userRepository.findById(request.getUserId());
		
		if(!opUser.isPresent()) {
			return new UpdateUserResponse(HttpStatus.NOT_FOUND, "404", "User not found with Id: "  + request.getUserId(), null);
		}
		
		User user = opUser.get();
		user = UserUtil.setUpdateUser(user, request);

		user = userRepository.save(user);
		
		UpdateUserResponse response = new UpdateUserResponse(HttpStatus.OK, "200", "User details updated successfully", null);
		response.setUser(user);
		
		return response;
	}
	
	@Override
	public GetUserResponse getUser(@Valid GetUserRequest request) {
		
		GetUserResponse response = null;
		
		Optional<User> opUser = userRepository.findByEmail(request.getEmail());
		if(opUser.isPresent()) {
			response = new GetUserResponse(HttpStatus.OK, "200", "User found success", null);
			response.setUser(opUser.get());
		}
		
		return new GetUserResponse(HttpStatus.NOT_FOUND, "404", "User not found", null);
	}

	@Override
	public ResetPasswordResponse resetPassword(@Valid ResetPasswordRequest request) {

		ResetPasswordResponse response = null;
		String newPassword = encodePassword(request.getNewPassword());

		int isReset = userRepository.resetPassword(newPassword, request.getEmail(), request.getUserId());

		if (isReset > 0) {
			response = new ResetPasswordResponse(HttpStatus.OK, "200", "Reset password successful", null);
		} else {
			response = new ResetPasswordResponse(HttpStatus.NOT_FOUND, "404", "Reset password failed", null);
		}
		response.setEmail(request.getEmail());

		return response;
	}
	
	private String encodePassword(String password) {
		return passwordEncoder.encode(password);
	}
}
