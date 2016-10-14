package com.apress.springrecipes.travel.weather;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;



@Controller
@RequestMapping("VIEW")
public class WeatherController {

    private WeatherService weatherService;

    @Autowired
    public void WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }


    @RequestMapping
    public String handleRender(Model model) throws Exception {
	model.addAttribute("temperatures", weatherService.getMajorCityTemperatures());
        return "weatherView";
    }
}
