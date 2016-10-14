//FINAL 
package com.apress.springrecipes.court.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class AboutController {
    @Value("#{ messageSource.getMessage('admin.email',null,'en')}")
    private String email;

    // Method is bound directly to URL /about
    @RequestMapping("/about")
    // Method contains Model input to setup date object
    public String courtReservation(Model model) {
        // Add email to model so it can be display in view
        model.addAttribute("email", email);

        // Return view about. Via resolver the view
        // will be mapped to /WEB-INF/jsp/welcome.jsp
        return "about";
    }
}
