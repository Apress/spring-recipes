package com.apress.springrecipes.springbatch.scheduler;

import org.apache.commons.lang.StringUtils;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * This bean launches a {@link org.springframework.batch.core.Job} at a given delay - waiting 10 seconds before
 * attempting a run
 *
 * @author <a href="mailto:josh@joshlong.com">Josh Long</a>
 */
@Component
public class ScheduledMain {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job job;

    public void runRegistrationsJob(Date date) throws Throwable {
        System.out.println(StringUtils.repeat("-", 100));
        System.out.println("Starting job at " + date.toString());
        JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();
        jobParametersBuilder.addDate("date", date);
        jobParametersBuilder.addString("input.file", "registrations");
        JobParameters jobParameters = jobParametersBuilder.toJobParameters();
        JobExecution jobExecution = jobLauncher.run(job, jobParameters);
        System.out.println("jobExecution finished, exit code: " + jobExecution.getExitStatus().getExitCode());
    }

    @Scheduled(fixedDelay = 1000 * 10)
    void runRegistrationsJobOnASchedule() throws Throwable {
        runRegistrationsJob(new Date());
    }

    public static void main(String[] args) throws Throwable {
        ClassPathXmlApplicationContext classPathXmlApplicationContext =
                new ClassPathXmlApplicationContext("scheduled_batch.xml", "solution1.xml");
        classPathXmlApplicationContext.start();
    }
}
