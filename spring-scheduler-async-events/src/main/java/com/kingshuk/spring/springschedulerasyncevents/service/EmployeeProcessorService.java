package com.kingshuk.spring.springschedulerasyncevents.service;

import com.kingshuk.spring.springschedulerasyncevents.model.EmployeeRecordFetchedEvent;
import com.kingshuk.spring.springschedulerasyncevents.model.entities.EmployeeEntity;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmployeeProcessorService {

    private final ApplicationEventPublisher eventPublisher;

    public void processEmployeeData() {
        for (int i = 0; i < 100; i++) {
            EmployeeRecordFetchedEvent recordFetchedEvent =
                    new EmployeeRecordFetchedEvent(EmployeeEntity.builder()
                            .emailAddress("sly.mania" + i + "@gmail.com")
                            .firstName("Kingshuk" + i)
                            .lastName("Mukherjee" + i)
                            .build());

            eventPublisher.publishEvent(recordFetchedEvent);
        }
    }
}
