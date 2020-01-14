package com.userinfoservice.service;

import java.sql.SQLException;

import javax.validation.ValidationException;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import com.userinfoservice.entity.User;
import com.userinfoservice.repository.UserRepository;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Component
@Data
@Slf4j
public class UserInfoService {

	@Autowired
	private UserRepository userRepository;

	public String storeUserInfo(User user) {
		
		log.debug("storeUserInfo() - start of method");
		
		String userId = RandomStringUtils.randomAlphabetic(10);
		user.setId(userId);
		try {			
			userRepository.save(user);
		}
		catch(Exception ex) {
			log.error("Exception occurred with message {} ", ex.getMessage());
			if(ex instanceof DataIntegrityViolationException) {
				throw new ValidationException("Email cannot be same for two users");
			}
		}
		return userId;
	}

}
