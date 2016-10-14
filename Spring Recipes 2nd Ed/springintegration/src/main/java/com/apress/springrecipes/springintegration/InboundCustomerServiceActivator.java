package com.apress.springrecipes.springintegration;

import org.apache.log4j.Logger;

import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.core.Message;


public class InboundCustomerServiceActivator {
    private static final Logger logger = Logger.getLogger(InboundCustomerServiceActivator.class);

    @ServiceActivator
    public void doSomethingWithCustomer(Message<Customer> customerMessage) {
        Customer customer = customerMessage.getPayload();
        logger.debug(String.format("id=%s, firstName:%s, lastName:%s", customer.getId(), customer.getFirstName(), customer.getLastName()));
    }
}
