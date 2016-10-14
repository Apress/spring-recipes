package com.apress.springrecipes.travel.tour;

import java.util.List;

public interface TourService {

    public List<String> getLocations();
    public void processBooking(BookingForm form);
}
