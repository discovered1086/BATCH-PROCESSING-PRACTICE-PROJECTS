package com.kingshuk.batchprocessing.spring.springquartzscheduler.jobs;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class TheActualCronJob implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("I'm inside the job called " + context.getJobDetail().getKey());

        System.out.println(context.getScheduledFireTime());

//        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
//
//        JobDataMap jobDataMap1 = context.getTrigger().getJobDataMap();

        //Instead of separately getting the JobDataMap from the JobDetail
        //And then from the Trigger, we can get a combined version with one statement
        JobDataMap mergedJobDataMap = context.getMergedJobDataMap();

        mergedJobDataMap.keySet().stream()
                .map(key -> mergedJobDataMap.get(key))
                .collect(Collectors.toSet())
                .forEach(System.out::println);

    }
}
