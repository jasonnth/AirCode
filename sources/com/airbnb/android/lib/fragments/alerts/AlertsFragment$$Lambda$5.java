package com.airbnb.android.lib.fragments.alerts;

import com.airbnb.android.core.utils.NetworkUtil;
import p032rx.functions.Action1;

final /* synthetic */ class AlertsFragment$$Lambda$5 implements Action1 {
    private final AlertsFragment arg$1;

    private AlertsFragment$$Lambda$5(AlertsFragment alertsFragment) {
        this.arg$1 = alertsFragment;
    }

    public static Action1 lambdaFactory$(AlertsFragment alertsFragment) {
        return new AlertsFragment$$Lambda$5(alertsFragment);
    }

    public void call(Object obj) {
        NetworkUtil.toastGenericNetworkError(this.arg$1.getActivity());
    }
}
