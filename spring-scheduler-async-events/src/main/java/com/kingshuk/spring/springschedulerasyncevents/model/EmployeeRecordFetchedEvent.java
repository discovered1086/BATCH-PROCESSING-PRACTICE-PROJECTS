package com.kingshuk.spring.springschedulerasyncevents.model;

import com.kingshuk.spring.springschedulerasyncevents.model.entities.EmployeeEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRecordFetchedEvent {

    private EmployeeEntity employee;
}
