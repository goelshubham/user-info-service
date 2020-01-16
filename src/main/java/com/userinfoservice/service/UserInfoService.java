package com.userinfoservice.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.ValidationException;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import com.userinfoservice.entity.Usage;
import com.userinfoservice.entity.UsageRequest;
import com.userinfoservice.entity.User;
import com.userinfoservice.exceptions.DatabaseException;
import com.userinfoservice.repository.UserRepository;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Component
@Data
@Slf4j
public class UserInfoService {

	@Autowired
	private UserRepository userRepository;

	public String storeUserInfo(User user) throws Exception {

		log.debug("storeUserInfo() - start of method");

		String userId = RandomStringUtils.randomAlphabetic(10);
		user.setId(userId);
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
		if (optional.isPresent()) {

			User fetchedUser = optional.get();
			List<Usage> usages = fetchedUser.getUsages();
			Usage usage = new Usage();
			usage.setUsageTime(this.formatUsageDate(usageRequest.getUsageTime()));
			usage.setUsageType(usageRequest.getUsageType());
			usages.add(usage);
			fetchedUser.setUsages(usages);
			userRepository.save(fetchedUser);
		} else {
			throw new ValidationException("Invalid Request - UserId does not exist");
		}
	}

	public Date formatUsageDate(String inputDate) throws ParseException {
		SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
		return myFormat.parse(inputDate);
	}

	public User getUserUsageInfo(String userId, String type, String startTime) throws Exception {

		Optional<User> optional = userRepository.findById(userId);
		
		if (!optional.isPresent())
			throw new ValidationException("Invalid Request - UserId does not exist");

		Date formatedStartTime = this.formatUsageDate(startTime);
		User user = optional.get();

		if (user != null) {
			List<Usage> filteredUsages;
			if (type.equals("ALL")) {
				filteredUsages = user.getUsages().stream()
						.filter(usage -> usage.getUsageTime().after(formatedStartTime)).collect(Collectors.toList());
			} else {
				filteredUsages = user.getUsages().stream().filter(usage -> usage.getUsageType().equals(type))
						.filter(usage -> usage.getUsageTime().after(formatedStartTime)).collect(Collectors.toList());
			}
			user.setUsages(filteredUsages);
		}
		return user;
	}
}
