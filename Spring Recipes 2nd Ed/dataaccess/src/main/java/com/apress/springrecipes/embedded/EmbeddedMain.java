package com.apress.springrecipes.embedded;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;

/**
 * Demonstrates using the embedded database functionality inside of the Spring framework.
 *
 */
public class EmbeddedMain {
    public static void main (String [] args) throws Throwable {

        ClassPathXmlApplicationContext classPathXmlApplicationContext =
                new ClassPathXmlApplicationContext("jdbc-ns.xml");
        classPathXmlApplicationContext.start();

        DataSource dataSource = classPathXmlApplicationContext.getBean(DataSource.class);
        
        System.out.println( "buildDataSource: " + dataSource );
    }
}
