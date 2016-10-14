package com.apress.springrecipes.jbpm.jbpm4.customers;


/**
 * Service to handle working with Customers This service handles CRUD as well as life cycle managment for a given
 * customer. It's able to do this using BPM.
 */
public interface CustomerService {
    /**
     * Sends a welcome email to the user
     *
     * @param customerId
     */
    void sendWelcomeEmail(Long customerId);

    /**
     * Revokes a customer's privileges
     *
     * @param customerId
     */
    void deauthorizeCustomer(Long customerId);

    /**
     * Authorizes a customer with certain privileges. In this simple example we merely afford them access to the system
     * through a boolean flag.
     *
     * @param customerId
     */
    void authorizeCustomer(Long customerId);

    /**
     * Returns a customer by the primary key used to store it
     */
    Customer getCustomerById(Long customerId);

    /**
     * Create a customer with a given email, password, firstName, and lastName
     */
    Customer createCustomer(String email, String password, String firstName, String lastName);

    void sendCustomerVerificationEmail(Long customerId);
}
