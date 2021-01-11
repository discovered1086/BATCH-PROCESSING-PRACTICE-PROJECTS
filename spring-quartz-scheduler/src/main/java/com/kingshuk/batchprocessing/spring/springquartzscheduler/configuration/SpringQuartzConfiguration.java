package com.kingshuk.batchprocessing.spring.springquartzscheduler.configuration;

import com.kingshuk.batchprocessing.spring.springquartzscheduler.jobs.TheActualCronJob;
import com.kingshuk.batchprocessing.spring.springquartzscheduler.jobs.TheActualJob;
import org.quartz.JobDetail;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.quartz.QuartzDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.quartz.*;

import javax.sql.DataSource;

@Configuration
@EnableScheduling
public class SpringQuartzConfiguration {

    @Autowired
    private ApplicationContext applicationContext;

    @Bean(name = "myJob")
    public JobDetailFactoryBean jobDetail1(){
        JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();
        jobDetailFactoryBean.setDescription("The regular Job");
        jobDetailFactoryBean.setGroup("king's Jobs");
        jobDetailFactoryBean.setDurability(true);
        jobDetailFactoryBean.setJobClass(TheActualJob.class);
        jobDetailFactoryBean.getJobDataMap().put("Kingshuk Says", "Hello World my friends...");
        return jobDetailFactoryBean;
    }

    @Bean(name = "myJob2")
    public JobDetailFactoryBean jobDetail2(){
        JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();
        jobDetailFactoryBean.setGroup("king's Jobs");
        jobDetailFactoryBean.setDescription("The Cron Job");
        jobDetailFactoryBean.setJobClass(TheActualCronJob.class);
        jobDetailFactoryBean.setDurability(true);
        jobDetailFactoryBean.getJobDataMap().put("Kingshuk Says", "Hello World my friends...");
        return jobDetailFactoryBean;
    }

    @Bean(name = "myTrigger1")
    public SimpleTriggerFactoryBean trigger1(@Qualifier("myJob") JobDetail jobDetail){
        SimpleTriggerFactoryBean triggerFactoryBean = new SimpleTriggerFactoryBean();
        triggerFactoryBean.setGroup("myTriggerGroup");
        triggerFactoryBean.setJobDetail(jobDetail);
        triggerFactoryBean.setRepeatInterval(60*1000L);
        triggerFactoryBean.setRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY);
        return triggerFactoryBean;
    }

    @Bean(name = "myTrigger2")
    public CronTriggerFactoryBean trigger2(@Qualifier("myJob2") JobDetail jobDetail){
        CronTriggerFactoryBean triggerFactoryBean = new CronTriggerFactoryBean();
        triggerFactoryBean.setGroup("myTriggerGroup");
        triggerFactoryBean.setJobDetail(jobDetail);
        triggerFactoryBean.setCronExpression("0/30 * * * * ?");
        return triggerFactoryBean;
    }

    @Bean
    @QuartzDataSource
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource quartzDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public SchedulerFactoryBean scheduler(
            @Qualifier("myTrigger1") Trigger trigger,
            @Qualifier("myTrigger2") Trigger trigger2,
            @Qualifier("myJob") JobDetail job,
            @Qualifier("myJob2") JobDetail job2, DataSource quartzDataSource) {

        SchedulerFactoryBean schedulerFactory = new SchedulerFactoryBean();
        schedulerFactory.setConfigLocation(new ClassPathResource("quartz.properties"));

        schedulerFactory.setJobFactory(springBeanJobFactory());
        schedulerFactory.setJobDetails(job, job2);
        schedulerFactory.setTriggers(trigger, trigger2);

        // Comment the following line to use the default Quartz job store.
        schedulerFactory.setDataSource(quartzDataSource);

        return schedulerFactory;
    }

    @Bean
    public SpringBeanJobFactory springBeanJobFactory() {
        AutoWiringSpringBeanJobFactory jobFactory = new AutoWiringSpringBeanJobFactory();
        jobFactory.setApplicationContext(applicationContext);
        return jobFactory;
    }
}
