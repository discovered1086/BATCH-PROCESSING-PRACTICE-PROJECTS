package com.kingshuk.spring.springschedulerasyncevents.model.dao;

import com.kingshuk.spring.springschedulerasyncevents.model.entities.EmployeeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

@Repository
public class EmployeeOracleRepository  {

    @Autowired
    @PersistenceContext(type = PersistenceContextType.TRANSACTION, unitName = "ORACLE_PERS_UNIT")
    private EntityManager entityManager;

    //@Transactional(transactionManager = "oracleTransactionManager")
    public void save(EmployeeEntity employeeEntity){
        entityManager.persist(EmployeeEntity.builder()
                .emailAddress(employeeEntity.getEmailAddress())
                .firstName(employeeEntity.getFirstName())
                .lastName(employeeEntity.getLastName()).build());
    }
}
