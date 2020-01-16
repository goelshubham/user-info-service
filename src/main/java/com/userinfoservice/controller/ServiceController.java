package com.userinfoservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.userinfoservice.entity.UsageRequest;
import com.userinfoservice.entity.User;
import com.userinfoservice.entity.UserResponse;
import com.userinfoservice.entity.UserUsageRequest;
import com.userinfoservice.service.UserInfoService;
import com.userinfoservice.service.ValidationService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(path = "/userinfoapi/v1")
@Slf4j
public class ServiceController {

	@Autowired
	private UserInfoService userInfoService;

	@Autowired
	private ValidationService validationService;

	@PostMapping(path = "/user", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserResponse> storeUserInfo(@RequestBody User user) throws Exception {

		log.debug("storeUserInfo() - start of method");
		validationService.validateUser(user);

		String userId = userInfoService.storeUserInfo(user);
		UserResponse response = new UserResponse();
		response.setUserId(userId);
		return new ResponseEntity<UserResponse>(response, HttpStatus.OK);
	}
	
	@PostMapping(path = "/usage", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void storeUsageInfo(@RequestBody UsageRequest usageRequest) throws Exception {

		log.debug("storeUsageInfo() - start of method");
		validationService.validateUsage(usageRequest);

		userInfoService.storeUsageInfo(usageRequest);
	}
	
	@GetMapping(path = "/user/usage/{userId}/{type}/{startTime}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> getUserUsageInfo(@PathVariable("userId") String userId,
								 @PathVariable("type") String type,
								 @PathVariable("startTime") String startTime) throws Exception {
		log.debug("getUserUsageInfo() - start of method");
		
		validationService.validateUserUsageRequest(userId, type, startTime);
		
		User response = userInfoService.getUserUsageInfo(userId, type, startTime);
		return new ResponseEntity<User>(response, HttpStatus.OK);
	}

}
