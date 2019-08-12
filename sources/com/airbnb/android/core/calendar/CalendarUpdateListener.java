package com.airbnb.android.core.calendar;

import com.airbnb.airrequest.NetworkException;
import com.airbnb.android.airdate.AirDate;
import java.util.Set;

public interface CalendarUpdateListener {
    void onCalendarError(NetworkException networkException);

    void onCalendarUpdateSuccess(Set<Long> set, AirDate airDate, AirDate airDate2);
}
