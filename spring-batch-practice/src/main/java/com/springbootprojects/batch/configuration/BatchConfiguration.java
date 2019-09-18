package com.springbootprojects.batch.configuration;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.springbootprojects.batch.listener.MyJobListener;
import com.springbootprojects.batch.performers.ItemProcessorImpl;
import com.springbootprojects.batch.performers.ItemReaderImpl;
import com.springbootprojects.batch.performers.ItemWriterImpl;

@Configuration
public class BatchConfiguration {
	
	@Autowired 
	private StepBuilderFactory stepBuilderFactory;

	@Bean(name = "myBatchStep")
	@Qualifier(value = "myBatchStep")
	public Step step() {
		return stepBuilderFactory
				.get("step1")
				.<String, String>chunk(2)
				.reader(itemReaderImpl())
				.writer(itemWriterImpl())
				.processor(itemProcessorImpl())
				.build();
	}
	
	@Bean(name = "myJob")
	@Qualifier(value = "myJob")
	public Job myJob(@Autowired JobBuilderFactory jobBuilderFactory) {
		return jobBuilderFactory
				.get("myFirstJob")
				.incrementer(new RunIdIncrementer())
				.listener(myJobListener())
				.start(step())
				.build();
								
	}

	@Bean(name = "myItemReader")
	@Qualifier(value = "myItemReader")
	public ItemReaderImpl itemReaderImpl() {
		return new ItemReaderImpl();
	}

	@Bean(name = "myItemProcessor")
	@Qualifier(value = "myItemProcessor")
	public ItemProcessorImpl itemProcessorImpl() {
		return new ItemProcessorImpl();
	}

	@Bean(name = "myItemWriter")
	@Qualifier(value = "myItemWriter")
	public ItemWriterImpl itemWriterImpl() {
		return new ItemWriterImpl();
	}

	@Bean(name = "myJobListener")
	@Qualifier(value = "myJobListener")
	public MyJobListener myJobListener() {
		return new MyJobListener();
	}
}
