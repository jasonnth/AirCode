package com.airbnb.android.hostcalendar.fragments;

import com.airbnb.android.hostcalendar.adapters.CalendarAgendaAdapter.InfiniteScrollListener;

final /* synthetic */ class AgendaCalendarFragment$$Lambda$10 implements InfiniteScrollListener {
    private final AgendaCalendarFragment arg$1;

    private AgendaCalendarFragment$$Lambda$10(AgendaCalendarFragment agendaCalendarFragment) {
        this.arg$1 = agendaCalendarFragment;
    }

    public static InfiniteScrollListener lambdaFactory$(AgendaCalendarFragment agendaCalendarFragment) {
        return new AgendaCalendarFragment$$Lambda$10(agendaCalendarFragment);
    }

    public void scrollForward(int i) {
        AgendaCalendarFragment.lambda$new$10(this.arg$1, i);
    }
}
