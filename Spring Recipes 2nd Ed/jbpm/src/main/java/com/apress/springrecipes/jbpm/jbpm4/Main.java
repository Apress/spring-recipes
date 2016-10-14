package com.apress.springrecipes.jbpm.jbpm4;

import com.apress.springrecipes.jbpm.jbpm4.customers.Customer;
import com.apress.springrecipes.jbpm.jbpm4.customers.CustomerService;

import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * This class is the launching point of a simple jBPM based service case
 */
public class Main {
    public static void main(String[] args) throws Throwable {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("context1.xml");
        applicationContext.start();

        CustomerService customerService = (CustomerService) applicationContext.getBean("customerService");
        Customer customer = customerService.createCustomer("user@userssite.com", "password", "First", "Last");
        customerService.authorizeCustomer(customer.getId());
    }
}
