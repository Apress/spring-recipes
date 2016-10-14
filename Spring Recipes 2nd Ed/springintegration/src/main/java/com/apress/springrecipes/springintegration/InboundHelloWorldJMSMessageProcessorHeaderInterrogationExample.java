package com.apress.springrecipes.springintegration;

import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.core.Message;
import org.springframework.integration.core.MessageHeaders;

import java.util.Map;


public class InboundHelloWorldJMSMessageProcessorHeaderInterrogationExample {
    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(InboundHelloWorldJMSMessageProcessorHeaderInterrogationExample.class);

    @ServiceActivator
    public void interrogateMessage(Message message) {
        MessageHeaders headers = message.getHeaders();

        for (Map.Entry header : headers.entrySet()) {
            logger.debug(String.format("key = %s, value = %s", header.getKey(), header.getValue()));
        }
    }
}
