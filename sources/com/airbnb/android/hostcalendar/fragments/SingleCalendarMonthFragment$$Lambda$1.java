package com.airbnb.android.hostcalendar.fragments;

import android.view.View;
import com.airbnb.android.sharedcalendar.listeners.OnboardingOverlayListener;

final /* synthetic */ class SingleCalendarMonthFragment$$Lambda$1 implements OnboardingOverlayListener {
    private final SingleCalendarMonthFragment arg$1;

    private SingleCalendarMonthFragment$$Lambda$1(SingleCalendarMonthFragment singleCalendarMonthFragment) {
        this.arg$1 = singleCalendarMonthFragment;
    }

    public static OnboardingOverlayListener lambdaFactory$(SingleCalendarMonthFragment singleCalendarMonthFragment) {
        return new SingleCalendarMonthFragment$$Lambda$1(singleCalendarMonthFragment);
    }

    public void showOnboardingOverlay(View view) {
        this.arg$1.setUpOnboardingOverlay(view);
    }
}
