package com.scaixeta.budgetmanager.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateUtils {

    public static String parseCalendarToString(Calendar calendar) {
        SimpleDateFormat fmt = new SimpleDateFormat("EEEE, MMM dd, yyyy");
        fmt.setCalendar(calendar);
        return fmt.format(calendar.getTime());
    }
}
