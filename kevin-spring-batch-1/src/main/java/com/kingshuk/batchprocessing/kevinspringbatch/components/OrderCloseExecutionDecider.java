package com.kingshuk.batchprocessing.kevinspringbatch.components;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.job.flow.JobExecutionDecider;

public class OrderCloseExecutionDecider implements JobExecutionDecider {
    @Override
    public FlowExecutionStatus decide(JobExecution jobExecution, StepExecution stepExecution) {
        final boolean returnInitiated = Boolean.parseBoolean(jobExecution.getJobParameters().getString("returnInitiated"));
        return new FlowExecutionStatus(returnInitiated?"RETURN_INITIATED":"RETURN_NOT_INITIATED");
    }
}
