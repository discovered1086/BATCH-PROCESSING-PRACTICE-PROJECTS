package com.kingshuk.batchprocessing.spring.springquartzscheduler.jobs;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class TheActualJob implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("I'm inside the job called " + context.getJobDetail().getKey());

        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();

        String theMessage = jobDataMap.getString("Kingshuk says");

        System.out.println(Arrays.stream(jobDataMap.getKeys()).findFirst().get() + " " + theMessage);
    }
}
