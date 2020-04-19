package com.springbootprojects.batch.scheduler;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ScheduledTasks {

	DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_TIME;

	@Scheduled(fixedRate = 5000)
	public void reportCurrentTime() {
		log.info("The time is now {}", LocalTime.now().format(formatter));
	}
}
