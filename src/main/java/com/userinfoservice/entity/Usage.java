/**
 * 
 */
package com.userinfoservice.entity;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

/**
 * @author goels10 
 * Java entity to store user's usage information in the DB
 */
@Entity
@Data
public class Usage {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;

	private String usageType;

	@Temporal(TemporalType.DATE)
	private Date usageTime;
}
