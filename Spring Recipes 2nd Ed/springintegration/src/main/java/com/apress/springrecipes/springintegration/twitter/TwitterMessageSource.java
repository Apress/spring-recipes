package com.apress.springrecipes.springintegration.twitter;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.InitializingBean;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.Message;
import org.springframework.integration.message.MessageBuilder;
import org.springframework.integration.message.MessageHandler;
import org.springframework.integration.message.MessageSource;

import org.springframework.util.Assert;

import twitter4j.Paging;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

import java.util.Date;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;


/*
*  @author Josh Long
*
*  Consumes messages from the Twitter that show up in a given
*  users timeline that weren't there before and feeds them.
*
**/
public class TwitterMessageSource implements MessageSource<Tweet>, InitializingBean {
    static private Logger logger = Logger.getLogger(TwitterMessageSource.class);
    private volatile Queue<Tweet> cachedStatuses;
    private volatile String userId;
    private volatile String password;
    private volatile Twitter twitter;
    private volatile long lastStatusIdRetreived = -1;

    public static void main(String[] args) throws Throwable {
        try {
            ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("08-1-adaptingexternalsystemstothebus.xml");
            classPathXmlApplicationContext.start();

            DirectChannel channel = (DirectChannel) classPathXmlApplicationContext.getBean("inboundTweets");

            channel.subscribe(new MessageHandler() {
                    public void handleMessage(Message<?> message) {
                        Tweet tweet = (Tweet) message.getPayload();
                        logger.debug(String.format("Received %s at %s ", tweet.toString(), new Date().toString()));
                    }
                });
        } catch (Throwable th) {
            logger.debug(ExceptionUtils.getFullStackTrace(th));
        }
    }

    private Tweet buildTweetFromStatus(Status firstPost) {
        Tweet tweet = new Tweet(firstPost.getId(), firstPost.getUser().getName(), firstPost.getCreatedAt(), firstPost.getText());

        return tweet;
    }

    public Message<Tweet> receive() {
        Assert.state(cachedStatuses != null);

        if (cachedStatuses.peek() == null) {
            Paging paging = new Paging();
            paging.setCount(20);

            if (-1 != lastStatusIdRetreived) {
                paging.sinceId(lastStatusIdRetreived);
            }

            try {
                List<Status> statuses = twitter.getFriendsTimeline(paging);
                Assert.state(cachedStatuses.peek() == null); // size() isn't
                                                             // constant time

                for (Status status : statuses) {
                    this.cachedStatuses.add(buildTweetFromStatus(status));
                }
            } catch (TwitterException e) {
                logger.info(ExceptionUtils.getFullStackTrace(e));
                throw new RuntimeException(e);
            }
        }

        if (cachedStatuses.peek() != null) {
            // size() == 0 would be more obvious
            // a test, but size() isn't constant time
            Tweet cachedStatus = cachedStatuses.poll();
            lastStatusIdRetreived = cachedStatus.getTweetId();

            return MessageBuilder.withPayload(cachedStatus).build();
        }

        return null;
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

        cachedStatuses = new ConcurrentLinkedQueue<Tweet>();
        lastStatusIdRetreived = -1;
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
}
