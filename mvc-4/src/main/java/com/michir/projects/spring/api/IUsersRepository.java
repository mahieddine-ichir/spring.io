package com.michir.projects.spring.api;


public interface IUsersRepository {

	User findByName(String name);
	
}
