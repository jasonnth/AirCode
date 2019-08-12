package com.airbnb.android.hostcalendar.fragments;

import com.airbnb.android.core.responses.ListingResponse;
import p032rx.functions.Action1;

final /* synthetic */ class AgendaCalendarFragment$$Lambda$5 implements Action1 {
    private final AgendaCalendarFragment arg$1;

    private AgendaCalendarFragment$$Lambda$5(AgendaCalendarFragment agendaCalendarFragment) {
        this.arg$1 = agendaCalendarFragment;
    }

    public static Action1 lambdaFactory$(AgendaCalendarFragment agendaCalendarFragment) {
        return new AgendaCalendarFragment$$Lambda$5(agendaCalendarFragment);
    }

    public void call(Object obj) {
        AgendaCalendarFragment.lambda$new$4(this.arg$1, (ListingResponse) obj);
    }
}
