package com.apress.springrecipes.travel.flight;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FlightServiceImpl implements FlightService {

    private Map<String, Flight> flights;

    public FlightServiceImpl() {
        flights = new HashMap<String, Flight>();
        flights.put("CX888", new Flight("CX888", "HKG", "YVR", new Date()));
        flights.put("CX889", new Flight("CX889", "HKG", "JFK", new Date()));
    }

    public List<Flight> findTodayFlights() {
        return new ArrayList<Flight>(flights.values());
    }

    public Flight findFlight(String flightNo) {
        return flights.get(flightNo);
    }
}
