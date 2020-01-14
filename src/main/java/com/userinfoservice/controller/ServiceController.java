package com.userinfoservice.controller;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.userinfoservice.entity.User;
import com.userinfoservice.entity.UserResponse;
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
		response.setId(userId);
		return new ResponseEntity<UserResponse>(response, HttpStatus.OK);
	}

}
