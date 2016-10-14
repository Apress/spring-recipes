package com.apress.springrecipes.weather;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * @author <a href="mailto:josh@joshlong.com">Josh Long</a>
 */
public class StandaloneServer {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("standalone-server.xml");
        applicationContext.start();
    }
}
