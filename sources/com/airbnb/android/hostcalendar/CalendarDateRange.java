package com.airbnb.android.hostcalendar;

import android.os.Parcelable;
import com.airbnb.android.airdate.AirDate;

public abstract class CalendarDateRange implements Parcelable {
    public abstract AirDate getEndDate();

    public abstract AirDate getScrollTargetDate();

    public abstract AirDate getStartDate();

    public static CalendarDateRange create(AirDate startDate, AirDate endDate, AirDate scrollTargetDate) {
        return new AutoValue_CalendarDateRange(startDate, endDate, scrollTargetDate);
    }
}
