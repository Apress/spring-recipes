package com.apress.springrecipes.springintegration;

import org.springframework.integration.annotation.Header;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.core.MessageHeaders;
import org.springframework.integration.file.FileHeaders;

import java.io.File;


public class InboundFileMessageServiceActivator {
    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(InboundFileMessageServiceActivator.class);

    @ServiceActivator
    public void interrogateMessage(@Header(MessageHeaders.ID)
    String uuid, @Header(FileHeaders.FILENAME)
    String fileName, File file) {
        logger.debug(String.format("the id of the message is %s, and name of the file payload is %s", uuid, fileName));
    }
}
