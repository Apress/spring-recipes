package com.apress.springrecipes.springintegration;

import org.apache.log4j.Logger;

import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.core.Message;
import org.springframework.integration.core.MessageHeaders;
import org.springframework.integration.message.MessageBuilder;


public class ServiceActivatorThatSpecifiesErrorChannel {
    private static final Logger logger = Logger.getLogger(ServiceActivatorThatSpecifiesErrorChannel.class);

    @ServiceActivator
    public Message<?> startIntegrationFlow(Message<?> firstMessage)
        throws Throwable {
        return MessageBuilder.fromMessage(firstMessage).setHeaderIfAbsent(MessageHeaders.ERROR_CHANNEL, "errorChannelForMySolution").build();
    }
}
