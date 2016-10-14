// FINAL 
package com.apress.springrecipes.court.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.apress.springrecipes.court.domain.PeriodicReservation;
import com.apress.springrecipes.court.domain.PeriodicReservationValidator;
import com.apress.springrecipes.court.domain.Player;
import com.apress.springrecipes.court.service.ReservationService;

import org.springframework.web.util.WebUtils;
import org.springframework.ui.Model;
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse;


@Controller
// Bind controller to URL /periodicReservationForm
// initial view will be resolved to the name returned in the default GET method
@RequestMapping("/periodicReservationForm")
// Add Reservation object to session, since its used in various pages/forms
@SessionAttributes("reservation") // Command name class was used in earlier Spring versions
public class PeriodicReservationController {

    private ReservationService reservationService;
    private PeriodicReservationValidator validator;

    // Wire service and validator in constructor, available in application context 
    @Autowired
    public PeriodicReservationController(ReservationService reservationService,
             PeriodicReservationValidator periodicReservationValidator) {
        this.reservationService = reservationService;
	this.validator = periodicReservationValidator;
    }

    // Controller will always look for a default GET method to call first, irrespective of name
    // In this case, named setupForm to ease identification
    @RequestMapping(method = RequestMethod.GET)
    // Method contain a Model input to setup reservation object
    public String setupForm(Model model) {	
	// Create inital reservation object
        PeriodicReservation reservation = new PeriodicReservation();
	// Set player for reservation object
        reservation.setPlayer(new Player());
	// Add reservation to model so it can be display in views
        model.addAttribute("reservation", reservation);
       	// Return view as a string
	// Based on resolver configuration the reservationCourtForm view 
	// will be mapped to a JSP in /WEB-INF/jsp/reservationCourtForm.jsp 
        return "reservationCourtForm";
    }

    // Controller will always look for a default POST method irrespective of name
    // when a submission ocurrs on the URL (i.e.@RequestMapping(/periodicReservationForm)) 
    // In this case, named submitForm to ease identification
    @RequestMapping(method = RequestMethod.POST)
    public String submitForm(
	    HttpServletRequest request, HttpServletResponse response,
            @ModelAttribute("reservation") PeriodicReservation reservation,
            BindingResult result, SessionStatus status,
	    @RequestParam("_page") int currentPage, Model model) {	
	// Define a map with page numbers and views
	// Used for when a user clicks Next or Cancel
	// Views via resolvers will be mapped to corresponding JSP pages in /WEB-INF/jsp/
	Map pageForms = new HashMap();
	pageForms.put(0,"reservationCourtForm");// Mapped to /WEB-INF/jsp/reservationCourtForm.jsp
	pageForms.put(1,"reservationTimeForm");// Mapped to /WEB-INF/jsp/reservationTimeForm.jsp
	pageForms.put(2,"reservationPlayerForm");// Mapped to /WEB-INF/jsp/reservationPlayerForm.jsp
	// Verify if the user clicked the cancel button
	if (request.getParameter("_cancel") != null) {
	    // Return to current page view, since user clicked cancel
	    return (String)pageForms.get(currentPage);	    
	// Verify if the user clicked the finish button
	} else if (request.getParameter("_finish") != null) {
	    // User is finished validate all reservation result
	    new PeriodicReservationValidator().validate(reservation, result);
	    // Check the result for errors after validation
	    if (!result.hasErrors()) {
		// No errors make reservation 
		reservationService.makePeriodic(reservation);
		// Set complete, mark the handler's session processing as complete
		// Allowing for cleanup of session attributes.
		status.setComplete();
		// Redirect to reservationSuccess URL, defined in ReservationSuccessController
		return "redirect:reservationSuccess";
	    } else {
		// Errors, this should always be "reservationPlayerForm" since its the last one, use HashMap anyways
		// return user to current view to correct
		return (String)pageForms.get(currentPage);	    
	    }
	// User clicked Next or Previous(_target - neither _cancel or _finish)
	} else {
	    // Extract target page
	    int targetPage = WebUtils.getTargetPage(request, "_target", currentPage);
	    // If targetPage is lesser than current page, user clicked 'Previous' 
	    if (targetPage < currentPage) {
		// Return to target page view 
		return (String)pageForms.get(targetPage);
	    } 
	    // User clicked 'Next'
	    // Validate data based on page
	    switch (currentPage) {
   	        case 0: 
		    new PeriodicReservationValidator().validateCourt(reservation, result); break;
	        case 1: 
		    new PeriodicReservationValidator().validateTime(reservation, result); break;
    	        case 2: 
		    new PeriodicReservationValidator().validatePlayer(reservation, result); break;
	    }
	    if (!result.hasErrors()) {
		// No errors, return target page
		return (String)pageForms.get(targetPage);
	    } else { 
		// Errors, return current page
		return (String)pageForms.get(currentPage);
	    }
	}
    }
    
    // Create attribute for model 
    // Will be represented as drop box containing "Daily, Weekly" values in reservationTimeForm
    @ModelAttribute("periods")
    public Map<Integer, String> periods() {	
	Map<Integer, String> periods = new HashMap<Integer, String>();
	periods.put(1, "Daily");
	periods.put(7, "Weekly");
        return periods;
    }
}
