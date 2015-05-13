package com.michir.projects.spring.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.michir.projects.spring.api.IProperties;

@Component
@PropertySource("classpath:/project.properties")
public class MyProperties implements IProperties {

	@Autowired
	private Environment environment;
	
	@Override
	public String getProperty(String key) {
		return environment.getProperty(key);
	}
}
