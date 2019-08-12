package com.airbnb.android.cohosting.controllers;

import com.airbnb.android.cohosting.controllers.CohostManagementDataController.UpdateListener;
import p032rx.functions.Action1;

final /* synthetic */ class CohostManagementDataController$$Lambda$1 implements Action1 {
    private final boolean arg$1;

    private CohostManagementDataController$$Lambda$1(boolean z) {
        this.arg$1 = z;
    }

    public static Action1 lambdaFactory$(boolean z) {
        return new CohostManagementDataController$$Lambda$1(z);
    }

    public void call(Object obj) {
        ((UpdateListener) obj).dataLoading(this.arg$1);
    }
}
