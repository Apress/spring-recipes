package com.apress.springrecipes.springintegration.twitter;

public class TwitterMessageOutput {
    public void announce(Tweet tweet) {
        System.out.println(tweet.toString());
    }
}
