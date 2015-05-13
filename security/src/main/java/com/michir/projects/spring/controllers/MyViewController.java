package com.michir.projects.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.michir.projects.spring.api.IUsersRepository;


@Controller
public class MyViewController {

	@Autowired
	@Qualifier("inMemoryRepository")
	private IUsersRepository usersRepository;
	
	@RequestMapping(value="/view/{name}", method=RequestMethod.GET)
	public String hello(@PathVariable("name") String name, Model model) {
		model.addAttribute("user", usersRepository.findByName(name));
		return "helloUser";
	}
}
