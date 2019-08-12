package com.airbnb.android.core.responses;

import com.airbnb.android.core.models.DashboardAlert;
import com.google.common.base.Predicate;

final /* synthetic */ class DashboardAlertsResponse$$Lambda$1 implements Predicate {
    private static final DashboardAlertsResponse$$Lambda$1 instance = new DashboardAlertsResponse$$Lambda$1();

    private DashboardAlertsResponse$$Lambda$1() {
    }

    public static Predicate lambdaFactory$() {
        return instance;
    }

    public boolean apply(Object obj) {
        return DashboardAlertsResponse.lambda$containsUnseenAlerts$0((DashboardAlert) obj);
    }
}
