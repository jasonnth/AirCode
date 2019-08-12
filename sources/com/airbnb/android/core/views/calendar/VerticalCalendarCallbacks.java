package com.airbnb.android.core.views.calendar;

import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.calendar.AvailabilityController.UnavailabilityType;

public interface VerticalCalendarCallbacks {
    void onDateRangeSelected(DateRangeModel dateRangeModel);

    void onEndDateClicked(AirDate airDate);

    void onStartDateClicked(AirDate airDate);

    boolean onUnavailableDateClicked(UnavailabilityType unavailabilityType, AirDate airDate, int[] iArr);
}
