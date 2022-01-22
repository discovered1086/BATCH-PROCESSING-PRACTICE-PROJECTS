package com.kingshuk.batchprocessing.kevinspringbatch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class KevinSpringBatch1Application {

    public static void main(String[] args) {
        SpringApplication.run(KevinSpringBatch1Application.class, args);
    }

}
