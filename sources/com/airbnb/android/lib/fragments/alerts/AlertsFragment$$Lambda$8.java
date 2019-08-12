package com.airbnb.android.lib.fragments.alerts;

import com.airbnb.android.core.interfaces.OnBackListener;

final /* synthetic */ class AlertsFragment$$Lambda$8 implements OnBackListener {
    private final AlertsFragment arg$1;

    private AlertsFragment$$Lambda$8(AlertsFragment alertsFragment) {
        this.arg$1 = alertsFragment;
    }

    public static OnBackListener lambdaFactory$(AlertsFragment alertsFragment) {
        return new AlertsFragment$$Lambda$8(alertsFragment);
    }

    public boolean onBackPressed() {
        return this.arg$1.setResultAndFinish();
    }
}
