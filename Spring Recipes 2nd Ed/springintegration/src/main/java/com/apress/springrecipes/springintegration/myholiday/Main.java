package com.apress.springrecipes.springintegration.myholiday;

import org.apache.commons.lang.time.DateUtils;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class Main {
    public static void main(String[] args) throws Throwable {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("09-1-gateways_service.xml");
        classPathXmlApplicationContext.start();

        Date now = new Date();
        HotelReservationSearch hotelReservationSearch = new HotelReservationSearch(200f, 2, DateUtils.add(now, Calendar.DATE, 1), DateUtils.add(now, Calendar.DATE, 8));
        ClassPathXmlApplicationContext classPathXmlApplicationContext1 = new ClassPathXmlApplicationContext("09-1-gateways_client.xml");
        classPathXmlApplicationContext1.start();

        VacationService vacationService = (VacationService) classPathXmlApplicationContext1.getBean("vacationService");
        List<HotelReservation> results = vacationService.findHotels(hotelReservationSearch);

        System.out.printf("Found %s results.", results.size());
        System.out.println();

        for (HotelReservation reservation : results) {
            System.out.printf("\t%s", reservation.toString());
            System.out.println();
        }
    }
}
