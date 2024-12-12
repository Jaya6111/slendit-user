package com.fq.slendit.user.service.impl;

import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fq.slendit.user.entity.User;
import com.fq.slendit.user.repository.UserRepository;
import com.fq.slendit.user.request.GetUserRequest;
import com.fq.slendit.user.request.RegistrationRequest;
import com.fq.slendit.user.request.ResetPasswordRequest;
import com.fq.slendit.user.request.UpdateEmailRequest;
import com.fq.slendit.user.request.UpdateUserRequest;
import com.fq.slendit.user.request.WelcomeMailRequest;
import com.fq.slendit.user.response.GetUserResponse;
import com.fq.slendit.user.response.RegistrationResponse;
import com.fq.slendit.user.response.ResetPasswordResponse;
import com.fq.slendit.user.response.UpdateEmailResponse;
import com.fq.slendit.user.response.UpdateUserResponse;
import com.fq.slendit.user.response.VerificationToken;
import com.fq.slendit.user.service.UserService;
import com.fq.slendit.user.utils.DateUtil;
import com.fq.slendit.user.utils.UserUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	private final RestTemplate restTemplate = new RestTemplate();

	@Override
	public RegistrationResponse registration(@Valid RegistrationRequest request) {

		log.info("UserServiceImpl.registration(): entry point");
		RegistrationResponse response = null;
		Optional<User> users = userRepository.findByEmail(request.getEmail());

		if (users.isPresent()) {
			log.info("UserServiceImpl.registration(): Given Email is already in use");
			return new RegistrationResponse(HttpStatus.PRECONDITION_FAILED, "412", "Given Email is already in use",
					null);
		}

		User user = UserUtil.setUser(request);
		user.setPassword(encodePassword(request.getPassword()));

		user = userRepository.save(user);

		if (user.getUserId() > 0) {
			log.info("UserServiceImpl.registration(): Registration successfull");
			response = new RegistrationResponse(HttpStatus.CREATED, "201", "Registration successfull", null);
			response.setUser(user);
			
			VerificationToken token = new VerificationToken(user.getVerificationCode(), user.getEmail(), user.getUpdated().plusHours(24));

			
			WelcomeMailRequest welcomeRequest = new WelcomeMailRequest(user.getEmail(), user.getFirstName() + " " + user.getLastName(), "Welcome to SLENDIT");
			
			restTemplate.postForEntity("http://localhost:8083/email/confirm-email", token, String.class);
			restTemplate.postForEntity("http://localhost:8083/email/welcome", welcomeRequest, String.class);
			// kafkaTemplate.sed(topic, verificatinAndWelcome);

		} else {
			log.info("UserServiceImpl.registration(): Registration get failed");
			response = new RegistrationResponse(HttpStatus.INTERNAL_SERVER_ERROR, "500", "Registration is failed",
					null);
		}

		return response;
	}

	@Override
	@Transactional
	public UpdateUserResponse updateUser(@Valid UpdateUserRequest request) {

		Optional<User> opUser = userRepository.findById(request.getUserId());

		if (!opUser.isPresent()) {
			return new UpdateUserResponse(HttpStatus.NOT_FOUND, "404", "User not found with Id: " + request.getUserId(),
					null);
		}

		User user = opUser.get();
		user = UserUtil.setUpdateUser(user, request);

		user = userRepository.save(user);

		UpdateUserResponse response = new UpdateUserResponse(HttpStatus.OK, "200", "User details updated successfully",
				null);
		response.setUser(user);

		return response;
	}

	@Override
	public GetUserResponse getUser(@Valid GetUserRequest request) {

		GetUserResponse response = null;

		Optional<User> opUser = userRepository.findByEmail(request.getEmail());
		if (opUser.isPresent()) {
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

	@Override
	public VerificationToken getVerifivcationToken(String token) {

		Optional<VerificationToken> Otoken = Optional.empty();
		VerificationToken userToken = new VerificationToken();

		if (null != token && !"".equals(token.trim())) {
			Otoken = userRepository.findByVerificationCode(token);

			if (Otoken.isPresent()) {
				userToken = Otoken.get();
				userToken.setExpiryDate(userToken.getExpiryDate().plusHours(24));
			}
			return userToken;
		}

		userToken.setToken(null);
		userToken.setExpiryDate(null);
		return userToken;
	}

	@Override
	public String deleteToken(String token) {

		try {
			if (null != token && !"".equals(token)) {
				userRepository.deleteToken(token);
				return "Token deletion success";
			}
		} catch (Exception e) {
			return "Token not found";
		}
		return "Invalid Token";
	}

	@Override
	public UpdateEmailResponse updateEmail(@Valid UpdateEmailRequest request) {
		int isUpdated = 0;
		UpdateEmailResponse response = null;
		Optional<User> users = userRepository.findByEmail(request.getCurrentEmail());

		if (!users.isPresent()) {
			response = new UpdateEmailResponse(HttpStatus.PRECONDITION_FAILED, "412", "Pre condition failed", null);
			response.setMessage("No user found with given current email");
			return response;
		} else {
			Optional<User> OpUser = userRepository.findByEmail(request.getNewEmail());

			if (!OpUser.isPresent()) {
				String verificationCode = UUID.randomUUID().toString();
				isUpdated = userRepository.updateEmail(request.getNewEmail(),verificationCode, request.getCurrentEmail());

				if (isUpdated > 0) {

					VerificationToken token = new VerificationToken(verificationCode, request.getNewEmail(),
							DateUtil.getCurrentDateTime().plusHours(24));
					restTemplate.postForEntity("http://localhost:8083/email/confirm-email", token, String.class);

					return new UpdateEmailResponse(HttpStatus.OK, "200", "Email updatedd Successfully", null);

				} else {
					return new UpdateEmailResponse(HttpStatus.EXPECTATION_FAILED, "417", "Failed to update the Email",
							null);
				}

			}
			response = new UpdateEmailResponse(HttpStatus.PRECONDITION_FAILED, "412", "Pre condition failed", null);
			response.setMessage("New Email is already registered for another user");
			return response;
		}
	}
}
