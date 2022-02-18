package com.kingshuk.batchprocessing.debtpaymentbatch.config;

import com.kingshuk.batchprocessing.debtpaymentbatch.components.DebtPaymentItemReader;
import com.kingshuk.batchprocessing.debtpaymentbatch.components.DebtPaymentItemWriter;
import com.kingshuk.batchprocessing.debtpaymentbatch.model.DebtPaymentDTO;
import lombok.AllArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

@Configuration
@EnableBatchProcessing
@AllArgsConstructor
public class BatchConfig {

    private final JobBuilderFactory jobBuilderFactory;

    private final StepBuilderFactory stepBuilderFactory;

    private final DebtPaymentItemReader itemReader;

    private final DebtPaymentItemWriter itemWriter;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder.setConnectTimeout(Duration.ofMinutes(1))
                .setReadTimeout(Duration.ofSeconds(15))
                .build();
    }

    @Bean
    public Job debtPaymentJob() {
        return jobBuilderFactory.get("debtPaymentJob")
                .start(dataUploadStep()).build();
    }

    @Bean
    public Step dataUploadStep() {
        return stepBuilderFactory.get("dataUploadStep")
                .<DebtPaymentDTO, DebtPaymentDTO>chunk(2)
                .reader(itemReader)
                .writer(itemWriter)
                .build();
    }
}
