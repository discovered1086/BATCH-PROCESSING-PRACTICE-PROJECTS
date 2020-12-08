package com.kingshuk.spring.springschedulerasyncevents.eventlisteners;

import com.kingshuk.spring.springschedulerasyncevents.model.EmployeeRecordFetchedEvent;
import com.kingshuk.spring.springschedulerasyncevents.model.dao.EmployeeH2Repository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@AllArgsConstructor
@Slf4j
public class H2DataUpdateListener {

    private final EmployeeH2Repository employeeRepository;

    @EventListener
    @Async("h2ThreadPoolTaskScheduler")
    @Transactional(transactionManager = "h2TransactionManager")
    public void addEmployeeData(EmployeeRecordFetchedEvent event) {
        employeeRepository.save(event.getEmployee());
        log.info("Employee added in H2");
    }
}
