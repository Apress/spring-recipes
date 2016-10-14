package com.apress.springrecipes.travel.weather;

import java.util.HashMap;
import java.util.Map;

public class WeatherServiceImpl implements WeatherService {

    public Map<String, Double> getMajorCityTemperatures() {
        Map<String, Double> temperatures = new HashMap<String, Double>();
        temperatures.put("New York", 6.0);
        temperatures.put("London", 10.0);
        temperatures.put("Beijing", 5.0);
        return temperatures;
    }
}
