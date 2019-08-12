package com.airbnb.android.core.calendar;

import android.support.p000v4.util.LongSparseArray;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.models.NightCount;

public abstract class CalendarStoreListener {
    private boolean isEnabled;

    public abstract void onError(NetworkException networkException);

    public abstract void onResponse(LongSparseArray<CalendarDays> longSparseArray, LongSparseArray<NightCount> longSparseArray2, AirDate airDate, AirDate airDate2);

    public void setEnabled(boolean enabled) {
        this.isEnabled = enabled;
    }

    public final void onCalendarResponse(LongSparseArray<CalendarDays> calendarDaysByListingId, LongSparseArray<NightCount> nightCountsByListingId, AirDate startDate, AirDate endDate) {
        if (this.isEnabled) {
            onResponse(calendarDaysByListingId, nightCountsByListingId, startDate, endDate);
        }
    }

    public final void onCalendarError(NetworkException e) {
        if (this.isEnabled) {
            onError(e);
        }
    }
}
