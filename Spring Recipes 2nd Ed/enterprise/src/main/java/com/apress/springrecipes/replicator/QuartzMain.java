package com.apress.springrecipes.replicator;

import org.springframework.context.support.ClassPathXmlApplicationContext;


public class QuartzMain {
    static public void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans-quartz.xml");
        applicationContext.start();
    }
}
