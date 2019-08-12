package com.airbnb.android.hostcalendar.views;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.hostcalendar.views.CalendarDetailReservationRow.CalendarDetailReservationClickListener;

final /* synthetic */ class CalendarDetailReservationBlock$$Lambda$1 implements OnClickListener {
    private final CalendarDetailReservationClickListener arg$1;
    private final CalendarDetailReservationRow arg$2;

    private CalendarDetailReservationBlock$$Lambda$1(CalendarDetailReservationClickListener calendarDetailReservationClickListener, CalendarDetailReservationRow calendarDetailReservationRow) {
        this.arg$1 = calendarDetailReservationClickListener;
        this.arg$2 = calendarDetailReservationRow;
    }

    public static OnClickListener lambdaFactory$(CalendarDetailReservationClickListener calendarDetailReservationClickListener, CalendarDetailReservationRow calendarDetailReservationRow) {
        return new CalendarDetailReservationBlock$$Lambda$1(calendarDetailReservationClickListener, calendarDetailReservationRow);
    }

    public void onClick(View view) {
        this.arg$1.onMessageClick(this.arg$2);
    }
}
