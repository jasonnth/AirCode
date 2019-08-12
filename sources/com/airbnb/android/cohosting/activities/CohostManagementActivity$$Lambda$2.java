package com.airbnb.android.cohosting.activities;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class CohostManagementActivity$$Lambda$2 implements Action1 {
    private final CohostManagementActivity arg$1;

    private CohostManagementActivity$$Lambda$2(CohostManagementActivity cohostManagementActivity) {
        this.arg$1 = cohostManagementActivity;
    }

    public static Action1 lambdaFactory$(CohostManagementActivity cohostManagementActivity) {
        return new CohostManagementActivity$$Lambda$2(cohostManagementActivity);
    }

    public void call(Object obj) {
        this.arg$1.onError((AirRequestNetworkException) obj);
    }
}
