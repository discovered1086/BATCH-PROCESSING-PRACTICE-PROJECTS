package com.springbootprojects.batch.sampleproject.performers;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

import org.springframework.batch.item.ItemProcessor;

import com.springbootprojects.batch.sampleproject.entities.Employee;

public class ItemProcessorImpl implements ItemProcessor<Employee, Employee> {

	@Override
	public Employee process(Employee item) throws Exception {
		if (item.getAge() == null) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

			LocalDate dob = LocalDate.parse(item.getDob(), formatter);

			Period age = Period.between(dob, LocalDate.now());

			item.setAge(String.valueOf(age.getYears()).concat(" years"));
		}
		return item;
	}

}
