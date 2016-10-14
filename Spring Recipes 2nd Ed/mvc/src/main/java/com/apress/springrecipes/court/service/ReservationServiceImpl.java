package com.apress.springrecipes.court.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.apress.springrecipes.court.domain.PeriodicReservation;
import com.apress.springrecipes.court.domain.Player;
import com.apress.springrecipes.court.domain.Reservation;
import com.apress.springrecipes.court.domain.SportType;


public class ReservationServiceImpl implements ReservationService {

    public static final SportType TENNIS = new SportType(1, "Tennis");
    public static final SportType SOCCER = new SportType(2, "Soccer");

    private List<Reservation> reservations;

    public ReservationServiceImpl() {
        reservations = new ArrayList<Reservation>();
        reservations.add(new Reservation("Tennis #1",
                new GregorianCalendar(2008, 0, 14).getTime(), 16,
                new Player("Roger", "N/A"), TENNIS));
        reservations.add(new Reservation("Tennis #2",
                new GregorianCalendar(2008, 0, 14).getTime(), 20,
                new Player("James", "N/A"), TENNIS));
    }

    public List<Reservation> query(String courtName) {
        List<Reservation> result = new ArrayList<Reservation>();
        for (Reservation reservation : reservations) {
            if (reservation.getCourtName().equals(courtName)) {
                result.add(reservation);
            }
        }
        return result;
    }

    public void make(Reservation reservation)
            throws ReservationNotAvailableException {
        for (Reservation made : reservations) {
            if (made.getCourtName().equals(reservation.getCourtName())
                    && made.getDate().equals(reservation.getDate())
                    && made.getHour() == reservation.getHour()) {
                throw new ReservationNotAvailableException(reservation
                        .getCourtName(), reservation.getDate(), reservation
                        .getHour());
            }
        }
        reservations.add(reservation);
    }

    public List<SportType> getAllSportTypes() {
        return Arrays.asList(new SportType[] { TENNIS, SOCCER });
    }

    public SportType getSportType(int sportTypeId) {
        switch (sportTypeId) {
        case 1:
            return TENNIS;
        case 2:
            return SOCCER;
        default:
            return null;
        }
    }

    public void makePeriodic(PeriodicReservation periodicReservation)
            throws ReservationNotAvailableException {
        Calendar fromCalendar = Calendar.getInstance();
        fromCalendar.setTime(periodicReservation.getFromDate());

        Calendar toCalendar = Calendar.getInstance();
        toCalendar.setTime(periodicReservation.getToDate());

        while (fromCalendar.before(toCalendar)) {
            Reservation reservation = new Reservation();
            reservation.setCourtName(periodicReservation.getCourtName());
            reservation.setDate(fromCalendar.getTime());
            reservation.setHour(periodicReservation.getHour());
            reservation.setPlayer(periodicReservation.getPlayer());
            make(reservation);

            fromCalendar.add(Calendar.DATE, periodicReservation.getPeriod());
        }
    }

    public List<Reservation> findByDate(Date date) {
        List<Reservation> result = new ArrayList<Reservation>();
        for (Reservation reservation : reservations) {
            if (reservation.getDate().equals(date)) {
                result.add(reservation);
            }
        }
        return result;
    }
}
