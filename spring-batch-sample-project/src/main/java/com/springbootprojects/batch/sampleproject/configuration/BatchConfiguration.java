package com.springbootprojects.batch.sampleproject.configuration;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.springbootprojects.batch.sampleproject.entities.Employee;

@Configuration
public class BatchConfiguration {

	// Configuring the reader
	@Bean
	public ItemReader<Employee> itemReader() {
		FlatFileItemReader<Employee> flatFileItemReader = new FlatFileItemReader<>();
		flatFileItemReader.setResource(new ClassPathResource("employees.csv"));

		// Next we need to set a line mapper
		DefaultLineMapper<Employee> lineMapper = new DefaultLineMapper<>();

		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
		lineTokenizer.setNames("firstName", "lastName", "email", "phoneNumber");

		BeanWrapperFieldSetMapper<Employee> fieldSetMapper = new BeanWrapperFieldSetMapper<>();

		fieldSetMapper.setTargetType(Employee.class);

		lineMapper.setFieldSetMapper(fieldSetMapper);

		lineMapper.setLineTokenizer(lineTokenizer);

		flatFileItemReader.setLineMapper(lineMapper);

		return flatFileItemReader;
	}
}
