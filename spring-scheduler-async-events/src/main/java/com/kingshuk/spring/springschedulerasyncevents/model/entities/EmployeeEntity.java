package com.kingshuk.spring.springschedulerasyncevents.model.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "EMPLOYEE_SCHEDULER")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "scheduler_seq")
    @SequenceGenerator(name = "scheduler_seq", sequenceName = "scheduler_seq", allocationSize = 1)
    @Column(name = "EMPLOYEE_ID")
    private long employeeId;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "EMAIL_ADDRESS")
    private String emailAddress;


}
