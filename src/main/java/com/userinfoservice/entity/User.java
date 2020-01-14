/**
 * 
 */
package com.userinfoservice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.UniqueConstraint;

import lombok.Data;

/**
 * @author goels10
 * Java entity to store User information in the DB
 */
@Entity
@Data
@IdClass(UserId.class)
public class User {

	//id and email forms the composite primary key of User table
	@Id
	String id;

	@Id
	@Column(unique=true)
	String email;

	String userName;

	String country;

	String phoneNumber;

}
