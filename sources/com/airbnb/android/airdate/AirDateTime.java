package com.airbnb.android.airdate;

import android.content.Context;
import android.content.res.Resources;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import net.danlew.android.joda.DateUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Days;
import org.joda.time.Hours;
import org.joda.time.Minutes;
import org.joda.time.Months;
import org.joda.time.MutableDateTime;
import org.joda.time.Period;
import org.joda.time.ReadableInstant;
import org.joda.time.ReadablePartial;
import org.joda.time.Weeks;
import org.joda.time.Years;
import org.joda.time.format.ISODateTimeFormat;

public final class AirDateTime implements Parcelable, Comparable<AirDateTime> {
    public static final Creator<AirDateTime> CREATOR = new Creator<AirDateTime>() {
        public AirDateTime createFromParcel(Parcel in) {
            return new AirDateTime(in);
        }

        public AirDateTime[] newArray(int size) {
            return new AirDateTime[size];
        }
    };
    private final DateTime dateTime;

    public static AirDateTime parse(String isoDateString) {
        return new AirDateTime(DateTime.parse(isoDateString, ISODateTimeFormat.dateTimeNoMillis().withOffsetParsed()));
    }

    public static AirDateTime parseWithCurrentTimeZone(String isoDateString) {
        return new AirDateTime(DateTime.parse(isoDateString, ISODateTimeFormat.dateTimeNoMillis()));
    }

    public static AirDateTime now() {
        return new AirDateTime(DateTime.now());
    }

    public AirDateTime(long millis) {
        this.dateTime = new DateTime(millis);
    }

    public AirDateTime(int year, int monthOfYear, int dayOfMonth, int hourOfDay, int minuteOfHour, int secondOfMinute) {
        this.dateTime = new DateTime(year, monthOfYear, dayOfMonth, hourOfDay, minuteOfHour, secondOfMinute);
    }

    public AirDateTime(DateTime dateTime2) {
        this.dateTime = dateTime2;
    }

    private AirDateTime(Parcel parcel) {
        this(new DateTime(parcel.readLong(), DateTimeZone.forID(parcel.readString())));
    }

    public String getIsoDateStringUTC() {
        return this.dateTime.toString(ISODateTimeFormat.dateTimeNoMillis().withZoneUTC());
    }

    public String getIsoDateString() {
        return this.dateTime.toString(ISODateTimeFormat.dateTimeNoMillis());
    }

    public String getDateString(Context context) {
        return DateUtils.formatDateTime(context, (ReadableInstant) this.dateTime, 65552);
    }

    public String getDateStringWithWeekday(Context context) {
        return DateUtils.formatDateTime(context, (ReadableInstant) this.dateTime, 98322);
    }

    public String getTimeString(Context context) {
        return DateUtils.formatDateTime(context, (ReadableInstant) this.dateTime, 1);
    }

    public String getDateRangeString(Context context, AirDateTime endAirDateTime) {
        return DateUtils.formatDateRange(context, (ReadableInstant) this.dateTime, (ReadableInstant) endAirDateTime.dateTime, 65552);
    }

    public String getElapsedTime(Context context) {
        DateTime now = DateTime.now();
        Resources resources = context.getResources();
        int minutes = Minutes.minutesBetween(this.dateTime, now).getMinutes();
        if (minutes < 0) {
            return "";
        }
        if (minutes == 0) {
            return resources.getString(C1647R.string.just_now);
        }
        int hours = Hours.hoursBetween(this.dateTime, now).getHours();
        if (hours == 0) {
            return resources.getQuantityString(C1647R.plurals.x_minutes_ago, minutes, new Object[]{Integer.valueOf(minutes)});
        }
        int days = Days.daysBetween((ReadableInstant) this.dateTime, (ReadableInstant) now).getDays();
        if (days == 0) {
            return resources.getQuantityString(C1647R.plurals.x_hours_ago, hours, new Object[]{Integer.valueOf(hours)});
        } else if (days == 1) {
            return resources.getString(C1647R.string.yesterday);
        } else {
            int weeks = Weeks.weeksBetween((ReadableInstant) this.dateTime, (ReadableInstant) now).getWeeks();
            if (weeks == 0) {
                return resources.getQuantityString(C1647R.plurals.x_days_ago, days, new Object[]{Integer.valueOf(days)});
            }
            int months = Months.monthsBetween((ReadableInstant) this.dateTime, (ReadableInstant) now).getMonths();
            if (months == 0) {
                return resources.getQuantityString(C1647R.plurals.x_weeks_ago, weeks, new Object[]{Integer.valueOf(weeks)});
            }
            int years = Years.yearsBetween((ReadableInstant) this.dateTime, (ReadableInstant) now).getYears();
            if (years == 0) {
                return resources.getQuantityString(C1647R.plurals.x_months_ago, months, new Object[]{Integer.valueOf(months)});
            }
            return resources.getQuantityString(C1647R.plurals.x_years_ago, years, new Object[]{Integer.valueOf(years)});
        }
    }

