package com.apress.springrecipes.springintegration;

import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.core.Message;
import org.springframework.integration.message.MessageBuilder;

import java.util.Map;


public class InboundJMSMessageToCustomerWithExtraMetadataTransformer {
    @Transformer
    public Message<Customer> transformJMSMapToCustomer(Message<Map<String, Object>> inboundSprignIntegrationMessage) {
        Map<String, Object> jmsMessagePayload = inboundSprignIntegrationMessage.getPayload();
        Customer customer = new Customer();
        customer.setFirstName((String) jmsMessagePayload.get("firstName"));
        customer.setLastName((String) jmsMessagePayload.get("lastName"));
        customer.setId((Long) jmsMessagePayload.get("id"));

        return MessageBuilder.withPayload(customer).copyHeadersIfAbsent(inboundSprignIntegrationMessage.getHeaders()).setHeaderIfAbsent("randomlySelectedForSurvey", Math.random() > .5).build();
    }
}
