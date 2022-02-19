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
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.List;

@Configuration
@EnableBatchProcessing
@AllArgsConstructor
public class BatchConfig {

    private final JobBuilderFactory jobBuilderFactory;

    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public ItemReader<DebtPaymentDTO> itemReader(){
        return new DebtPaymentItemReader();
    }

    @Bean
    public ItemWriter<DebtPaymentDTO> itemWriter(){
        return new DebtPaymentItemWriter();
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder.setConnectTimeout(Duration.ofMinutes(1))
                .setReadTimeout(Duration.ofSeconds(15))
                .build();
    }

    @Bean
    public Job debtPaymentJob() {
        return jobBuilderFactory.get("debtPaymentJob")
                .incrementer(new RunIdIncrementer())
                .start(dataUploadStep()).build();
    }

    @Bean
    public Step dataUploadStep() {
        return stepBuilderFactory.get("dataUploadStep")
                .<DebtPaymentDTO, DebtPaymentDTO>chunk(1)
                .reader(itemReader())
                .writer(itemWriter())
                .build();
    }
}
