package com.airbnb.android.lib.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public final class CalendarHelper {
    private CalendarHelper() {
    }

    public static boolean isSameDay(Calendar c1, Calendar c2) {
        if (c1 == c2) {
            return true;
        }
        if (c1 == null || c2 == null) {
            return false;
        }
        if (c1.get(2) == c2.get(2) && c1.get(5) == c2.get(5) && c1.get(1) == c2.get(1)) {
            return true;
        }
        return false;
    }

    public static int getStayDuration(Calendar c1, Calendar c2) {
        if (c1 == null || c2 == null) {
            return 0;
        }
        return Math.round(((float) (cloneCalendarAtNoon(c2).getTimeInMillis() - cloneCalendarAtNoon(c1).getTimeInMillis())) / ((float) 86400000));
    }

    private static Calendar cloneCalendarAtNoon(Calendar calendar) {
        Calendar clone = (Calendar) calendar.clone();
        clone.set(11, 12);
        clone.set(12, 0);
        clone.set(13, 0);
        return clone;
    }

    public static Calendar roundDateToMidnight(Calendar date) {
        Calendar cellTimeMidnight = (Calendar) date.clone();
        cellTimeMidnight.set(11, 0);
        cellTimeMidnight.set(12, 0);
        cellTimeMidnight.set(13, 0);
        cellTimeMidnight.set(14, 0);
        return cellTimeMidnight;
    }

    public static String formatHour(int hour) {
        DateFormat format = SimpleDateFormat.getTimeInstance(3);
        Calendar cal = Calendar.getInstance();
        cal.set(11, hour);
        cal.set(12, 0);
        return format.format(cal.getTime());
    }
}
