package com.apress.springrecipes.distributedspring.terracotta.customerconsole;

import com.apress.springrecipes.distributedspring.terracotta.customerconsole.view.CustomerConsole;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class MainWithSpring {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("customerconsole-context.xml");
        CustomerConsole customerConsole = (CustomerConsole) context.getBean("customerConsole");

        while (true) {
            customerConsole.handleNextCommand("Welcome to the customer console: your choices are DELETE, UPDATE, CREATE or LIST");
        }
    }
}
