package com.userinfoservice.repository;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.userinfoservice.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, String> {

	@Query("SELECT u1 FROM User u1 JOIN u1.usages u2  where u1.id = :id AND u2.usageType = :type AND u2.usageTime > :startTime")
	User findUserUsageInfo(@Param("id") String id, @Param("type") String type, @Param("startTime") Date startTime );
	
}
