package com.userinfoservice.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.ValidationException;

import org.apache.commons.lang3.RandomStringUtils;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.userinfoservice.entity.Usage;
import com.userinfoservice.entity.UsageRequest;
import com.userinfoservice.entity.User;
import com.userinfoservice.entity.UserRequest;
import com.userinfoservice.exceptions.DatabaseException;
import com.userinfoservice.repository.UsageRepository;
import com.userinfoservice.repository.UserRepository;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Component
@Data
@Slf4j
public class UserInfoService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UsageRepository usageRepository;

	public String storeUserInfo(UserRequest userRequest) throws Exception {

		log.debug("storeUserInfo() - start of method");

		String userId = RandomStringUtils.randomAlphabetic(10);
		User user = new User();
		user.setId(userId);
		user.setUserName(userRequest.getUserName());
		user.setCountry(userRequest.getCountry());
		user.setPhoneNumber(userRequest.getPhoneNumber());
		user.setEmail(userRequest.getEmail());
		try {
			userRepository.save(user);
		} catch (Exception ex) {
			log.error("Exception occurred with message {} ", ex.getMessage());
			if (ex instanceof DataIntegrityViolationException) {
				throw new ValidationException("Email cannot be same for two users");
			} else {
				ex.printStackTrace();
				throw new DatabaseException("An exception occured in database operation");
			}
		}
		return userId;
	}

	public void storeUsageInfo(UsageRequest usageRequest) throws Exception {

		log.debug("storeUsageInfo() - start of method");

		Optional<User> optional = userRepository.findById(usageRequest.getUserId());
		if(!optional.isPresent()) {
			throw new ValidationException("Invalid Request - UserId does not exist");
		}
		
		Usage usage = new Usage();
		usage.setUserId(usageRequest.getUserId());
		usage.setUsageType(usageRequest.getUsageType());
		usage.setUsageTime(this.formatUsageDate(usageRequest.getUsageTime()));
		
		usageRepository.save(usage);
	}

	public Date formatUsageDate(String inputDate) throws ParseException {
		SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
		return myFormat.parse(inputDate);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public User getUserUsageInfo(String userId, String type, String startTime) throws Exception {

		Optional<User> optional = userRepository.findById(userId);
		
		if (!optional.isPresent())
			throw new ValidationException("Invalid Request - UserId does not exist");

		Date formatedStartTime = this.formatUsageDate(startTime);
		User user = optional.get();
		List<Usage> usages;
		if(type.equalsIgnoreCase("ALL")) {
			usages = usageRepository.usageListAllUsageType(userId, formatedStartTime);
		} else {
			usages = usageRepository.usageListForUsageType(userId, type, formatedStartTime);
		}
		
		user.setUsages(usages);
		return user;
	}
}
