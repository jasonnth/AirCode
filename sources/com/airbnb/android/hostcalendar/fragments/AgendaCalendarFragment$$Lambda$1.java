package com.airbnb.android.hostcalendar.fragments;

import com.airbnb.android.core.responses.UpcomingReservationsResponse;
import java.util.ArrayList;
import p032rx.functions.Action1;

final /* synthetic */ class AgendaCalendarFragment$$Lambda$1 implements Action1 {
    private final AgendaCalendarFragment arg$1;

    private AgendaCalendarFragment$$Lambda$1(AgendaCalendarFragment agendaCalendarFragment) {
        this.arg$1 = agendaCalendarFragment;
    }

    public static Action1 lambdaFactory$(AgendaCalendarFragment agendaCalendarFragment) {
        return new AgendaCalendarFragment$$Lambda$1(agendaCalendarFragment);
    }

    public void call(Object obj) {
        this.arg$1.thumbnailReservations = new ArrayList<>(((UpcomingReservationsResponse) obj).reservations);
    }
}
