package com.apress.springrecipes.replicator;

import org.springframework.context.support.ClassPathXmlApplicationContext;


public class JMXMain {
    public static void main(String[] args) throws Throwable {
        ClassPathXmlApplicationContext services = new ClassPathXmlApplicationContext("beans-jmx.xml");
        services.start();
        System.out.println("Started JMX instance...");

        ClassPathXmlApplicationContext client = new ClassPathXmlApplicationContext("beans-jmx-client.xml");
        client.start();
        System.out.println("Started JMX client...");

        FileReplicator fileReplicator = client.getBean("fileReplicatorProxy", FileReplicator.class);
        fileReplicator.replicate();
    }
}
