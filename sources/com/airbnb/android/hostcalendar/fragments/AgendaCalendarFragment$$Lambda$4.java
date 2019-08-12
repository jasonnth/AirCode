package com.airbnb.android.hostcalendar.fragments;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class AgendaCalendarFragment$$Lambda$4 implements Action1 {
    private final AgendaCalendarFragment arg$1;

    private AgendaCalendarFragment$$Lambda$4(AgendaCalendarFragment agendaCalendarFragment) {
        this.arg$1 = agendaCalendarFragment;
    }

    public static Action1 lambdaFactory$(AgendaCalendarFragment agendaCalendarFragment) {
        return new AgendaCalendarFragment$$Lambda$4(agendaCalendarFragment);
    }

    public void call(Object obj) {
        AgendaCalendarFragment.lambda$new$3(this.arg$1, (AirRequestNetworkException) obj);
    }
}
