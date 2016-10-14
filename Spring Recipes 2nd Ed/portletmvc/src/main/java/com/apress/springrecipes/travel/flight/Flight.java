package com.apress.springrecipes.travel.flight;

import java.util.Date;

public class Flight {

    private String number;
    private String origin;
    private String destination;
    private Date departureTime;

    public Flight() {}

    public Flight(String number, String origin, String destination, Date departureTime) {
        this.number = number;
        this.origin = origin;
        this.destination = destination;
        this.departureTime = departureTime;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public String getDestination() {
        return destination;
    }

    public String getNumber() {
        return number;
    }

    public String getOrigin() {
        return origin;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

}
