package com.airbnb.android.core.utils;

import android.content.Context;
import android.content.res.Resources;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.C0716R;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import org.joda.time.DateTime;
import org.joda.time.IllegalInstantException;
import org.joda.time.LocalDateTime;
import org.joda.time.MutableDateTime;

public final class DateHelper {
    public static final long ONE_DAY_MILLIS = 86400000;
    public static final long ONE_HOUR_MILLIS = 3600000;
    public static final long ONE_MINUTE_MILLIS = 60000;
    public static final long ONE_MONTH_MILLIS = 2629740900L;
    public static final long ONE_SECOND_MILLIS = 1000;
    public static final long ONE_WEEK_MILLIS = 604800000;
    public static final long ONE_YEAR_MILLIS = 31556890800L;
    public static final SimpleDateFormat YEAR_MONTH_DAY_FORMATTER_US = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
    private static final Calendar sCalendar = Calendar.getInstance();

    private DateHelper() {
    }

    public static String getArrivalTimeWithName(Context context, AirDate date, String firstName, boolean isCheckingIn) {
        AirDate today = AirDate.today();
        Resources res = context.getResources();
        int monthsUntil = today.getMonthsUntil(date);
        if (monthsUntil > 0) {
            return res.getQuantityString(isCheckingIn ? C0716R.plurals.hh_first_name_arrives_in_months : C0716R.plurals.hh_first_name_checks_out_months, monthsUntil, new Object[]{firstName, Integer.valueOf(monthsUntil)});
        }
        int weeksUntil = today.getWeeksUntil(date);
        if (weeksUntil > 1) {
            return res.getQuantityString(isCheckingIn ? C0716R.plurals.hh_first_name_arrives_in_weeks : C0716R.plurals.hh_first_name_checks_out_weeks, weeksUntil, new Object[]{firstName, Integer.valueOf(weeksUntil)});
        }
        int daysUntil = today.getDaysUntil(date);
        if (daysUntil > 0) {
            return res.getQuantityString(isCheckingIn ? C0716R.plurals.hh_first_name_arrives_in_days : C0716R.plurals.hh_first_name_checks_out_days, daysUntil, new Object[]{firstName, Integer.valueOf(daysUntil)});
        } else if (daysUntil != 0) {
            return "";
        } else {
            return res.getString(isCheckingIn ? C0716R.string.hh_first_name_arrives_today : C0716R.string.hh_first_name_checks_out_today, new Object[]{firstName});
        }
    }

    public static String getInXTimeString(Context context, AirDate date) {
        AirDate today = AirDate.today();
        Resources res = context.getResources();
        int monthsUntil = today.getMonthsUntil(date);
        if (monthsUntil > 0) {
            return res.getQuantityString(C0716R.plurals.in_x_months, monthsUntil, new Object[]{Integer.valueOf(monthsUntil)});
        }
        int weeksUntil = today.getWeeksUntil(date);
        if (weeksUntil > 1) {
            return res.getQuantityString(C0716R.plurals.in_x_weeks, weeksUntil, new Object[]{Integer.valueOf(weeksUntil)});
        }
        int daysUntil = today.getDaysUntil(date);
        if (daysUntil > 1) {
            return res.getQuantityString(C0716R.plurals.in_x_days, daysUntil, new Object[]{Integer.valueOf(daysUntil)});
        } else if (daysUntil == 1) {
            return res.getString(C0716R.string.tomorrow);
        } else {
            if (daysUntil == 0) {
                return res.getString(C0716R.string.today);
            }
            return "";
        }
    }

    public static String getArrivingInString(Context context, AirDate arrivalDate) {
        AirDate today = AirDate.today();
        Resources res = context.getResources();
        int monthsUntil = today.getMonthsUntil(arrivalDate);
        if (monthsUntil > 0) {
            return res.getQuantityString(C0716R.plurals.x_arriving_in_months, monthsUntil, new Object[]{Integer.valueOf(monthsUntil)});
        }
        int weeksUntil = today.getWeeksUntil(arrivalDate);
        if (weeksUntil > 1) {
            return res.getQuantityString(C0716R.plurals.x_arriving_in_weeks, weeksUntil, new Object[]{Integer.valueOf(weeksUntil)});
        }
        int daysUntil = today.getDaysUntil(arrivalDate);
        if (daysUntil < 0) {
            return "";
        }
        return res.getQuantityString(C0716R.plurals.x_arriving_in_days, daysUntil, new Object[]{Integer.valueOf(daysUntil)});
    }

    public static String getStringDateSpan(Context context, AirDate startDate, AirDate endDate) {
        SimpleDateFormat sdf = new SimpleDateFormat(context.getString(C0716R.string.date_name_format));
        return context.getString(C0716R.string.calendar_setting_date_range, new Object[]{startDate.formatDate((DateFormat) sdf), endDate.formatDate((DateFormat) sdf)});
    }

