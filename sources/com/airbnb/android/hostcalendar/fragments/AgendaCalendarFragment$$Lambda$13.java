package com.airbnb.android.hostcalendar.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class AgendaCalendarFragment$$Lambda$13 implements OnClickListener {
    private final AgendaCalendarFragment arg$1;

    private AgendaCalendarFragment$$Lambda$13(AgendaCalendarFragment agendaCalendarFragment) {
        this.arg$1 = agendaCalendarFragment;
    }

    public static OnClickListener lambdaFactory$(AgendaCalendarFragment agendaCalendarFragment) {
        return new AgendaCalendarFragment$$Lambda$13(agendaCalendarFragment);
    }

    public void onClick(View view) {
        this.arg$1.onOptionsItemSelected(this.arg$1.settingsMenuItem);
    }
}
