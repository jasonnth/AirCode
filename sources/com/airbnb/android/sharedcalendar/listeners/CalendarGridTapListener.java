package com.airbnb.android.sharedcalendar.listeners;

import com.airbnb.android.sharedcalendar.models.CalendarGridDayModel;

public interface CalendarGridTapListener {
    void onDayClick(CalendarGridDayModel calendarGridDayModel);

    void onReservationClick(String str);
}
