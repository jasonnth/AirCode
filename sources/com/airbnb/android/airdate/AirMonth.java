package com.airbnb.android.airdate;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.Locale;
import org.joda.time.Months;
import org.joda.time.ReadablePartial;
import org.joda.time.YearMonth;

public class AirMonth implements Parcelable, Comparable<AirMonth> {
    public static final Creator<AirMonth> CREATOR = new Creator<AirMonth>() {
        public AirMonth createFromParcel(Parcel in) {
            return new AirMonth(in);
        }

        public AirMonth[] newArray(int size) {
            return new AirMonth[size];
        }
    };
    private AirDate firstDayOfMonth;
    private AirDate lastDayOfMonth;
    private final YearMonth yearMonth;

    public AirMonth(int year, int monthOfYear) {
        boolean z;
        boolean z2 = true;
        if (year <= 0) {
            throw new IllegalStateException("Year for an AirMonth must be positive, you specified: " + year);
        }
        if (monthOfYear <= 0) {
            z = true;
        } else {
            z = false;
        }
        if (monthOfYear <= 12) {
            z2 = false;
        }
        if (z2 || z) {
            throw new IllegalStateException("monthOfYear for an AirMonth must be between 1 and 12, you specified: " + monthOfYear);
        }
        this.yearMonth = new YearMonth(year, monthOfYear);
    }

    public AirMonth(AirDate date) {
        this.yearMonth = new YearMonth(date.getYear(), date.getMonthOfYear());
    }

    public AirMonth(Parcel in) {
        this(in.readInt(), in.readInt());
    }

    private AirMonth(YearMonth yearMonth2) {
        this.yearMonth = yearMonth2;
    }

    public AirMonth plusMonths(int months) {
        return new AirMonth(this.yearMonth.plusMonths(months));
    }

    public int getYear() {
        return this.yearMonth.getYear();
    }

    public int getMonth() {
        return this.yearMonth.getMonthOfYear();
    }

    public AirDate firstDayOfMonth() {
        if (this.firstDayOfMonth == null) {
            this.firstDayOfMonth = new AirDate(this.yearMonth.toLocalDate(1).dayOfMonth().withMinimumValue());
        }
        return this.firstDayOfMonth;
    }

    public AirDate lastDayOfMonth() {
        if (this.lastDayOfMonth == null) {
            this.lastDayOfMonth = new AirDate(this.yearMonth.toLocalDate(1).dayOfMonth().withMaximumValue());
        }
        return this.lastDayOfMonth;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.yearMonth.getYear());
        dest.writeInt(this.yearMonth.getMonthOfYear());
    }

    public int compareTo(AirMonth another) {
        return this.yearMonth.compareTo((ReadablePartial) another.yearMonth);
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        return this.yearMonth.equals(((AirMonth) o).yearMonth);
    }

    public int hashCode() {
        return this.yearMonth.hashCode();
    }

    public boolean isAfter(AirMonth another) {
        return compareTo(another) > 0;
    }

    public boolean isBefore(AirMonth another) {
        return compareTo(another) < 0;
    }

    public String toString() {
        return this.yearMonth.toString();
    }

    public String formatMonth(String pattern) {
        return this.yearMonth.toString(pattern);
    }

    public String formatMonth(String pattern, Locale locale) {
        return this.yearMonth.toString(pattern, locale);
    }

    public static AirMonth thisMonth() {
        return new AirMonth(YearMonth.now());
    }

    public static AirMonth getEarlierMonth(AirMonth monthOne, AirMonth monthTwo) {
        return monthOne.isBefore(monthTwo) ? monthOne : monthTwo;
    }

    public static AirMonth getLaterMonth(AirMonth monthOne, AirMonth monthTwo) {
        return monthOne.isAfter(monthTwo) ? monthOne : monthTwo;
    }

    public int getMonthsUntil(AirMonth endMonth) {
        return Months.monthsBetween((ReadablePartial) this.yearMonth, (ReadablePartial) endMonth.yearMonth).getMonths();
    }
}
