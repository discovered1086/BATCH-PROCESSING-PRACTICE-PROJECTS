package com.kingshuk.batchprocessing.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.calendar.HolidayCalendar;

import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.TimeZone;

public class SchedulerCronTest {

    public static void main(String[] args) throws SchedulerException {
        SchedulerFactory factory = new StdSchedulerFactory();

        Scheduler scheduler = factory.getScheduler();

        JobDetail jobDetail2 = JobBuilder.newJob(TheActualCronJob.class)
                .withIdentity("myJob2", "King's Jobs")
                .usingJobData("Kingshuk says", "Hello World my friends...")
                .build();

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(new TriggerKey("myTriggerCron", "myTriggerGroup"))
                .withSchedule(CronScheduleBuilder.cronSchedule("0 52 12 ? * SUN")) //Every Sunday at 12:55 PM
                .build();

        scheduler.scheduleJob(jobDetail2, trigger);

        scheduler.start();

    }
}
