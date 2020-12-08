package com.kingshuk.spring.springschedulerasyncevents.eventlisteners;

import com.kingshuk.spring.springschedulerasyncevents.model.EmployeeRecordFetchedEvent;
import com.kingshuk.spring.springschedulerasyncevents.model.dao.EmployeeOracleRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@AllArgsConstructor
@Slf4j
public class OracleDataUpdateListener {

    private final EmployeeOracleRepository employeeOracleRepository;

    @EventListener
    @Async("oracleThreadPoolTaskScheduler")
    @Transactional(transactionManager = "oracleTransactionManager")
    public void addEmployeeData(EmployeeRecordFetchedEvent event){
        employeeOracleRepository.save(event.getEmployee());
    }
}
