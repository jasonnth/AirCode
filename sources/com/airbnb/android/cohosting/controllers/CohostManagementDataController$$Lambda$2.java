package com.airbnb.android.cohosting.controllers;

import com.airbnb.android.cohosting.controllers.CohostManagementDataController.UpdateListener;
import p032rx.functions.Action1;

final /* synthetic */ class CohostManagementDataController$$Lambda$2 implements Action1 {
    private static final CohostManagementDataController$$Lambda$2 instance = new CohostManagementDataController$$Lambda$2();

    private CohostManagementDataController$$Lambda$2() {
    }

    public static Action1 lambdaFactory$() {
        return instance;
    }

    public void call(Object obj) {
        ((UpdateListener) obj).dataUpdated();
    }
}
