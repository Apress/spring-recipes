package com.apress.springrecipes.signup.web;

import java.io.Serializable;


/**
 * This is a simple bean that we use to demonstrate RichFaces' ability to update state via Ajax
 */
public class CounterBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private int count;

    public void updateCount() {
        this.count += 1;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }
}
