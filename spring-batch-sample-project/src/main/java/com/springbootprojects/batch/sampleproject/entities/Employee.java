package com.springbootprojects.batch.sampleproject.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="EMPLOYEE_BATCH")
public class Employee implements Serializable{

	private static final long serialVersionUID = -1497555110482987046L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "EMPL_ID")
	private long employeeId;

	@Column(name = "FRST_NM")
	private String firstName;

	@Column(name = "LSTNM")
	private String lastName;

	@Column(name = "EML_ADDRSS")
	private String email;
	
	@Column(name = "PHN_NMBR")
	private String phoneNumber;
	

}
