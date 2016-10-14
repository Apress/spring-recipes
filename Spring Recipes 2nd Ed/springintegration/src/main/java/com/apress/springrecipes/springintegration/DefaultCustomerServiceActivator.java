package com.apress.springrecipes.springintegration;

import org.springframework.integration.annotation.ServiceActivator;


public class DefaultCustomerServiceActivator {
    @ServiceActivator
    public void handleCustomer(Customer customer) {
        System.out.println("customer.creditScore=" + customer.getCreditScore());
    }
}
