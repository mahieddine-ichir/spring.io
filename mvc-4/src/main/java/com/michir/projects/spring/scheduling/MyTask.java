package com.michir.projects.spring.scheduling;

import java.util.logging.Logger;

import org.springframework.scheduling.annotation.Scheduled;

public class MyTask {

	@Scheduled(cron="*/15 * * * * *")
	void doWork() {
		Logger.getLogger(MyTask.class.getName()).info("++++ lauching task");
	}
}
