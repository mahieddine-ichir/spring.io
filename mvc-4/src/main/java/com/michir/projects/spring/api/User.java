package com.michir.projects.spring.api;

public class User {

	private String name;
	
	private String email;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 * @return 
	 */
	public User setName(String name) {
		this.name = name;
		return this;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 * @return 
	 */
	public User setEmail(String email) {
		this.email = email;
		return this;
	}
	
}
