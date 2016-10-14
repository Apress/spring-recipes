package com.apress.springrecipes.court.domain;

import java.beans.PropertyEditorSupport;

import com.apress.springrecipes.court.service.ReservationService;

public class SportTypeEditor extends PropertyEditorSupport {

    private ReservationService reservationService;

    public SportTypeEditor(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    public void setAsText(String text) throws IllegalArgumentException {
        int sportTypeId = Integer.parseInt(text);
        SportType sportType = reservationService.getSportType(sportTypeId);
        setValue(sportType);
    }
}
