package com.airbnb.android.sharedcalendar.listeners;

import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.calendar.CalendarDays.OnChangeListener;
import com.airbnb.android.core.models.CalendarDay;
import java.util.Set;

public interface SingleCalendarListener {
    void addSelectedDaysChangeListener(OnChangeListener onChangeListener);

    Set<AirDate> getSelectedDates();

    void goToMessageThread(long j);

    void goToReservation(String str);

    void onDayClick(CalendarDay calendarDay);

    void removeSelectedDaysChangeListener(OnChangeListener onChangeListener);
}
