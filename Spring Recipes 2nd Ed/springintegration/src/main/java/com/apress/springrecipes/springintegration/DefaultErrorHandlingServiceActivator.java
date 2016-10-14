package com.apress.springrecipes.springintegration;

import org.apache.commons.lang.exception.ExceptionUtils;

import org.apache.log4j.Logger;

import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.core.Message;
import org.springframework.integration.core.MessagingException;


public class DefaultErrorHandlingServiceActivator {
    private static final Logger logger = Logger.getLogger(DefaultErrorHandlingServiceActivator.class);

    @ServiceActivator
    public void handleThrowable(Message<Throwable> errorMessage)
        throws Throwable {
        Throwable throwable = errorMessage.getPayload();
        logger.debug(String.format("message: %s, stack trace :%s", throwable.getMessage(), ExceptionUtils.getFullStackTrace(throwable)));

        if (throwable instanceof MessagingException) {
            Message<?> failedMessage = ((MessagingException) throwable).getFailedMessage();

            if (failedMessage != null) {
                // do something with the original message
            }
        } else {
            // it's something that was thrown in the execution of code in some component you created
        }
    }
}
