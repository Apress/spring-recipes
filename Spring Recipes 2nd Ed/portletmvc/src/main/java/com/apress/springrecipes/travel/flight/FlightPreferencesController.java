package com.apress.springrecipes.travel.flight;

import javax.portlet.PortletPreferences;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("EDIT")
public class FlightPreferencesController {

    @RequestMapping
    public String showPreferences(PortletPreferences preferences, Model model) {
        model.addAttribute("timeZone", preferences.getValue("timeZone", null));
        return "flightPreferences";
    }

    @RequestMapping
    public void changePreference(PortletPreferences preferences,
            @RequestParam("timeZone") String timeZone) throws Exception {
        preferences.setValue("timeZone", timeZone);
        preferences.store();
    }
}
