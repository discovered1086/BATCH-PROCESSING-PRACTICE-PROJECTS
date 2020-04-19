package com.springbootprojects.batch.sampleproject;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class SpringBatchSampleProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBatchSampleProjectApplication.class, args);
	}

}
