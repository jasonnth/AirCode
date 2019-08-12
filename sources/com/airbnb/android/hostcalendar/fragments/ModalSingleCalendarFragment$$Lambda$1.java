package com.airbnb.android.hostcalendar.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class ModalSingleCalendarFragment$$Lambda$1 implements OnClickListener {
    private final ModalSingleCalendarFragment arg$1;

    private ModalSingleCalendarFragment$$Lambda$1(ModalSingleCalendarFragment modalSingleCalendarFragment) {
        this.arg$1 = modalSingleCalendarFragment;
    }

    public static OnClickListener lambdaFactory$(ModalSingleCalendarFragment modalSingleCalendarFragment) {
        return new ModalSingleCalendarFragment$$Lambda$1(modalSingleCalendarFragment);
    }

    public void onClick(View view) {
        this.arg$1.onBackPressed();
    }
}
