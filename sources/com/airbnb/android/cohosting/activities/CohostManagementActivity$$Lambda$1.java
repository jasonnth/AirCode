package com.airbnb.android.cohosting.activities;

import com.airbnb.android.core.responses.AirBatchResponse;
import p032rx.functions.Action1;

final /* synthetic */ class CohostManagementActivity$$Lambda$1 implements Action1 {
    private final CohostManagementActivity arg$1;

    private CohostManagementActivity$$Lambda$1(CohostManagementActivity cohostManagementActivity) {
        this.arg$1 = cohostManagementActivity;
    }

    public static Action1 lambdaFactory$(CohostManagementActivity cohostManagementActivity) {
        return new CohostManagementActivity$$Lambda$1(cohostManagementActivity);
    }

    public void call(Object obj) {
        this.arg$1.responseFinished((AirBatchResponse) obj);
    }
}
