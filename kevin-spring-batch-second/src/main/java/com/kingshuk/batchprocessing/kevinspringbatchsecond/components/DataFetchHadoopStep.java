package com.kingshuk.batchprocessing.kevinspringbatchsecond.components;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class DataFetchHadoopStep implements Tasklet {
    private static final Logger LOGGER = LoggerFactory.getLogger(DataFetchHadoopStep.class);

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        LOGGER.info("Data fetched from hadoop");
        return RepeatStatus.FINISHED;
    }
}
