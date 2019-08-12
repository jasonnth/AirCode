package com.airbnb.android.hostcalendar.fragments;

import com.airbnb.p027n2.components.bottom_sheet.BottomSheetItemClickListener;
import com.airbnb.p027n2.components.bottom_sheet.BottomSheetMenuItem;

final /* synthetic */ class AgendaCalendarFragment$$Lambda$11 implements BottomSheetItemClickListener {
    private final AgendaCalendarFragment arg$1;

    private AgendaCalendarFragment$$Lambda$11(AgendaCalendarFragment agendaCalendarFragment) {
        this.arg$1 = agendaCalendarFragment;
    }

    public static BottomSheetItemClickListener lambdaFactory$(AgendaCalendarFragment agendaCalendarFragment) {
        return new AgendaCalendarFragment$$Lambda$11(agendaCalendarFragment);
    }

    public void onBottomSheetItemClick(BottomSheetMenuItem bottomSheetMenuItem) {
        AgendaCalendarFragment.lambda$new$13(this.arg$1, bottomSheetMenuItem);
    }
}
