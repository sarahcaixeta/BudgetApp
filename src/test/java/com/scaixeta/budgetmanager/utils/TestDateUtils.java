package com.scaixeta.budgetmanager.utils;

import org.joda.time.LocalDate;

import java.util.Calendar;

public class TestDateUtils {

    public static LocalDate aCalendarOn(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        return LocalDate.fromCalendarFields(calendar);
    }
}
