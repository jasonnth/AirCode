package com.airbnb.android.lib.fragments.alerts;

import com.airbnb.android.core.responses.DashboardAlertsResponse;
import java.util.ArrayList;
import p032rx.functions.Action1;

final /* synthetic */ class AlertsFragment$$Lambda$1 implements Action1 {
    private final AlertsFragment arg$1;

    private AlertsFragment$$Lambda$1(AlertsFragment alertsFragment) {
        this.arg$1 = alertsFragment;
    }

    public static Action1 lambdaFactory$(AlertsFragment alertsFragment) {
        return new AlertsFragment$$Lambda$1(alertsFragment);
    }

    public void call(Object obj) {
        this.arg$1.alertsAdapter.setAlerts(new ArrayList(((DashboardAlertsResponse) obj).dashboardAlerts), this.arg$1.isEligibleForNestedListings, this.arg$1.isActiveHost);
    }
}
