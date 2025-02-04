package com.fq.slendit.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import com.fq.slendit.user.controllers.UserController;
import com.fq.slendit.user.entity.User;
import com.fq.slendit.user.request.GetUserRequest;
import com.fq.slendit.user.response.GetUserResponse;
import com.fq.slendit.user.service.UserService;

@ExtendWith(MockitoExtension.class)
public class UserControllerGetTest {

	@InjectMocks
	private UserController usercontroller;
	
	@Mock
	private UserService userService;
	
	@Test
	void testGetUserSuccess() {
		GetUserRequest request = new GetUserRequest();
		request.setEmail("Surya.m6111@gamil.com");
		
		GetUserResponse expectedResponse = new GetUserResponse(HttpStatus.OK, "200", "User found success", null);
		expectedResponse.setUser(new User());
		
		when(userService.getUser(Mockito.any())).thenReturn(expectedResponse);
		
		GetUserResponse response = userService.getUser(request);
		
		assertNotNull(response);
		assertEquals(expectedResponse, response);
		assertNotEquals(null, response);
		
		verify(userService, times(1)).getUser(request);
	}
	
	@Test
	void testGetUserFailure() {
		
		GetUserRequest request = new GetUserRequest();
		request.setEmail("Surya.m6111");
		
		GetUserResponse expectedResponse = new GetUserResponse(HttpStatus.PRECONDITION_FAILED, "412", "Pre condition Failed", null);
		expectedResponse.setUser(null);
		
		when(userService.getUser(Mockito.any())).thenReturn(expectedResponse);
		
		GetUserResponse response = userService.getUser(request);
		
		assertEquals(expectedResponse, response);
	}
}
