package com.kingshuk.batchprocessing.kevinspringbatch.config;

import com.kingshuk.batchprocessing.kevinspringbatch.components.DeliverItemTask;
import com.kingshuk.batchprocessing.kevinspringbatch.components.PackageItemTask;
import com.kingshuk.batchprocessing.kevinspringbatch.components.ShipItemTask;
import lombok.AllArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class BatchConfig {

    private final JobBuilderFactory jobBuilderFactory;

    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step packageItemStep() {
        return stepBuilderFactory.get("packageItemStep")
                .tasklet(new PackageItemTask()).build();
    }

    @Bean
    public Step shipItemStep() {
        return stepBuilderFactory.get("shipItemStep")
                .tasklet(new ShipItemTask()).build();
    }

    @Bean
    public Step deliverItemStep() {
        return stepBuilderFactory.get("deliverItemStep")
                .tasklet(new DeliverItemTask()).build();
    }

    //Here we define the actual object representation of our batch Job
    @Bean
    public Job myPackageDelivery() {
        return jobBuilderFactory.get("myPackageDelivery")
                .start(packageItemStep())
                .next(shipItemStep())
                .next(deliverItemStep()).build();
    }

}