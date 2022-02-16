package com.kingshuk.batchprocessing.kevinspringbatchsecond.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

public class PlantMappingStepExecutionListener implements StepExecutionListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(PlantMappingStepExecutionListener.class);

    @Override
    public void beforeStep(StepExecution stepExecution) {
        LOGGER.info("Before executing the step: {}", stepExecution.getStepName());
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        if("FAILED".equalsIgnoreCase(stepExecution.getExitStatus().getExitCode())){
            LOGGER.info("Logic to get plant mapping data from a different data source has been executed");
        }
        return ExitStatus.COMPLETED;
    }
}
