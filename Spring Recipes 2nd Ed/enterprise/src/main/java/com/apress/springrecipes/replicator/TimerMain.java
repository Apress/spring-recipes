package com.apress.springrecipes.replicator;

import org.springframework.context.support.ClassPathXmlApplicationContext;


public class TimerMain {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans-replicator-scheduler.xml");
        applicationContext.start();
    }
}
