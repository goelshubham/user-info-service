package com.userinfoservice.repository;

import org.springframework.data.repository.CrudRepository;

import com.userinfoservice.entity.User;
import com.userinfoservice.entity.UserId;

public interface UserRepository extends CrudRepository<User, UserId> {

}
