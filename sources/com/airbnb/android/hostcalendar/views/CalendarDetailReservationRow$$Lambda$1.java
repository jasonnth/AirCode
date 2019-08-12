package com.airbnb.android.hostcalendar.views;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.hostcalendar.views.CalendarDetailReservationRow.CalendarDetailReservationClickListener;

final /* synthetic */ class CalendarDetailReservationRow$$Lambda$1 implements OnClickListener {
    private final CalendarDetailReservationRow arg$1;
    private final CalendarDetailReservationClickListener arg$2;

    private CalendarDetailReservationRow$$Lambda$1(CalendarDetailReservationRow calendarDetailReservationRow, CalendarDetailReservationClickListener calendarDetailReservationClickListener) {
        this.arg$1 = calendarDetailReservationRow;
        this.arg$2 = calendarDetailReservationClickListener;
    }

    public static OnClickListener lambdaFactory$(CalendarDetailReservationRow calendarDetailReservationRow, CalendarDetailReservationClickListener calendarDetailReservationClickListener) {
        return new CalendarDetailReservationRow$$Lambda$1(calendarDetailReservationRow, calendarDetailReservationClickListener);
    }

    public void onClick(View view) {
        this.arg$2.onReservationClick(this.arg$1);
    }
}
