package com.userinfoservice.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class UserId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6090188926830377497L;

	private String id;

	private String email;

	public UserId(String id, String email) {
		super();
		this.id = id;
		this.email = email;
	}
	
	public UserId() {
		// TODO Auto-generated constructor stub
	}
}
