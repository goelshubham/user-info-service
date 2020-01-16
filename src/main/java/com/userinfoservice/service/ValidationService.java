package com.userinfoservice.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.validator.EmailValidator;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.userinfoservice.entity.UsageRequest;
import com.userinfoservice.entity.User;
import com.userinfoservice.enums.UsageType;
import com.userinfoservice.exceptions.ValidationException;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ValidationService {

	public void validateUser(User user) throws Exception {
		if (user == null)
			throw new ValidationException("Invalid Request");

		if (StringUtils.isEmpty(user.getUserName()))
			throw new ValidationException("Required field(s) not present.");

		if (StringUtils.isEmpty(user.getEmail()))
			throw new ValidationException("Required field(s) not present.");

		if (StringUtils.isEmpty(user.getCountry()))
			throw new ValidationException("Required field(s) not present.");

		if (StringUtils.isEmpty(user.getPhoneNumber()))
			throw new ValidationException("Required field(s) not present.");

		char[] charsInName = user.getUserName().toCharArray();
		for (char c : charsInName) {
			if (!Character.isLetter(c) && c != ' ') {
				throw new ValidationException("Name should contain only characters and spaces");
			}
		}

		if (!EmailValidator.getInstance().isValid(user.getEmail())) {
			throw new ValidationException("Invalid email");
		}

		Pattern pattern = Pattern.compile("\\d{3}-\\d{7}");
		Matcher matcher = pattern.matcher(user.getPhoneNumber());
		if (!matcher.matches()) {
			throw new ValidationException("Invalid phonenumber: Phone number should of of format: xxx-xxxxxxx");
		}
	}

	public void validateUsage(UsageRequest usageRequest) throws Exception {
		if (usageRequest == null)
			throw new ValidationException("Invalid Request");

		boolean found = false;
		for (UsageType type : UsageType.values()) {
			if (type.name().equals(usageRequest.getUsageType())) {
				found = true;
				break;
			}
		}
		if (!found) {
			throw new ValidationException(
					"Invalid user usage type. Should be one of these types: " + Arrays.asList(UsageType.values()));
		}
		
		SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
		myFormat.setLenient(false);
		try {
			myFormat.parse(usageRequest.getUsageTime());
		}
		catch(ParseException ex) {
			log.error("validateUsage() - exception occurred while validate usagetime with error message {} ", ex.getMessage());
			throw new ValidationException("Invalid usage time. Usage time should of format yyyy-mm-dd ");
		}
	}

	public void validateUserUsageRequest(String userId, String type, String startTime) throws Exception {
		if(StringUtils.isEmpty(userId)) {
			throw new ValidationException("Invalid request: Required input param(s) not present");
		}

		if(StringUtils.isEmpty(type)) {
			throw new ValidationException("Invalid request: Required input param(s) not present");
		}
		
		if(StringUtils.isEmpty(startTime)) {
			throw new ValidationException("Invalid request: Required input param(s) not present");
		}
		
		boolean found = false;
		for (UsageType usageType : UsageType.values()) {
			if (usageType.name().equals(type)) {
				found = true;
				break;
			}
		}
		if (!found && !"ALL".equals(type)) {
			throw new ValidationException("Invalid user usage type");
		}
		
		SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
		myFormat.setLenient(false);
		try {
			myFormat.parse(startTime);
		}
		catch(ParseException ex) {
			log.error("validateUsage() - exception occurred while validate startTime with error message {} ", ex.getMessage());
			throw new ValidationException("Invalid startTime. Start time should of format yyyy-mm-dd");
		}
	}

}
