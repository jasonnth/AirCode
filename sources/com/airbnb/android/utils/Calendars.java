package com.airbnb.android.utils;

import java.util.Calendar;
import java.util.Date;

public final class Calendars {
    private Calendars() {
    }

    public static Calendar fromLong(Long time) {
        if (time.longValue() <= 0) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time.longValue());
        return calendar;
    }

    public static Calendar fromDate(Date date) {
        return fromLong(Long.valueOf(date.getTime()));
    }

    public static Calendar fromYearMonthDay(int year, int month, int day) {
        Calendar date = Calendar.getInstance();
        date.set(1, year);
        date.set(2, month);
        date.set(5, day);
        return date;
    }
}
