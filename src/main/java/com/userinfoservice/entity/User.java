/**
 * 
 */
package com.userinfoservice.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.UniqueConstraint;

import lombok.Data;

/**
 * @author goels10 Java entity to store User information in the DB
 */
@Entity
@Data
//@IdClass(UserId.class)
public class User {

	@Id
	String id;

	@Column(unique = true)
	String email;

	String userName;

	String country;

	String phoneNumber;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	//@JoinColumn(name = "USER_USAGE_ID", nullable = true)
	List<Usage> usages = new ArrayList<Usage>();

}
