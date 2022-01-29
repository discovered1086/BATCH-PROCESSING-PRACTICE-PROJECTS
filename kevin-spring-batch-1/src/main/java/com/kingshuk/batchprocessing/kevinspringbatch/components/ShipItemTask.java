package com.kingshuk.batchprocessing.kevinspringbatch.components;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class ShipItemTask implements Tasklet {
    private static final Logger LOGGER = LoggerFactory.getLogger(ShipItemTask.class);

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {
        final String item = chunkContext.getStepContext().getJobParameters().get("item").toString();
        final String date = chunkContext.getStepContext().getJobParameters().get("run.date").toString();
        LOGGER.info("All parameters {} ", chunkContext.getStepContext().getJobParameters());
        LOGGER.info("The {} has been shipped on {}", item, date);
        return RepeatStatus.FINISHED;
    }
}
