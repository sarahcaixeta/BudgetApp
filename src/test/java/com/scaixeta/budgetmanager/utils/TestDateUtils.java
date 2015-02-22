package com.scaixeta.budgetmanager.utils;

import java.util.Calendar;

public class TestDateUtils {

    public static Calendar aCalendarOn(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        return calendar;
    }
}
