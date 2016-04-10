package com.core.util;

import org.quartz.*;
import org.quartz.impl.triggers.CronTriggerImpl;

import java.text.ParseException;

import static org.quartz.JobBuilder.newJob;

/**
 * Created by stereomatrix on 2016/4/10.
 */
public class QuartzSchedulerTest {
    public static void main(String[] args) {
        JobDetail jobDetail = newJob(JobOne.class).withIdentity("job1", "group1").build();
        JobDetail jobDetail2 = newJob(JobTwo.class).withIdentity("job2", "group1").build();
        Trigger trigger = null;
        Trigger trigger2 = null;
        try {
            trigger = new CronTriggerImpl("trigger1", "group1", "*/3 * * * * ?");
            trigger2 = new CronTriggerImpl("trigger2", "group1", "*/3 * * * * ?");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        QuartzScheduler.schedule(jobDetail, trigger);
        QuartzScheduler.schedule(jobDetail2, trigger2);
    }

    public static class JobOne implements Job {

        public void execute(JobExecutionContext context) throws JobExecutionException {
            System.out.println("Run Job One...");
        }
    }

    public static class JobTwo implements Job {

        public void execute(JobExecutionContext context) throws JobExecutionException {
            System.out.println("Run Job Two...");
        }
    }
}
