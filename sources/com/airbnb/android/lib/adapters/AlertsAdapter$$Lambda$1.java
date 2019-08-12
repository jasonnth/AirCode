package com.airbnb.android.lib.adapters;

import com.airbnb.android.core.models.DashboardAlert;
import com.google.common.base.Predicate;

final /* synthetic */ class AlertsAdapter$$Lambda$1 implements Predicate {
    private static final AlertsAdapter$$Lambda$1 instance = new AlertsAdapter$$Lambda$1();

    private AlertsAdapter$$Lambda$1() {
    }

    public static Predicate lambdaFactory$() {
        return instance;
    }

    public boolean apply(Object obj) {
        return AlertsAdapter.lambda$setAlerts$0((DashboardAlert) obj);
    }
}
