package com.apress.springrecipes.springbatch.solution1;

import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

public class Main {
    public static void main(String[] args) throws Throwable {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext(
            "batch.xml", "solution3.xml");
        classPathXmlApplicationContext.start();

        JobLauncher jobLauncher = (JobLauncher) classPathXmlApplicationContext.getBean("jobLauncher");
        Job job = (Job) classPathXmlApplicationContext.getBean("insertIntoDbFromCsvJob");
        JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();
        jobParametersBuilder.addDate("date", new Date());
        jobParametersBuilder.addString("input.file", "registrations");
        JobParameters jobParameters =jobParametersBuilder.toJobParameters();
        JobExecution jobExecution = jobLauncher.run(job, jobParameters);
        BatchStatus batchStatus = jobExecution.getStatus();

        while(batchStatus.isRunning()) {
            System.out.println( "Still running...");
            Thread.sleep( 1000 );
        }
        System.out.println( "Exit status: "+ jobExecution.getExitStatus().getExitCode());

        JobInstance jobInstance = jobExecution.getJobInstance();
        System.out.println( "job instance Id: "+ jobInstance.getId());


    }
}
