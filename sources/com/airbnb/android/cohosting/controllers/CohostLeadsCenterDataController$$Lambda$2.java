package com.airbnb.android.cohosting.controllers;

import com.airbnb.android.cohosting.controllers.CohostLeadsCenterDataController.UpdateListener;
import p032rx.functions.Action1;

final /* synthetic */ class CohostLeadsCenterDataController$$Lambda$2 implements Action1 {
    private final boolean arg$1;

    private CohostLeadsCenterDataController$$Lambda$2(boolean z) {
        this.arg$1 = z;
    }

    public static Action1 lambdaFactory$(boolean z) {
        return new CohostLeadsCenterDataController$$Lambda$2(z);
    }

    public void call(Object obj) {
        ((UpdateListener) obj).dataLoading(this.arg$1);
    }
}
