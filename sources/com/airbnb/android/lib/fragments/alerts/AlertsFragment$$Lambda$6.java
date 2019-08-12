package com.airbnb.android.lib.fragments.alerts;

import p032rx.functions.Action1;

final /* synthetic */ class AlertsFragment$$Lambda$6 implements Action1 {
    private final AlertsFragment arg$1;

    private AlertsFragment$$Lambda$6(AlertsFragment alertsFragment) {
        this.arg$1 = alertsFragment;
    }

    public static Action1 lambdaFactory$(AlertsFragment alertsFragment) {
        return new AlertsFragment$$Lambda$6(alertsFragment);
    }

    public void call(Object obj) {
        AlertsFragment.lambda$new$5(this.arg$1, (Boolean) obj);
    }
}
