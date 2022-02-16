package com.kingshuk.batchprocessing.kevinspringbatchsecond;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class KevinSpringBatchSecondApplication {

    public static void main(String[] args) {
        SpringApplication.run(KevinSpringBatchSecondApplication.class, args);
    }

}
