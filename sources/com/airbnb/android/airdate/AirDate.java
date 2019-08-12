package com.airbnb.android.airdate;

import android.content.Context;
import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import net.danlew.android.joda.DateUtils;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.Months;
import org.joda.time.Period;
import org.joda.time.ReadablePartial;
import org.joda.time.Weeks;
import org.joda.time.Years;
import org.joda.time.format.ISODateTimeFormat;

public class AirDate implements Parcelable, Comparable<AirDate> {
    public static final Creator<AirDate> CREATOR = new Creator<AirDate>() {
        public AirDate createFromParcel(Parcel in) {
            return new AirDate(in);
        }

        public AirDate[] newArray(int size) {
            return new AirDate[size];
        }
    };
    public static final int NUM_DAYS_IN_WEEK = 7;
    private final LocalDate date;

    public AirDate(int year, int monthofYear, int dayOfMonth) {
        this.date = new LocalDate(year, monthofYear, dayOfMonth);
    }

    public AirDate(long timestamp) {
        this.date = new LocalDate(timestamp);
    }

    public AirDate(String isoDateString) {
        this(LocalDate.parse(isoDateString, ISODateTimeFormat.date()));
    }

    AirDate(LocalDate localDate) {
        this.date = localDate;
    }

    private AirDate(Parcel parcel) {
        this(parcel.readInt(), parcel.readInt(), parcel.readInt());
    }

    public static AirDate fromNextDayOfWeek(DayOfWeek dayOfWeek) {
        LocalDate date2 = LocalDate.now();
        if (date2.getDayOfWeek() > dayOfWeek.getDayIndex()) {
            date2 = date2.plusWeeks(1);
        }
        return new AirDate(date2.withDayOfWeek(dayOfWeek.getDayIndex()));
    }

    public static AirDate fromCalendar(Calendar calendar) {
        if (calendar == null) {
            return null;
        }
        return new AirDate(calendar.getTimeInMillis());
    }

    public static AirDate fromISODateString(String isoDateString) {
        if (!TextUtils.isEmpty(isoDateString)) {
            try {
                return new AirDate(isoDateString);
            } catch (IllegalArgumentException e) {
            }
        }
        return null;
    }

    public static AirDate fromLastDayOfWeek(DayOfWeek dayOfWeek) {
        LocalDate date2 = LocalDate.now();
        if (date2.getDayOfWeek() < dayOfWeek.getDayIndex()) {
            date2 = date2.plusWeeks(-1);
        }
        return new AirDate(date2.withDayOfWeek(dayOfWeek.getDayIndex()));
    }

    public static AirDate yesterday() {
        return today().plusDays(-1);
    }

    public LocalDate getLocalDate() {
        return this.date;
    }

    public AirDate plusYears(int years) {
        return new AirDate(this.date.plusYears(years));
    }

    public AirDate plusMonths(int months) {
        return new AirDate(this.date.plusMonths(months));
    }

    public AirDate plusDays(int days) {
        return new AirDate(this.date.plusDays(days));
    }

    public AirDate plusWeeks(int weeks) {
        return new AirDate(this.date.plusWeeks(weeks));
    }

    public static AirDate today() {
        return new AirDate(LocalDate.now());
    }

    public static boolean isToday(AirDate date2) {
        return today().isSameDay(date2);
    }

    public String getDateSpanString(Context context, AirDate endDate) {
        return DateUtils.formatDateRange(context, (ReadablePartial) this.date, (ReadablePartial) endDate.date, 65552);
    }

    public String getDateString(Context context) {
        return DateUtils.formatDateTime(context, (ReadablePartial) this.date, 65552);
    }

    public String getDateSpanStringWithDayOfWeek(Context context, AirDate endDate) {
        return DateUtils.formatDateRange(context, (ReadablePartial) this.date, (ReadablePartial) endDate.date, 98322);
    }

    public String getDateSpanString(Context context, int days) {
        return getDateSpanString(context, new AirDate(this.date.plusDays(days)));
    }

    public String getDateSpanStringWithDayOfWeek(Context context, int days) {
        return getDateSpanStringWithDayOfWeek(context, new AirDate(this.date.plusDays(days)));
    }

    public String getRelativeDateStringFromNow(Context context) {
        return getRelativeDateString(context, today());
    }

    public String getRelativeDateString(Context context, AirDate otherDate) {
        Period period = new Period((ReadablePartial) otherDate.date, (ReadablePartial) this.date);
        int months = period.getMonths();
        if (months > 0) {
            return context.getResources().getQuantityString(C1647R.plurals.in_x_months, months, new Object[]{Integer.valueOf(months)});
        }
        int weeks = period.getWeeks();
        if (weeks > 0) {
            return context.getResources().getQuantityString(C1647R.plurals.in_x_weeks, weeks, new Object[]{Integer.valueOf(weeks)});
        }
        int days = period.getDays();
        if (days == 0) {
            return context.getString(C1647R.string.today);
        }
        if (days == 1) {
            return context.getString(C1647R.string.tomorrow);
        }
        return this.date.dayOfWeek().getAsText(Locale.getDefault());
    }

