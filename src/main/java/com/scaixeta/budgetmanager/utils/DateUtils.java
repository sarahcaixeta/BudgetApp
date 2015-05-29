package com.scaixeta.budgetmanager.utils;

import org.joda.time.LocalDate;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateUtils {

    public static String localDateToString(LocalDate localDate) {
        return localDate.toString("EEEE, MMM dd, yyyy");
    }

    public static String parseToISOString(LocalDate localDate) {
        return localDate.toString("yyyy-MM-dd HH:mm:ss");
    }

    public static LocalDate parseISODateToDate(String isoDate) {
        return LocalDate.parse(isoDate);
    }

    public static String parseCalendarToString(Calendar calendar) {
        SimpleDateFormat fmt = new SimpleDateFormat("EEEE, MMM dd, yyyy");
        fmt.setCalendar(calendar);
        return fmt.format(calendar.getTime());
    }
}
