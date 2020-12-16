package com.kingshuk.spring.springschedulerasyncevents.model.dao;

import com.kingshuk.spring.springschedulerasyncevents.model.entities.EmployeeEntity;

import javax.persistence.EntityManager;

public abstract class AbstractDAORepository {

    public void updateData(EmployeeEntity employeeEntity){
        getEntityManager().persist(EmployeeEntity.builder()
                .emailAddress(employeeEntity.getEmailAddress())
                .firstName(employeeEntity.getFirstName())
                .lastName(employeeEntity.getLastName()).build());
    }

    public abstract EntityManager getEntityManager();
}