    public String getTimeFromNow(Context context) {
        DateTime now = DateTime.now();
        Resources resources = context.getResources();
        if (now.isBefore((ReadableInstant) this.dateTime)) {
            int days = Days.daysBetween((ReadablePartial) now.toLocalDate(), (ReadablePartial) this.dateTime.toLocalDate()).getDays();
            if (days == 0) {
                if (DateUtils.isToday(this.dateTime)) {
                    return resources.getString(C1647R.string.today);
                }
                return resources.getString(C1647R.string.tomorrow);
            } else if (days == 1) {
                return resources.getString(C1647R.string.tomorrow);
            } else {
                int weeks = Weeks.weeksBetween((ReadableInstant) now, (ReadableInstant) this.dateTime).getWeeks();
                if (weeks == 0) {
                    return resources.getQuantityString(C1647R.plurals.itinerary_x_days_into_the_future, days, new Object[]{Integer.valueOf(days)});
                }
                int months = Months.monthsBetween((ReadableInstant) now, (ReadableInstant) this.dateTime).getMonths();
                if (months != 0) {
                    int years = Years.yearsBetween((ReadableInstant) now, (ReadableInstant) this.dateTime).getYears();
                    if (years == 0) {
                        return resources.getQuantityString(C1647R.plurals.itinerary_x_months_into_the_future, months, new Object[]{Integer.valueOf(months)});
                    }
                    return resources.getQuantityString(C1647R.plurals.itinerary_x_years_into_the_future, years, new Object[]{Integer.valueOf(years)});
                } else if (days % 7 != 0) {
                    return resources.getQuantityString(C1647R.plurals.itinerary_x_days_into_the_future, days, new Object[]{Integer.valueOf(days)});
                } else {
                    return resources.getQuantityString(C1647R.plurals.itinerary_x_weeks_into_the_future, weeks, new Object[]{Integer.valueOf(weeks)});
                }
            }
        } else {
            int days2 = Days.daysBetween((ReadableInstant) this.dateTime, (ReadableInstant) now).getDays();
            if (days2 == 0) {
                return DateUtils.isToday(this.dateTime) ? resources.getString(C1647R.string.today) : resources.getString(C1647R.string.yesterday);
            }
            if (days2 == 1) {
                return resources.getString(C1647R.string.yesterday);
            }
            int weeks2 = Weeks.weeksBetween((ReadableInstant) this.dateTime, (ReadableInstant) now).getWeeks();
            if (weeks2 == 0) {
                return resources.getQuantityString(C1647R.plurals.x_days_ago, days2, new Object[]{Integer.valueOf(days2)});
            }
            int months2 = Months.monthsBetween((ReadableInstant) this.dateTime, (ReadableInstant) now).getMonths();
            if (months2 == 0) {
                return resources.getQuantityString(C1647R.plurals.x_weeks_ago, weeks2, new Object[]{Integer.valueOf(weeks2)});
            }
            int years2 = Years.yearsBetween((ReadableInstant) this.dateTime, (ReadableInstant) now).getYears();
            if (years2 == 0) {
                return resources.getQuantityString(C1647R.plurals.x_months_ago, months2, new Object[]{Integer.valueOf(months2)});
            }
            return resources.getQuantityString(C1647R.plurals.x_years_ago, years2, new Object[]{Integer.valueOf(years2)});
        }
    }

    public String getExpiresAtString(Context context) {
        return getExpiresAtString(context, false);
    }

    public String getExpiresAtStringWithMaxTimeUnitOnly(Context context) {
        return getExpiresAtString(context, true);
    }

    private String getExpiresAtString(Context context, boolean displayMaxTimeUnitOnly) {
        DateTime now = DateTime.now();
        Resources res = context.getResources();
        if (!now.isAfter((ReadableInstant) this.dateTime)) {
            int minutes = Minutes.minutesBetween(now, this.dateTime).getMinutes();
            if (((long) minutes) < 60) {
                return res.getQuantityString(C1647R.plurals.expires_in_x_mins, minutes, new Object[]{Integer.valueOf(minutes)});
            }
            int hours = Hours.hoursBetween(now, this.dateTime).getHours();
            if (((long) hours) < 24) {
                int minutesLeft = minutes % 60;
                if (minutesLeft == 0 || displayMaxTimeUnitOnly) {
                    return res.getQuantityString(C1647R.plurals.expires_in_x_hrs, hours, new Object[]{Integer.valueOf(hours)});
                }
                String minutesLeftString = res.getQuantityString(C1647R.plurals.x_mins, minutesLeft, new Object[]{Integer.valueOf(minutesLeft)});
                String hoursLeftString = res.getQuantityString(C1647R.plurals.x_hrs, hours, new Object[]{Integer.valueOf(hours)});
                return res.getString(C1647R.string.expires_in_x_hrs_mins, new Object[]{hoursLeftString, minutesLeftString});
            }
            int days = Days.daysBetween((ReadableInstant) now, (ReadableInstant) this.dateTime).getDays();
            return res.getQuantityString(C1647R.plurals.expires_in_x_days, days, new Object[]{Integer.valueOf(days)});
        } else if (displayMaxTimeUnitOnly) {
            return res.getString(C1647R.string.status_timeout);
        } else {
            return "";
        }
    }

