package com.apress.springrecipes.springintegration;

import org.apache.log4j.Logger;

import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.core.Message;

import java.util.Map;


public class InboundHelloWorldJMSMessageProcessor {
    private static final Logger logger = Logger.getLogger(InboundHelloWorldJMSMessageProcessor.class);

    /*
    //
    // this is how the method would look if you had set extract-payload == false.
    //
    @ServiceActivator
    public void handleIncomingJmsMessageWithPayloadNotExtracted(
            Message<javax.jms.Message> msgWithJmsMessageAsPayload
    ) throws Throwable {
        javax.jms.MapMessage jmsMessage = (MapMessage) msgWithJmsMessageAsPayload.getPayload();
        logger.debug(String.format("firstName: %s, lastName: %s, id:%s", jmsMessage.getString("firstName"),
                jmsMessage.getString("lastName"), jmsMessage.getLong("id")));
    }
     */
    @ServiceActivator
    public void handleIncomingJmsMessage(Message<Map<String, Object>> inboundJmsMessage)
        throws Throwable {
        Map<String, Object> msg = inboundJmsMessage.getPayload();
        logger.debug(String.format("firstName: %s, lastName: %s, id:%s", msg.get("firstName"), msg.get("lastName"), msg.get("id")));
    }
}
