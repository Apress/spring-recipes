package com.apress.springrecipes.springbatch.solution2;

import com.apress.springrecipes.springbatch.UserRegistration;
import com.apress.springrecipes.springbatch.UserRegistrationService;

import org.apache.commons.lang.builder.ToStringBuilder;

import org.apache.log4j.Logger;

import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.retry.RetryCallback;
import org.springframework.batch.retry.RetryContext;
import org.springframework.batch.retry.support.RetryTemplate;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


/**
 * This class writes the user registration by calling an RPC service (whose client interface is wired in using Spring
 */
public class RetryableUserRegistrationServiceItemWriter implements ItemWriter<UserRegistration> {
    private static final Logger logger = Logger.getLogger(RetryableUserRegistrationServiceItemWriter.class);

    // this is the client interface to an HTTP Invoker service.
    @Autowired
    private UserRegistrationService userRegistrationService;
    
    @Autowired
    private RetryTemplate retryTemplate;

    /**
     * takes aggregated input from the reader and 'writes' them using a custom implementation.
     */
    public void write(List<?extends UserRegistration> items)
        throws Exception {
        for (final UserRegistration userRegistration : items) {
            UserRegistration registeredUserRegistration = retryTemplate.execute(new RetryCallback<UserRegistration>() {
                        public UserRegistration doWithRetry(RetryContext context)
                            throws Exception {
                            return userRegistrationService.registerUser(userRegistration);
                        }
                    });

            logger.debug("Registered:" + ToStringBuilder.reflectionToString(registeredUserRegistration));
        }
    }
}
