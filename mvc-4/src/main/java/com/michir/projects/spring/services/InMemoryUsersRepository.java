package com.michir.projects.spring.services;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.michir.projects.spring.api.IUsersRepository;
import com.michir.projects.spring.api.User;

@Repository("inMemoryRepository")
@Scope("singleton")
public class InMemoryUsersRepository implements IUsersRepository {

	private Map<Long, User> users = new HashMap<Long, User>();

	public InMemoryUsersRepository() {
		users.put(1l, new User().setName("michir").setEmail("mahieddine.ichir@gmail.com"));
		users.put(2l, new User().setName("toto").setEmail("toto@gmail.com"));
	}
	
	@Override
	public User findByName(String name) {
		for (Entry<Long, User> user : users.entrySet()) {
			if (user.getValue().getName().equalsIgnoreCase(name)) {
				return user.getValue();
			}
		}
		return new User().setName("John Doe");
	}

}
