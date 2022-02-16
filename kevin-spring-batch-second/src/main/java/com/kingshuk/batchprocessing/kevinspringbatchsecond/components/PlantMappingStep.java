package com.kingshuk.batchprocessing.kevinspringbatchsecond.components;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class PlantMappingStep implements Tasklet {
    private static final Logger LOGGER = LoggerFactory.getLogger(PlantMappingStep.class);

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {
        boolean fetchSuccessful = Boolean.parseBoolean(chunkContext.getStepContext().getJobParameters()
                .get("plantMappingFetchSuccessful").toString());
        if (fetchSuccessful) {
            LOGGER.info("Fetched plant mappings from the database");
            return RepeatStatus.FINISHED;
        } else {
            throw new RuntimeException("Step execution failed");
        }
    }
}
