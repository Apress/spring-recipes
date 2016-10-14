package com.apress.springrecipes.springintegration;

import org.springframework.integration.annotation.Router;


public class CustomerCreditScoreRouter {
    @Router
    public String routeByCustomerCreditScore(Customer customer) {
        if (customer.getCreditScore() > 770) {
            System.out.println("Credit score > 770, routing to safeCustomerChannel");

            return "safeCustomerChannel";
        } else {
            System.out.println("Credit score <= 770, routing to riskyCustomerChannel");

            return "riskyCustomerChannel";
        }
    }
}
