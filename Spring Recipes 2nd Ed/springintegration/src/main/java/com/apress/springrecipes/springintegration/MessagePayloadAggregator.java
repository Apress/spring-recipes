package com.apress.springrecipes.springintegration;

import org.springframework.integration.annotation.Aggregator;
import org.springframework.integration.core.Message;
import org.springframework.integration.message.MessageBuilder;

import java.util.List;


public class MessagePayloadAggregator {
    @Aggregator
    public Message<?> joinMessages(List<Message<Customer>> customers) {
        if (customers.size() > 0) {
            return MessageBuilder.withPayload(customers).copyHeadersIfAbsent(customers.get(0).getHeaders()).build();
        }

        return null;
    }
}
