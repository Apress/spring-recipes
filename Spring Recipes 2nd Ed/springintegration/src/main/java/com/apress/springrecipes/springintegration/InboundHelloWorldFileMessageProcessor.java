package com.apress.springrecipes.springintegration;

import org.apache.log4j.Logger;

import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.core.Message;

import java.io.File;


public class InboundHelloWorldFileMessageProcessor {
    private static final Logger logger = Logger.getLogger(InboundHelloWorldFileMessageProcessor.class);

    @ServiceActivator
    public void handleIncomingFileMessage(Message<File> inboundJmsMessage)
        throws Throwable {
        File filePayload = inboundJmsMessage.getPayload();
        System.out.println(String.format("absolute path: %s, size: %s", filePayload.getAbsolutePath(), filePayload.length()));
    }
}
