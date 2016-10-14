package com.apress.springrecipes.springintegration.twitter;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.InitializingBean;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.Message;
import org.springframework.integration.message.MessageBuilder;

import org.springframework.util.Assert;

import twitter4j.Twitter;
import twitter4j.TwitterException;


/*
*  @author Josh Long
*
*  Produces status updates
*
**/
public class TwitterMessageProducer implements InitializingBean {
    static private Logger logger = Logger.getLogger(TwitterMessageProducer.class);
    private volatile String userId;
    private volatile String password;
    private volatile Twitter twitter;

    public static void main(String[] args) throws Throwable {
        try {
            ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("08-2-adaptingexternalsystemstothebus.xml");
            classPathXmlApplicationContext.start();

            DirectChannel channel = (DirectChannel) classPathXmlApplicationContext.getBean("outboundTweets");
            Message<String> helloWorldMessage = MessageBuilder.withPayload("I wonder...").build();
            channel.send(helloWorldMessage);
        } catch (Throwable th) {
            logger.debug(ExceptionUtils.getFullStackTrace(th));
        }
    }

    public void tweet(String tweet) {
        try {
            twitter.updateStatus(tweet);
        } catch (TwitterException e) {
            logger.debug(ExceptionUtils.getFullStackTrace(e));
        }
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Twitter getTwitter() {
        return twitter;
    }

    public void setTwitter(Twitter twitter) {
        this.twitter = twitter;
    }

    public void afterPropertiesSet() throws Exception {
        if (twitter == null) {
            Assert.state(!StringUtils.isEmpty(userId));
            Assert.state(!StringUtils.isEmpty(password));

            twitter = new Twitter();
            twitter.setUserId(userId);
            twitter.setPassword(password);
        } else { // it isnt null, in which case it becomes canonical memory
            setPassword(twitter.getPassword());
            setUserId(twitter.getUserId());
        }
    }
}
