package com.apress.springrecipes.hello;

import java.util.List;

public class HelloWorld {

    private String message;
    private List<Holiday> holidays;

    public void setMessage(String message) {
        this.message = message;
    }

    public void setHolidays(List<Holiday> holidays) {
        this.holidays = holidays;
    }

    public void hello() {
        System.out.println("Hello! " + message);
    }
}
