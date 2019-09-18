package com.springbootprojects.batch.sampleproject.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springbootprojects.batch.sampleproject.entities.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
