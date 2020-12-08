package com.kingshuk.spring.springschedulerasyncevents.model.dao;

import com.kingshuk.spring.springschedulerasyncevents.model.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

@Repository
@PersistenceContext(type = PersistenceContextType.TRANSACTION, unitName = "h2EntityManager")
public interface EmployeeH2Repository extends JpaRepository<EmployeeEntity, Long> {
}
