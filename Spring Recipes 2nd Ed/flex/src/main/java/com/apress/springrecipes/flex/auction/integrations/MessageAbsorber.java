package com.apress.springrecipes.flex.auction.integrations;

import org.apache.commons.lang.builder.ToStringBuilder;

import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.core.Message;
import org.springframework.integration.core.MessageChannel;

import org.springframework.stereotype.Component;

import java.util.Map;

import javax.annotation.Resource;


@Component
public class MessageAbsorber {
    @Resource(name = "inboundItemsAudited")
    private MessageChannel publishSubscribeChannel;

    @ServiceActivator
    public void handle(Message<Map<String, Object>> msg) {
        Map<String, Object> item = msg.getPayload();

        System.out.println(ToStringBuilder.reflectionToString(item));

        // eat the false
        publishSubscribeChannel.send(msg);
    }
}
