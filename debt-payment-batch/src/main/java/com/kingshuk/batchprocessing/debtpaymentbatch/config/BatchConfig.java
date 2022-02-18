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
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
@AllArgsConstructor
public class BatchConfig {

    private final JobBuilderFactory jobBuilderFactory;

    private final StepBuilderFactory stepBuilderFactory;

    private final DebtPaymentItemReader itemReader;

    private final DebtPaymentItemWriter itemWriter;

    @Bean
    public Job debtPaymentJob(){
        return jobBuilderFactory.get("debtPaymentJob")
                .start(dataUploadStep()).build();
    }

    @Bean
    public Step dataUploadStep() {
        return stepBuilderFactory.get("dataUploadStep")
                .<DebtPaymentDTO,DebtPaymentDTO>chunk(2)
                .reader(itemReader)
                .writer(itemWriter)
                .build();
    }
}
