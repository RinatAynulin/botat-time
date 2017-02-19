package com.aynulin.botat.time.config;

import com.aynulin.botat.time.scheduler.AutowiringSpringBeanJobFactory;
import com.aynulin.botat.time.scheduler.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * @Aynulin on 19.02.2017.
 */

@Configuration
@ComponentScan("com.aynulin.botat.time.scheduler")
public class QuartzContext {

    @Autowired
    private org.springframework.context.ApplicationContext applicationContext;

    @Bean
    public JobDetailFactoryBean jobDetailFactoryBean() {
        JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();
        jobDetailFactoryBean.setJobClass(Job.class);
//        jobDetailFactoryBean.setJobDataAsMap(new HashMap<String, Object>() {{
//            put("postService", ))
//        }});
        return jobDetailFactoryBean;
    }

//    @Bean
//    public SimpleTriggerFactoryBean simpleTriggerFactoryBean() {
//        SimpleTriggerFactoryBean simpleTriggerFactoryBean = new SimpleTriggerFactoryBean();
//        simpleTriggerFactoryBean.setJobDetail(jobDetailFactoryBean().getObject());
//        simpleTriggerFactoryBean.setStartDelay(10000);
//        simpleTriggerFactoryBean.setRepeatInterval(60000);
//        return simpleTriggerFactoryBean;
//    }

    @Bean
    public CronTriggerFactoryBean cronTriggerFactoryBean() {
        CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
        cronTriggerFactoryBean.setJobDetail(jobDetailFactoryBean().getObject());
        cronTriggerFactoryBean.setCronExpression("0 0 0 * * ?"); // run everyday at 00hr
        return cronTriggerFactoryBean;
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        schedulerFactoryBean.setTriggers(cronTriggerFactoryBean().getObject());
        AutowiringSpringBeanJobFactory jobFactory = new AutowiringSpringBeanJobFactory();
        jobFactory.setApplicationContext(applicationContext);
        schedulerFactoryBean.setJobFactory(jobFactory);
        return schedulerFactoryBean;
    }
}
