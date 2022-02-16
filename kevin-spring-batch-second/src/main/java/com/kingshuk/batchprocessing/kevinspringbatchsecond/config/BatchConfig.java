package com.kingshuk.batchprocessing.kevinspringbatchsecond.config;

import com.kingshuk.batchprocessing.kevinspringbatchsecond.components.*;
import com.kingshuk.batchprocessing.kevinspringbatchsecond.listeners.PlantMappingStepExecutionListener;
import lombok.AllArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.job.flow.support.SimpleFlow;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class BatchConfig {

    private final StepBuilderFactory stepBuilderFactory;

    private final JobBuilderFactory jobBuilderFactory;

    @Bean
    public Step getProgramsStep() {
        return stepBuilderFactory.get("getProgramStep")
                .tasklet(new ProgramFetchStep()).build();
    }

    @Bean
    public Step getPlantMappingStep() {
        return stepBuilderFactory.get("getPlantMappingStep")
                .listener(new PlantMappingStepExecutionListener())
                .tasklet(new PlantMappingStep()).build();
    }

    @Bean
    public Step getHadoopDataStep() {
        return stepBuilderFactory.get("getHadoopDataStep")
                .tasklet(new DataFetchHadoopStep()).build();
    }

    @Bean
    public Step insertInHadoopStep() {
        return stepBuilderFactory.get("insertInHadoopStep")
                .tasklet(new InsertInHadoopStep()).build();
    }

    @Bean
    public Step insertInSqlStep() {
        return stepBuilderFactory.get("insertInSqlStep")
                .tasklet(new InsertInSqlStep()).build();
    }

    @Bean
    public Flow firstThreeSteps() {
        return new FlowBuilder<SimpleFlow>("HadoopVinFetch")
                .start(getProgramsStep())
                .next(getPlantMappingStep())
                .next(getHadoopDataStep()).build();
    }

    @Bean
    public Job vinLoadJob() {
        return jobBuilderFactory.get("VinLoadJob")
                .start(firstThreeSteps())
                .next(insertInHadoopStep()).end()
                .build();
    }

    @Bean
    public Job authorizationUpdateJob() {
        return jobBuilderFactory.get("AuthorizationUpdateJob")
                .start(firstThreeSteps())
                .next(insertInSqlStep()).end()
                .build();
    }
}
