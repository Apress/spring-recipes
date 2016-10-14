package com.apress.springrecipes.travel.flight;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("HELP")
public class FlightHelpController {

    @RequestMapping
    public String showHelp() {
        return "flightHelp";
    }
}
