package com.apress.springrecipes.shop;

import java.util.Date;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

public class CheckoutListener implements ApplicationListener {

    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof CheckoutEvent) {
            double amount = ((CheckoutEvent) event).getAmount();
            Date time = ((CheckoutEvent) event).getTime();

            // Do anything you like with the checkout amount and time
            System.out.println("Checkout event [" + amount + ", " + time + "]");
        }
    }
}
