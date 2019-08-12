package com.airbnb.android.lib.fragments.alerts;

import com.airbnb.android.core.interfaces.OnHomeListener;

final /* synthetic */ class AlertsFragment$$Lambda$9 implements OnHomeListener {
    private final AlertsFragment arg$1;

    private AlertsFragment$$Lambda$9(AlertsFragment alertsFragment) {
        this.arg$1 = alertsFragment;
    }

    public static OnHomeListener lambdaFactory$(AlertsFragment alertsFragment) {
        return new AlertsFragment$$Lambda$9(alertsFragment);
    }

    public boolean onHomePressed() {
        return this.arg$1.setResultAndFinish();
    }
}
