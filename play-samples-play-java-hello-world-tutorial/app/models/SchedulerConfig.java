package models;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class SchedulerConfig {
    public void runSchedule() throws SchedulerException {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        JobDetail job = JobBuilder.newJob(EmailJob.class)
                .withIdentity("EmailHob", "group1")
                .build();

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("EmailTrigger", "group1")
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(5)
                        .withRepeatCount(0))
                .build();

        scheduler.scheduleJob(job, trigger);
        scheduler.start();
    }
}
