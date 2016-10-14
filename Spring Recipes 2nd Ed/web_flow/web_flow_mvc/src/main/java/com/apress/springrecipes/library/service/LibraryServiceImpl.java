package com.apress.springrecipes.library.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;


public class LibraryServiceImpl implements LibraryService {
    public List<Date> getHolidays() {
        List<Date> holidays = new ArrayList<Date>();
        holidays.add(new GregorianCalendar(2007, 11, 25).getTime());
        holidays.add(new GregorianCalendar(2008, 0, 1).getTime());

        return holidays;
    }
}
