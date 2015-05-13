package com.michir.projects.spring.services;

import javax.sql.DataSource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.michir.projects.spring.api.IUsersRepository;
import com.michir.projects.spring.api.User;

@Repository("dbUsersRepository")
@Scope("singleton")
public class DataUsersRepository implements IUsersRepository, InitializingBean {

	@Override
	public User findByName(String name) {
		try {
			return jdbcTemplate.queryForObject("select * from users where name = ?", User.class, name);
		} catch (EmptyResultDataAccessException e) {
			return new User().setName("John Doe");
		}
	}

	@Autowired
	private DataSource dataSource;

	private JdbcTemplate jdbcTemplate;

	@Override
	public void afterPropertiesSet() throws Exception {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
}
