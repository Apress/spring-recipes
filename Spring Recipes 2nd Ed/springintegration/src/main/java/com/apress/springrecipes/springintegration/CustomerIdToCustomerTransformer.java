package com.apress.springrecipes.springintegration;

import org.springframework.integration.annotation.Transformer;


public class CustomerIdToCustomerTransformer {
    @Transformer
    public Customer fromCustomerId(String id) {
        // in a real integration you'd probably use this point to look up the
        // Customer in the DB
        Customer customer = new Customer();
        customer.setId(Long.parseLong(id));
        customer.setCreditScore((float) (Math.random() * 1000));

        // customer.set*
        return customer;
    }
}
