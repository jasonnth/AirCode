package com.airbnb.android.lib.adapters.viewholders;

import com.airbnb.android.core.models.DashboardAlert;
import com.google.common.base.Predicate;

final /* synthetic */ class AlertViewModelFactory$$Lambda$1 implements Predicate {
    private static final AlertViewModelFactory$$Lambda$1 instance = new AlertViewModelFactory$$Lambda$1();

    private AlertViewModelFactory$$Lambda$1() {
    }

    public static Predicate lambdaFactory$() {
        return instance;
    }

    public boolean apply(Object obj) {
        return AlertViewModelFactory.isSupportedAlert((DashboardAlert) obj);
    }
}
