package com.airbnb.android.hostcalendar.fragments;

import com.airbnb.android.core.models.NestedListing;
import com.airbnb.android.hostcalendar.adapters.NestedListingViewAdapter.NestedListingViewListener;

final /* synthetic */ class CalendarNestedBusyDayFragment$$Lambda$1 implements NestedListingViewListener {
    private final CalendarNestedBusyDayFragment arg$1;

    private CalendarNestedBusyDayFragment$$Lambda$1(CalendarNestedBusyDayFragment calendarNestedBusyDayFragment) {
        this.arg$1 = calendarNestedBusyDayFragment;
    }

    public static NestedListingViewListener lambdaFactory$(CalendarNestedBusyDayFragment calendarNestedBusyDayFragment) {
        return new CalendarNestedBusyDayFragment$$Lambda$1(calendarNestedBusyDayFragment);
    }

    public void onClickListing(NestedListing nestedListing) {
        this.arg$1.goToCalendar(nestedListing);
    }
}
