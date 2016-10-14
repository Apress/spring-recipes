package com.apress.springrecipes.replicator;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.management.MBeanServerConnection;
import javax.management.ObjectName;


public class Client {
    public static void main(String[] args) throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans-client.xml");

        MBeanServerConnection mbeanServerConnection = (MBeanServerConnection) context.getBean("mbeanServerConnection");

        ObjectName mbeanName = new ObjectName("bean:name=documentReplicator,type=FileReplicatorImpl");

        mbeanServerConnection.addNotificationListener(mbeanName, new ReplicationNotificationListener(), null, null);

        FileReplicator fileReplicatorProxy = (FileReplicator) context.getBean("fileReplicatorProxy");

        String srcDir = fileReplicatorProxy.getSrcDir();
        fileReplicatorProxy.setDestDir(srcDir + "_1");
        fileReplicatorProxy.replicate();
    }
}
