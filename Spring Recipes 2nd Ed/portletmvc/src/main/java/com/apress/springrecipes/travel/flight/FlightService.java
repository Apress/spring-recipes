package com.apress.springrecipes.travel.flight;

import java.util.List;

public interface FlightService {

    public List<Flight> findTodayFlights();
    public Flight findFlight(String flightNo);
}
