package com.apress.springrecipes.springintegration;

import org.springframework.integration.annotation.Headers;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.core.MessageHeaders;
import org.springframework.integration.file.FileHeaders;

import java.io.File;

import java.util.Map;


public class InboundFileMessageServiceActivatorWithHeadersMap {
    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(InboundFileMessageServiceActivatorWithHeadersMap.class);

    @ServiceActivator
    public void interrogateMessage(@Headers
    Map<String, Object> headers, File file) {
        logger.debug(String.format("the id of the message is %s, and name of the file payload is %s", headers.get(MessageHeaders.ID), headers.get(FileHeaders.FILENAME)));
    }
}
