package com.apress.springrecipes.travel.tour;

import java.util.ArrayList;
import java.util.List;

public class TourServiceImpl implements TourService {

    private List<BookingForm> forms = new ArrayList<BookingForm>();
    private List<String> locations;

    public List<String> getLocations() {
        return locations;
    }

    public void setLocations(List<String> locations) {
        this.locations = locations;
    }

    public void processBooking(BookingForm form) {
        forms.add(form);
    }
}
