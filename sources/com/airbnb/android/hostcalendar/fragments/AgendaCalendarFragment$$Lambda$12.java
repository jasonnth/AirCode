package com.airbnb.android.hostcalendar.fragments;

import android.support.p000v4.widget.SwipeRefreshLayout.OnRefreshListener;

final /* synthetic */ class AgendaCalendarFragment$$Lambda$12 implements OnRefreshListener {
    private final AgendaCalendarFragment arg$1;

    private AgendaCalendarFragment$$Lambda$12(AgendaCalendarFragment agendaCalendarFragment) {
        this.arg$1 = agendaCalendarFragment;
    }

    public static OnRefreshListener lambdaFactory$(AgendaCalendarFragment agendaCalendarFragment) {
        return new AgendaCalendarFragment$$Lambda$12(agendaCalendarFragment);
    }

    public void onRefresh() {
        AgendaCalendarFragment.lambda$onCreateView$11(this.arg$1);
    }
}
