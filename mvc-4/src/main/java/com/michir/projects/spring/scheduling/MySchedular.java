package com.michir.projects.spring.scheduling;

import java.util.logging.Logger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class MySchedular {
	
	@Scheduled(fixedRate=10000)
	public void doWork() {
		Logger.getLogger(MySchedular.class.getName()).info("++++ lauching scheduled");
	}

	@Bean
	public MyTask task() {
		return new MyTask();
	}

}
