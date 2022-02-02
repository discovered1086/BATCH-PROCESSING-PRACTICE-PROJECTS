package com.kingshuk.batchprocessing.kevinspringbatch.components;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class CloseOrderTask implements Tasklet {
    private static final Logger LOGGER = LoggerFactory.getLogger(CloseOrderTask.class);

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {
        final String date = chunkContext.getStepContext().getJobParameters().get("run.date").toString();
        LOGGER.info("The order has been closed on {}", date);
        return RepeatStatus.FINISHED;
    }
}
