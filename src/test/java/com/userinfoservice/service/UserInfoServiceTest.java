package com.userinfoservice.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.userinfoservice.entity.UserRequest;
import com.userinfoservice.repository.UserRepository;

@RunWith(MockitoJUnitRunner.class)
public class UserInfoServiceTest {

	@InjectMocks
	private UserInfoService userInfoService;
	
	@Mock
	private UserRepository userRepository;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void test_storeUserInfo() throws Exception{
		UserRequest request = new UserRequest();
		request.setUserName("shubham");
		request.setCountry("US");
		request.setPhoneNumber("716-3499290");
		request.setEmail("test@gmail.com");
		
		Mockito.when(userRepository.save(Mockito.any())).thenReturn(Mockito.any());
		
		String userId = userInfoService.storeUserInfo(request);
		
		assertNotNull(userId);
		assertEquals(userId.length(), 10);
	}
	
}
