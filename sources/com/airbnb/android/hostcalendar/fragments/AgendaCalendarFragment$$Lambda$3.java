package com.airbnb.android.hostcalendar.fragments;

import com.airbnb.android.core.responses.AirBatchResponse;
import p032rx.functions.Action1;

final /* synthetic */ class AgendaCalendarFragment$$Lambda$3 implements Action1 {
    private final AgendaCalendarFragment arg$1;

    private AgendaCalendarFragment$$Lambda$3(AgendaCalendarFragment agendaCalendarFragment) {
        this.arg$1 = agendaCalendarFragment;
    }

    public static Action1 lambdaFactory$(AgendaCalendarFragment agendaCalendarFragment) {
        return new AgendaCalendarFragment$$Lambda$3(agendaCalendarFragment);
    }

    public void call(Object obj) {
        this.arg$1.handleBatchResponse((AirBatchResponse) obj);
    }
}
