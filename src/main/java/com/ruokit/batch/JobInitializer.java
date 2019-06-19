package com.ruokit.batch;

import static org.quartz.DateBuilder.evenMinuteDate;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;
import java.util.Date;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JobInitializer {

  private static final Logger logger = LoggerFactory.getLogger(JobInitializer.class);

  public void run() throws Exception {

    SchedulerFactory sf = new StdSchedulerFactory();
    Scheduler sched = sf.getScheduler();

    Date runTime = evenMinuteDate(new Date());
    JobDetail job = newJob(HelloJob.class).withIdentity("job1", "group1").build();

    Trigger trigger = newTrigger().withIdentity("trigger1", "group1").startAt(runTime).build();

    sched.scheduleJob(job, trigger);
    sched.start();
  }

  public static void main(String[] args) throws Exception {

    JobInitializer example = new JobInitializer();
    example.run();

  }

}
