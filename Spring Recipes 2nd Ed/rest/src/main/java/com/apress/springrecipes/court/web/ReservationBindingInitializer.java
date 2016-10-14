package com.apress.springrecipes.court.web;

import com.apress.springrecipes.court.domain.SportType;
import com.apress.springrecipes.court.domain.SportTypeEditor;
import com.apress.springrecipes.court.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

import java.text.SimpleDateFormat;
import java.util.Date;


public class ReservationBindingInitializer implements WebBindingInitializer {
    private ReservationService reservationService;

    @Autowired
    public ReservationBindingInitializer(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    public void initBinder(WebDataBinder binder, WebRequest request) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
        binder.registerCustomEditor(SportType.class, new SportTypeEditor(reservationService));
    }
}
