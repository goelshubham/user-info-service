package com.userinfoservice.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.validator.EmailValidator;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.userinfoservice.entity.User;
import com.userinfoservice.exceptions.ValidationException;

@Component
public class ValidationService {

	public void validateUser(User user) throws Exception {
		if(user == null)
			throw new ValidationException("Invalid Request");
		
		if(StringUtils.isEmpty(user.getUserName()))
			throw new ValidationException("Required field(s) not present.");
		
		if(StringUtils.isEmpty(user.getEmail()))
			throw new ValidationException("Required field(s) not present.");
		
		if(StringUtils.isEmpty(user.getCountry()))
			throw new ValidationException("Required field(s) not present.");
		
		if(StringUtils.isEmpty(user.getPhoneNumber()))
			throw new ValidationException("Required field(s) not present.");
		
		char[] charsInName = user.getUserName().toCharArray();
	    for (char c : charsInName) {
	        if(!Character.isLetter(c) && c != ' ' ) {
	        	throw new ValidationException("Name should contain only characters and spaces");
	        }
	    }
	    
	    if(!EmailValidator.getInstance().isValid(user.getEmail())) {
	    	throw new ValidationException("Invalid email");
	    }
	    
	    Pattern pattern = Pattern.compile("\\d{3}-\\d{7}");
	    Matcher matcher = pattern.matcher(user.getPhoneNumber());
	    if(!matcher.matches()) {
	    	throw new ValidationException("Invalid phonenumber: Phone number should of of format: xxx-xxxxxxx");
	    }
	}
	
}
