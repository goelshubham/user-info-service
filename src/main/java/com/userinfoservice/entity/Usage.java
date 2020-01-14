/**
 * 
 */
package com.userinfoservice.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

/**
 * @author goels10 
 * Java entity to store user's usage information in the DB
 */
@Entity
@Data
public class Usage {

	@Id
	@GeneratedValue
	int id;

	String userId;

	String usageType;

	Date usageTime;
}
