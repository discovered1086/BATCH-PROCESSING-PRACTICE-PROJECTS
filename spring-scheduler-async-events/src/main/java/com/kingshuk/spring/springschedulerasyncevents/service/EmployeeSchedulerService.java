package com.kingshuk.spring.springschedulerasyncevents.service;

import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmployeeSchedulerService {

    private final EmployeeProcessorService processorService;

    @Scheduled(fixedDelay = 100000)
    public void addEmployee(){
        processorService.processEmployeeData();
    }
}
