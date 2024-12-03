package com.fq.slendit.user.service;

import javax.validation.Valid;

import com.fq.slendit.user.request.RegistrationRequest;
import com.fq.slendit.user.response.RegistrationResponse;

public interface UserService {

	public RegistrationResponse registration(@Valid RegistrationRequest request);

}
