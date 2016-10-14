package com.apress.springrecipes.travel.weather;

import java.util.Map;

public interface WeatherService {

    public Map<String, Double> getMajorCityTemperatures();
}
