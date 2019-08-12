package com.airbnb.android.core.controllers;

import com.airbnb.android.airdate.AirDate;

public interface CalendarViewCallbacks {
    void onCalendarDatesApplied(AirDate airDate, AirDate airDate2);

    void onEndDateClicked(AirDate airDate);

    void onStartDateClicked(AirDate airDate);
}
