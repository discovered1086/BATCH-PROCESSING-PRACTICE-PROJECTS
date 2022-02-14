package com.kingshuk.batchprocessing.kevinspringbatch.config;

import com.kingshuk.batchprocessing.kevinspringbatch.components.*;
import lombok.AllArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.flow.JobExecutionDecider;
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

    @Bean
    public Step refundCustomerStep() {
        return stepBuilderFactory.get("refundCustomerStep")
                .tasklet(new RefundCustomerTask()).build();
    }

    @Bean
    public Step closeOrderStep() {
        return stepBuilderFactory.get("closeOrderStep")
                .tasklet(new CloseOrderTask()).build();
    }

    @Bean
    public Step returnItemStep() {
        return stepBuilderFactory.get("returnItemStep")
                .tasklet(new ProcessReturnTask()).build();
    }

    @Bean
    public JobExecutionDecider decider() {
        return new OrderCloseExecutionDecider();
    }

//    //Here we define the actual object representation of our batch Job
//    @Bean
//    public Job myPackageDelivery() {
//        return jobBuilderFactory.get("myPackageDelivery")
//                .start(packageItemStep())
//                .next(shipItemStep())
//                .next(deliverItemStep()).build();
//    }

    //Here we define the actual object representation of our batch Job
    //Conditional job flow
    @Bean
    public Job myPackageDelivery() {
        return jobBuilderFactory.get("myPackageDelivery")
                .start(packageItemStep())
                .next(shipItemStep())
                    .on("FAILED").to(refundCustomerStep())
                .from(shipItemStep())
                    .on("*").to(deliverItemStep())
                .from(deliverItemStep())
                    .on("FAILED").fail()
                .from(deliverItemStep())
                    .on("*").to(decider())
                        .on("RETURN_INITIATED").to(returnItemStep())
                                    .next(refundCustomerStep())
                                    .next(closeOrderStep())
                    .from(decider())
                        .on("RETURN_NOT_INITIATED").to(closeOrderStep())
                .end().build();
    }

}
