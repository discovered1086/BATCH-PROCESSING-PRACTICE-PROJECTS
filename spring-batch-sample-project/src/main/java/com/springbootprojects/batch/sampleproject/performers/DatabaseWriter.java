package com.springbootprojects.batch.sampleproject.performers;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import com.springbootprojects.batch.sampleproject.entities.Employee;
import com.springbootprojects.batch.sampleproject.repo.EmployeeRepository;

public class DatabaseWriter implements ItemWriter<Employee> {
	
	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public void write(List<? extends Employee> items) throws Exception {
		items.forEach(employeeRepository::save);
	}

}
