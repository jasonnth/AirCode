package com.airbnb.android.hostcalendar.fragments;

import p032rx.functions.Action1;

final /* synthetic */ class AgendaCalendarFragment$$Lambda$9 implements Action1 {
    private final AgendaCalendarFragment arg$1;

    private AgendaCalendarFragment$$Lambda$9(AgendaCalendarFragment agendaCalendarFragment) {
        this.arg$1 = agendaCalendarFragment;
    }

    public static Action1 lambdaFactory$(AgendaCalendarFragment agendaCalendarFragment) {
        return new AgendaCalendarFragment$$Lambda$9(agendaCalendarFragment);
    }

    public void call(Object obj) {
        this.arg$1.showLoader(false);
    }
}