    public String getCountdown(Context context) {
        Period period = new Period((ReadableInstant) DateTime.now(), (ReadableInstant) this.dateTime);
        int minutes = period.getMinutes();
        int seconds = period.getSeconds();
        int hours = Hours.hoursBetween(DateTime.now().plusMinutes(minutes).plusSeconds(seconds), this.dateTime).getHours();
        return context.getResources().getString(C1647R.string.countdown_time_hh_mm_ss, new Object[]{Integer.valueOf(hours), Integer.valueOf(minutes), Integer.valueOf(seconds)});
    }

    public String getTimeRemaining(Context context) {
        DateTime now = DateTime.now();
        if (now.isAfter((ReadableInstant) this.dateTime)) {
            return "";
        }
        int minutes = Minutes.minutesBetween(now, this.dateTime).getMinutes();
        if (((long) minutes) < 60) {
            return context.getResources().getQuantityString(C1647R.plurals.x_mins, minutes, new Object[]{Integer.valueOf(minutes)});
        }
        int hours = Hours.hoursBetween(now, this.dateTime).getHours();
        if (((long) hours) < 24) {
            return context.getResources().getQuantityString(C1647R.plurals.x_hrs, hours, new Object[]{Integer.valueOf(hours)});
        }
        int days = Days.daysBetween((ReadableInstant) now, (ReadableInstant) this.dateTime).getDays();
        return context.getResources().getQuantityString(C1647R.plurals.x_days, days, new Object[]{Integer.valueOf(days)});
    }

    public int daysUntil(AirDateTime otherTime) {
        return Days.daysBetween((ReadableInstant) this.dateTime, (ReadableInstant) otherTime.dateTime).getDays();
    }

    public int hoursFromNow() {
        return hoursFrom(now());
    }

    public int hoursFrom(AirDateTime otherTime) {
        return Hours.hoursBetween(otherTime.dateTime, this.dateTime).getHours();
    }

    public int minutesFrom(AirDateTime otherTime) {
        return Minutes.minutesBetween(otherTime.dateTime, this.dateTime).getMinutes();
    }

    public AirDateTime plusYears(int years) {
        return new AirDateTime(this.dateTime.plusYears(years));
    }

    public AirDateTime plusMonths(int months) {
        return new AirDateTime(this.dateTime.plusMonths(months));
    }

    public AirDateTime plusDays(int days) {
        return new AirDateTime(this.dateTime.plusDays(days));
    }

    public AirDateTime plusHours(int hours) {
        return new AirDateTime(this.dateTime.plusHours(hours));
    }

    public AirDateTime plusMinutes(int minutes) {
        return new AirDateTime(this.dateTime.plusMinutes(minutes));
    }

    public AirDateTime plusSeconds(int seconds) {
        return new AirDateTime(this.dateTime.plusSeconds(seconds));
    }

    public AirDateTime nextFutureDateSameTime() {
        DateTime nowDateTime = DateTime.now();
        MutableDateTime nextDateTime = nowDateTime.toMutableDateTime();
        nextDateTime.setTime((ReadableInstant) this.dateTime.toDateTime());
        if (nextDateTime.isBefore((ReadableInstant) nowDateTime)) {
            nextDateTime.addDays(1);
        }
        return new AirDateTime(nextDateTime.toDateTime());
    }

    public AirDateTime withZone(String timeZone) {
        return new AirDateTime(this.dateTime.withZone(DateTimeZone.forID(timeZone)));
    }

    public long getMillis() {
        return this.dateTime.getMillis();
    }

    public boolean isAfter(AirDateTime another) {
        return compareTo(another) > 0;
    }

    public boolean isBefore(AirDateTime another) {
        return compareTo(another) < 0;
    }

    public String formatDate(DateFormat df) {
        return df.format(new GregorianCalendar(this.dateTime.getYear(), this.dateTime.getMonthOfYear() - 1, this.dateTime.getDayOfMonth()).getTime());
    }

    public String getTimeAgoText(Context context) {
        return Hours.hoursBetween(this.dateTime, DateTime.now()).getHours() < 24 ? format(new SimpleDateFormat("hh:mm a")) : getElapsedTime(context);
    }

    public int getYear() {
        return this.dateTime.getYear();
    }

    public int getMonthOfYear() {
        return this.dateTime.getMonthOfYear();
    }

    public int getDayOfMonth() {
        return this.dateTime.getDayOfMonth();
    }

    public boolean isAfterNow() {
        return compareTo(now()) > 0;
    }

    public boolean isBeforeNow() {
        return compareTo(now()) < 0;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        return this.dateTime.equals(((AirDateTime) o).dateTime);
    }

    public int hashCode() {
        return this.dateTime.hashCode();
    }

    public int compareTo(AirDateTime other) {
        return this.dateTime.compareTo((ReadableInstant) other.dateTime);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.dateTime.getMillis());
        dest.writeString(this.dateTime.getZone().getID());
    }

    public String format(DateFormat dateFormat) {
        return dateFormat.format(this.dateTime.toGregorianCalendar().getTime());
    }
}
