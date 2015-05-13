package com.michir.projects.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.michir.projects.spring.api.IUsersRepository;
import com.michir.projects.spring.api.User;

@RestController
public class MyRestController {

	@Autowired
	@Qualifier("dbUsersRepository")
	private IUsersRepository usersRepository;
	
	@RequestMapping("/rest/{name}")
	public User user(@PathVariable("name") String name) {
		return usersRepository.findByName(name);
	}
}