    public static String getStringDateSpan(Context context, AirDate startDate, int nights) {
        SimpleDateFormat sdf = new SimpleDateFormat(context.getString(C0716R.string.date_name_format));
        String startString = startDate.formatDate((DateFormat) sdf);
        String endString = startDate.plusDays(nights).formatDate((DateFormat) sdf);
        return context.getString(C0716R.string.calendar_setting_date_range, new Object[]{startString, endString});
    }

    public static boolean isSameDay(Date date1, Date date2) {
        sCalendar.setTime(date1);
        int year1 = sCalendar.get(1);
        int day1 = sCalendar.get(6);
        sCalendar.setTime(date2);
        int year2 = sCalendar.get(1);
        int day2 = sCalendar.get(6);
        if (year1 == year2 && day1 == day2) {
            return true;
        }
        return false;
    }

    public static boolean is24Hour(Context context) {
        return android.text.format.DateFormat.is24HourFormat(context);
    }

    public static String getMonthYearRangeString(Context context, Date startDate, Date endDate) {
        SimpleDateFormat sdf = new SimpleDateFormat(context.getString(C0716R.string.month_name_short_format));
        return context.getString(C0716R.string.calendar_setting_date_range, new Object[]{sdf.format(startDate), sdf.format(endDate)});
    }

    public static String getMonthDateRangeString(Context context, Date startDate, Date endDate) {
        SimpleDateFormat sdf = new SimpleDateFormat(context.getString(C0716R.string.date_name_format));
        return context.getString(C0716R.string.calendar_setting_date_range, new Object[]{sdf.format(startDate), sdf.format(endDate)});
    }

    public static String getMonthDateYearRangeString(Context context, Date startDate, Date endDate) {
        SimpleDateFormat sdf = new SimpleDateFormat(context.getString(C0716R.string.mdy_format_shorter));
        return context.getString(C0716R.string.calendar_setting_date_range, new Object[]{sdf.format(startDate), sdf.format(endDate)});
    }

    public static String getDayOfWeekFromIndex(Context context, Integer index) {
        if (index == null || index.intValue() == -1) {
            return context.getString(C0716R.string.any_day_of_week);
        }
        switch (index.intValue()) {
            case 0:
                return context.getString(C0716R.string.sunday);
            case 1:
                return context.getString(C0716R.string.monday);
            case 2:
                return context.getString(C0716R.string.tuesday);
            case 3:
                return context.getString(C0716R.string.wednesday);
            case 4:
                return context.getString(C0716R.string.thursday);
            case 5:
                return context.getString(C0716R.string.friday);
            case 6:
                return context.getString(C0716R.string.saturday);
            default:
                throw new IllegalArgumentException("unknown day of week index: " + index);
        }
    }

    public static String formatHourOfDay(Context context, int hour, boolean specifyNoon) {
        SimpleDateFormat format;
        if (hour < 0 || hour > 24) {
            throw new IllegalArgumentException("Invalid hour: " + hour);
        }
        sCalendar.set(12, 0);
        sCalendar.set(11, hour);
        Resources res = context.getResources();
        boolean is24HourFormat = is24Hour(context);
        if (is24HourFormat) {
            format = new SimpleDateFormat(res.getString(C0716R.string.time_short_format_24_hour));
        } else {
            format = new SimpleDateFormat(res.getString(C0716R.string.time_short_format_12_hour));
        }
        String time = format.format(sCalendar.getTime());
        if (is24HourFormat || !specifyNoon) {
            return time;
        }
        if (hour == 12) {
            return res.getString(C0716R.string.ml_noon, new Object[]{time});
        } else if (hour != 24 && hour != 0) {
            return time;
        } else {
            return res.getString(C0716R.string.ml_midnight, new Object[]{time});
        }
    }

    public static int getStayDuration(AirDate checkInDate, AirDate checkOutDate) {
        if (checkInDate == null || checkOutDate == null) {
            return 0;
        }
        return checkInDate.getDaysUntil(checkOutDate);
    }

    public static MutableDateTime forDate(int year, int month, int day) {
        DateTime dt;
        LocalDateTime localDateTime = new LocalDateTime(year, month, day, 0, 0, 0, 1);
        try {
            dt = localDateTime.toDateTime();
        } catch (IllegalInstantException e) {
            dt = localDateTime.toLocalDate().toDateTimeAtStartOfDay().plusMillis(1);
        }
        return new MutableDateTime(dt);
    }

    @Deprecated
    public static AirDate toAirDate(Date date) {
        return new AirDate(date.getTime());
    }

    @Deprecated
    public static Date fromAirDateToDate(AirDate date) {
        return new Date(date.getTimeInMillisAtStartOfDay());
    }
}
