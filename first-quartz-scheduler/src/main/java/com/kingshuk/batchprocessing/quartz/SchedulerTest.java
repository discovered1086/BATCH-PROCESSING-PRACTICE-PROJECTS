package com.kingshuk.batchprocessing.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.calendar.HolidayCalendar;

import java.time.*;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.TimeZone;

public class SchedulerTest {

    public static void main(String[] args) throws SchedulerException {
        SchedulerFactory factory = new StdSchedulerFactory();

        Scheduler scheduler = factory.getScheduler();

        JobDetail jobDetail = JobBuilder.newJob(TheActualJob.class)
                .withIdentity("myJob1", "King's Jobs")
                .usingJobData("Kingshuk says", "Hello World")
                .build();

        JobDetail jobDetail2 = JobBuilder.newJob(TheActualCronJob.class)
                .withIdentity("myJob2", "King's Jobs")
                .usingJobData("Kingshuk says", "Hello World my friends...")
                .build();

//        JobDetail jobDetail3 = JobBuilder.newJob(TheActualJob.class)
//                .withIdentity("myJob3", "King's Jobs")
//                .usingJobData("Kingshuk says", "Hello World from third job")
//                .build();


        Trigger myTrigger = TriggerBuilder.newTrigger()
                .withIdentity("myTrigger", "myTriggerGroup")
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(30)
                        .repeatForever()
                        .withMisfireHandlingInstructionFireNow())
                //.forJob(jobDetail)
                .build();

        Trigger myTrigger2 = TriggerBuilder.newTrigger()
                .withIdentity("myTrigger2", "myTriggerGroup")
                .usingJobData("SaveStatus", false)
                .startNow()
                //.forJob(jobDetail2)
                .withSchedule(CronScheduleBuilder.atHourAndMinuteOnGivenDaysOfWeek(11, 30, DateBuilder.SUNDAY))
                .build();

        HolidayCalendar calendar = new HolidayCalendar();
        calendar.setTimeZone(TimeZone.getTimeZone(ZoneId.of("America/Chicago")));
        calendar.addExcludedDate(new Date());

        scheduler.addCalendar("myCal", calendar, false, true);

        Trigger myTrigger3 = TriggerBuilder.newTrigger()
                .withIdentity("myTrigger3", "myTriggerGroup")
                .usingJobData("SaveStatus", false)
                .startNow()
                //.forJob(jobDetail3)
                .withSchedule(CronScheduleBuilder.dailyAtHourAndMinute(12, 18))
                .modifiedByCalendar("myCal")
                .build();


        scheduler.scheduleJob(jobDetail, myTrigger);
        scheduler.scheduleJob(jobDetail2, new HashSet<>(Arrays.asList(myTrigger2, myTrigger3)), false);
        //scheduler.scheduleJob(jobDetail3, myTrigger3);

//        scheduler.scheduleJob(myTrigger);
//        scheduler.scheduleJob(myTrigger2);
//        scheduler.scheduleJob(myTrigger3);

    }
}
