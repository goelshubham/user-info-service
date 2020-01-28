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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import lombok.Data;

/**
 * @author goels10 Java entity to store User information in the DB
 */
@Entity
@Data
public class User {

	@Id
	String id;

	@Column(unique = true)
	String email;

	String userName;

	String country;

	String phoneNumber;

	@OneToMany
	@JoinColumn(name="userId")
	List<Usage> usages = new ArrayList<Usage>();

}
