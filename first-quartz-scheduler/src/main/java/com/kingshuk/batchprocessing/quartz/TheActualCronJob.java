package com.kingshuk.batchprocessing.quartz;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Arrays;

public class TheActualCronJob implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("I'm inside the job called " + context.getJobDetail().getKey());

        System.out.println(context.getScheduledFireTime());
    }
}
