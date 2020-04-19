package com.springbootprojects.batch.scheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringSchedulerPracticeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSchedulerPracticeApplication.class, args);
	}

}
