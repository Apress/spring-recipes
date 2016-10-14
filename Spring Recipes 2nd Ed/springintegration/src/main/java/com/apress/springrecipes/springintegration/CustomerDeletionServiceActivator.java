package com.apress.springrecipes.springintegration;

import org.apache.log4j.Logger;

import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.core.Message;

public class CustomerDeletionServiceActivator {
    static private Logger logger = Logger.getLogger(CustomerDeletionServiceActivator.class);

    @ServiceActivator
    public void deleteCustomer(Message<String> customerIdPayload) {
        logger.debug(String.format("the id of the customer to delete is %s", customerIdPayload.getPayload()));
    }
}
