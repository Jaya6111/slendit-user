package com.fq.slendit.user.service;

import javax.validation.Valid;

import com.fq.slendit.user.request.GetUserRequest;
import com.fq.slendit.user.request.RegistrationRequest;
import com.fq.slendit.user.request.ResetPasswordRequest;
import com.fq.slendit.user.request.UpdateUserRequest;
import com.fq.slendit.user.response.GetUserResponse;
import com.fq.slendit.user.response.RegistrationResponse;
import com.fq.slendit.user.response.ResetPasswordResponse;
import com.fq.slendit.user.response.UpdateUserResponse;

public interface UserService {

	public RegistrationResponse registration(@Valid RegistrationRequest request);

	public UpdateUserResponse updateUser(@Valid UpdateUserRequest request);

	public ResetPasswordResponse resetPassword(@Valid ResetPasswordRequest request);

	public GetUserResponse getUser(@Valid GetUserRequest request);

}
