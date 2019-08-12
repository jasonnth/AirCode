package com.airbnb.android.hostcalendar.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class AgendaCalendarFragment$$Lambda$14 implements OnClickListener {
    private final AgendaCalendarFragment arg$1;

    private AgendaCalendarFragment$$Lambda$14(AgendaCalendarFragment agendaCalendarFragment) {
        this.arg$1 = agendaCalendarFragment;
    }

    public static OnClickListener lambdaFactory$(AgendaCalendarFragment agendaCalendarFragment) {
        return new AgendaCalendarFragment$$Lambda$14(agendaCalendarFragment);
    }

    public void onClick(View view) {
        this.arg$1.agendaAdapter.retryLoading();
    }
}
