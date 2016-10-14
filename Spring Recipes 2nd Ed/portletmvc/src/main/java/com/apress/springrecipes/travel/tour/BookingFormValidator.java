package com.apress.springrecipes.travel.tour;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class BookingFormValidator implements Validator {

    public boolean supports(Class clazz) {
        return BookingForm.class.isAssignableFrom(clazz);
    }

    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tourist",
                "required.tourist");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone",
                "required.phone");
        ValidationUtils.rejectIfEmpty(errors, "origin",
                "required.origin");
        ValidationUtils.rejectIfEmpty(errors, "destination",
                "required.destination");
        ValidationUtils.rejectIfEmpty(errors, "departureDate",
                "required.departureDate");
        ValidationUtils.rejectIfEmpty(errors, "returnDate",
                "required.returnDate");

        BookingForm form = (BookingForm) target;
        if (form.getOrigin().equals(form.getDestination())) {
            errors.rejectValue("destination", "invalid.destination");
        }
        if (form.getDepartureDate() != null && form.getReturnDate() != null
                && !form.getDepartureDate().before(form.getReturnDate())) {
            errors.rejectValue("returnDate", "invalid.returnDate");
        }
    }
}
