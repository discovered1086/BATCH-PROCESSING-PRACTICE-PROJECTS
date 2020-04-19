package com.springbootprojects.batch.sampleproject.configuration;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.springbootprojects.batch.sampleproject.entities.Employee;
import com.springbootprojects.batch.sampleproject.listener.MyJobListener;
import com.springbootprojects.batch.sampleproject.performers.DatabaseWriter;
import com.springbootprojects.batch.sampleproject.performers.ItemProcessorImpl;

@Configuration
public class BatchConfiguration {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Bean(name = "myJob")
	@Qualifier(value = "myJob")
	public Job myJob(@Autowired JobBuilderFactory jobBuilderFactory) {
		return jobBuilderFactory.get("employeeJob").incrementer(new RunIdIncrementer()).listener(myJobListener())
				.start(step()).build();
	}

	@Bean(name = "myBatchStep")
	@Qualifier(value = "myBatchStep")
	public Step step() {
		return stepBuilderFactory.get("Step1").<Employee, Employee>chunk(2).reader(itemReader()).writer(itemWriter())
				.processor(employeeProcessor()).build();
	}

	// Configuring the reader
	@Bean
	public ItemReader<Employee> itemReader() {
		FlatFileItemReader<Employee> flatFileItemReader = new FlatFileItemReader<>();
		flatFileItemReader.setResource(new ClassPathResource("employees.csv"));

		// Next we need to set a line mapper
		DefaultLineMapper<Employee> lineMapper = new DefaultLineMapper<>();

		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
		lineTokenizer.setNames("firstName", "lastName", "email", "phoneNumber", "dob");

		BeanWrapperFieldSetMapper<Employee> fieldSetMapper = new BeanWrapperFieldSetMapper<>();

		fieldSetMapper.setTargetType(Employee.class);

		lineMapper.setFieldSetMapper(fieldSetMapper);

		lineMapper.setLineTokenizer(lineTokenizer);

		flatFileItemReader.setLineMapper(lineMapper);

		return flatFileItemReader;
	}

	@Bean
	public ItemProcessorImpl employeeProcessor() {
		return new ItemProcessorImpl();
	}

	@Bean
	public ItemWriter<Employee> itemWriter() {
		return new DatabaseWriter();
	}

	@Bean(name = "myJobListener")
	@Qualifier(value = "myJobListener")
	public MyJobListener myJobListener() {
		return new MyJobListener();
	}

}
