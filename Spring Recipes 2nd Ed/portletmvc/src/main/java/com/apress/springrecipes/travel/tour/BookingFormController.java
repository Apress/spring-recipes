package com.apress.springrecipes.travel.tour;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.portlet.ActionResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@RequestMapping("VIEW")
@SessionAttributes("bookingForm")
public class BookingFormController {

    private TourService tourService;
    private BookingFormValidator bookingFormValidator;

    @Autowired
    public BookingFormController(TourService tourService,
            BookingFormValidator bookingFormValidator) {
        this.tourService = tourService;
        this.bookingFormValidator = bookingFormValidator;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(
                dateFormat, true));
    }

    @ModelAttribute("locations")
    protected List<String> getLocations() {
        return tourService.getLocations();
    }

    @RequestMapping
    public String showForm(
            @RequestParam(required = false, value = "form-submit")
            Boolean isFormSubmission, ModelMap model) {
        if (isFormSubmission == null) {
            BookingForm bookingForm = new BookingForm();
            model.addAttribute("bookingForm", bookingForm);
            return "bookingForm";
        }
        return "bookingSuccess";
    }

    @RequestMapping
    public void processSubmit(
            @ModelAttribute("bookingForm") BookingForm form,
            BindingResult result, SessionStatus status,
            ActionResponse response) {
        bookingFormValidator.validate(form, result);
        if (!result.hasErrors()) {	    
	    tourService.processBooking(form);
	    status.setComplete();
	    response.setRenderParameter("form-submit", "true");
	}
    }
}
