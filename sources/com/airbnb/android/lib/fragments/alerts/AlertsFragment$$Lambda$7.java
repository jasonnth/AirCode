package com.airbnb.android.lib.fragments.alerts;

import android.support.p000v4.widget.SwipeRefreshLayout.OnRefreshListener;

final /* synthetic */ class AlertsFragment$$Lambda$7 implements OnRefreshListener {
    private final AlertsFragment arg$1;

    private AlertsFragment$$Lambda$7(AlertsFragment alertsFragment) {
        this.arg$1 = alertsFragment;
    }

    public static OnRefreshListener lambdaFactory$(AlertsFragment alertsFragment) {
        return new AlertsFragment$$Lambda$7(alertsFragment);
    }

    public void onRefresh() {
        this.arg$1.load(true);
    }
}
