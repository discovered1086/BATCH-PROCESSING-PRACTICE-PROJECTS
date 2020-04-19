package com.springbootprojects.batch.sampleproject.listener;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyJobListener implements JobExecutionListener {

	@Override
	public void beforeJob(JobExecution jobExecution) {
		log.info(jobExecution.getJobId() + " Job started....");
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		log.info(jobExecution.getJobId() + "Job completed with status - " + jobExecution.getStatus());
	}

}
