// FINAL
package com.apress.springrecipes.court.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ReservationSuccessController {
    // Method is bound directly to URL /reservationSuccess 
    @RequestMapping("/reservationSuccess")
    public String reservationSuccess() {
	// Return view reservationSuccess. Via resolver the view
	// will be mapped to /WEB-INF/jsp/reservationSuccess.jsp
        return "reservationSuccess";
    }
}
