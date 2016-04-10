package com.core.util;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

/**
 * Created by stereomatrix on 2016/4/10.
 */
public class QuartzScheduler {
    private static Scheduler scheduler = null;
    public synchronized static void schedule(JobDetail job, Trigger trigger) {
        try {
            if(scheduler == null) {
                scheduler = new StdSchedulerFactory().getScheduler();
            }
            scheduler.scheduleJob(job, trigger);
            scheduler.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
