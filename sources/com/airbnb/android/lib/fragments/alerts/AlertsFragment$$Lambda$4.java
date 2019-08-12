package com.airbnb.android.lib.fragments.alerts;

import com.airbnb.android.core.responses.AirBatchResponse;
import p032rx.functions.Action1;

final /* synthetic */ class AlertsFragment$$Lambda$4 implements Action1 {
    private final AlertsFragment arg$1;

    private AlertsFragment$$Lambda$4(AlertsFragment alertsFragment) {
        this.arg$1 = alertsFragment;
    }

    public static Action1 lambdaFactory$(AlertsFragment alertsFragment) {
        return new AlertsFragment$$Lambda$4(alertsFragment);
    }

    public void call(Object obj) {
        AlertsFragment.lambda$new$3(this.arg$1, (AirBatchResponse) obj);
    }
}