    public String getDayOfWeekString(Context context, boolean abbrev) {
        int flags = 2;
        if (abbrev) {
            flags = 2 | 32768;
        }
        return DateUtils.formatDateTime(context, (ReadablePartial) this.date, flags);
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        return this.date.equals(((AirDate) o).date);
    }

    public int hashCode() {
        return this.date.hashCode();
    }

    public int compareTo(AirDate another) {
        return this.date.compareTo((ReadablePartial) another.date);
    }

    public boolean isSameDayOrAfter(AirDate another) {
        return compareTo(another) >= 0;
    }

    public boolean isAfter(AirDate another) {
        return compareTo(another) > 0;
    }

    public boolean isSameDayOrBefore(AirDate another) {
        return compareTo(another) <= 0;
    }

    public boolean isBefore(AirDate another) {
        return compareTo(another) < 0;
    }

    public boolean isSameDay(AirDate another) {
        return compareTo(another) == 0;
    }

    public static boolean isInPast(AirDate date2) {
        return today().isAfter(date2);
    }

    public boolean isWithinOneWeekInFuture() {
        AirDate today = today();
        return new AirDateInterval(today, today.plusDays(7)).contains(this);
    }

    public int getDaysUntil(AirDate endDate) {
        return Days.daysBetween((ReadablePartial) this.date, (ReadablePartial) endDate.date).getDays();
    }

    public int getWeeksUntil(AirDate endDate) {
        return Weeks.weeksBetween((ReadablePartial) this.date, (ReadablePartial) endDate.date).getWeeks();
    }

    public int getYearsUntil(AirDate endDate) {
        return Years.yearsBetween((ReadablePartial) this.date, (ReadablePartial) endDate.date).getYears();
    }

    public int getMonthsUntil(AirDate otherDate) {
        return Months.monthsBetween((ReadablePartial) this.date, (ReadablePartial) otherDate.date).getMonths();
    }

    public int getYear() {
        return this.date.getYear();
    }

    public int getMonthOfYear() {
        return this.date.getMonthOfYear();
    }

    public int getDayOfMonth() {
        return this.date.getDayOfMonth();
    }

    public String formatDate(String pattern) {
        return formatDate(pattern, Locale.getDefault());
    }

    public String formatDate(String pattern, Locale locale) {
        return formatDate((DateFormat) new SimpleDateFormat(pattern, locale));
    }

    public String formatDate(DateFormat sdf) {
        return sdf.format(new GregorianCalendar(getYear(), getMonthOfYear() - 1, getDayOfMonth()).getTime());
    }

    public String getIsoDateString() {
        return this.date.toString();
    }

    public long getTimeInMillisAtStartOfDay() {
        return this.date.toDateTimeAtStartOfDay().getMillis();
    }

    public DayOfWeek getDayOfWeek() {
        return DayOfWeek.getDayOfWeek(this.date.getDayOfWeek());
    }

    public static DayOfWeek getDayOfWeekFromCalendar() {
        return DayOfWeek.getDayOfWeek(((Calendar.getInstance().getFirstDayOfWeek() + 5) % 7) + 1);
    }

    public String getShortDayOfWeek(Context context) {
        if (VERSION.SDK_INT > 17) {
            return formatDate(context.getString(C1647R.string.single_letter_day_of_week_format));
        }
        return android.text.format.DateUtils.getDayOfWeekString((this.date.getDayOfWeek() % 7) + 1, 50);
    }

    public int getDaysFromDayOfWeek(DayOfWeek dayOfWeek) {
        int gap = this.date.getDayOfWeek() - dayOfWeek.getDayIndex();
        return gap < 0 ? gap + 7 : gap;
    }

    public static AirDate getFirstDayOfMonth(int year, int month) {
        return new AirDate(year, month, 1);
    }

    public AirDate getFirstDayOfMonth() {
        return new AirDate(this.date.getYear(), this.date.getMonthOfYear(), 1);
    }

    public AirDate getLastDayOfMonth() {
        return getFirstDayOfMonth().plusMonths(1).plusDays(-1);
    }

    public int getDaysInMonth() {
        return this.date.dayOfMonth().getMaximumValue();
    }

    public boolean isBetweenInclusive(AirDate startDate, AirDate endDate) {
        return isSameDayOrAfter(startDate) && isSameDayOrBefore(endDate);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(getYear());
        dest.writeInt(getMonthOfYear());
        dest.writeInt(getDayOfMonth());
    }
}
