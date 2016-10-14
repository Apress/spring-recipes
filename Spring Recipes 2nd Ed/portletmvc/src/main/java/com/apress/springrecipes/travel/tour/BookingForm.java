package com.apress.springrecipes.travel.tour;

import java.io.Serializable;
import java.util.Date;

public class BookingForm implements Serializable {

    private String tourist;
    private String phone;
    private String origin;
    private String destination;
    private Date departureDate;
    private Date returnDate;

    public Date getDepartureDate() {
        return departureDate;
    }

    public String getDestination() {
        return destination;
    }

    public String getOrigin() {
        return origin;
    }

    public String getPhone() {
        return phone;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public String getTourist() {
        return tourist;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public void setTourist(String tourist) {
        this.tourist = tourist;
    }
}
