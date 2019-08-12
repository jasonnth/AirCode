package com.airbnb.android.lib.adapters;

import com.airbnb.android.core.models.DashboardAlert;
import com.google.common.base.Predicate;

final /* synthetic */ class AlertsAdapter$$Lambda$2 implements Predicate {
    private static final AlertsAdapter$$Lambda$2 instance = new AlertsAdapter$$Lambda$2();

    private AlertsAdapter$$Lambda$2() {
    }

    public static Predicate lambdaFactory$() {
        return instance;
    }

    public boolean apply(Object obj) {
        return AlertsAdapter.lambda$setAlerts$1((DashboardAlert) obj);
    }
}
