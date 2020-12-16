package com.kingshuk.spring.springschedulerasyncevents.model.dao;

import com.kingshuk.spring.springschedulerasyncevents.model.entities.EmployeeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

@Repository
public class EmployeeH2Repository extends AbstractDAORepository{

    @Autowired
    @PersistenceContext(type = PersistenceContextType.TRANSACTION, unitName = "H2_PERS_UNIT")
    private EntityManager entityManager;

    //@Transactional(transactionManager = "oracleTransactionManager")
    public void save(EmployeeEntity employeeEntity){
        updateData(employeeEntity);
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }
}
