package com.kingshuk.spring.springschedulerasyncevents.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@Configuration
@EnableAsync
@EnableScheduling
@ComponentScan(basePackages = "com.kingshuk.spring.springschedulerasyncevents")
public class ApplicationConfiguration {

    @Bean(name = "h2ThreadPoolTaskScheduler")
    public ThreadPoolTaskScheduler h2ThreadPoolTaskScheduler(){
        ThreadPoolTaskScheduler threadPoolTaskScheduler
                = new ThreadPoolTaskScheduler();
        threadPoolTaskScheduler.setPoolSize(10);
        threadPoolTaskScheduler.setThreadNamePrefix(
                "h2ThreadPoolTaskScheduler");
        return threadPoolTaskScheduler;
    }

    @Bean(name = "oracleThreadPoolTaskScheduler")
    public ThreadPoolTaskScheduler oracleThreadPoolTaskScheduler(){
        ThreadPoolTaskScheduler threadPoolTaskScheduler
                = new ThreadPoolTaskScheduler();
        threadPoolTaskScheduler.setPoolSize(10);
        threadPoolTaskScheduler.setThreadNamePrefix(
                "oracleThreadPoolTaskScheduler");
        return threadPoolTaskScheduler;
    }
}
