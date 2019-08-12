package com.airbnb.android.hostcalendar.views;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.hostcalendar.views.CalendarAgendaInfoBlock.CalendarAgendaTapListener;

final /* synthetic */ class CalendarAgendaInfoBlock$$Lambda$1 implements OnClickListener {
    private final CalendarAgendaTapListener arg$1;
    private final long arg$2;
    private final Reservation arg$3;

    private CalendarAgendaInfoBlock$$Lambda$1(CalendarAgendaTapListener calendarAgendaTapListener, long j, Reservation reservation) {
        this.arg$1 = calendarAgendaTapListener;
        this.arg$2 = j;
        this.arg$3 = reservation;
    }

    public static OnClickListener lambdaFactory$(CalendarAgendaTapListener calendarAgendaTapListener, long j, Reservation reservation) {
        return new CalendarAgendaInfoBlock$$Lambda$1(calendarAgendaTapListener, j, reservation);
    }

    public void onClick(View view) {
        this.arg$1.onReservationClick(this.arg$2, this.arg$3.getConfirmationCode());
    }
}
