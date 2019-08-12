package com.airbnb.android.cohosting.controllers;

import com.airbnb.android.cohosting.controllers.CohostInvitationDataController.UpdateListener;
import p032rx.functions.Action1;

final /* synthetic */ class CohostInvitationDataController$$Lambda$2 implements Action1 {
    private static final CohostInvitationDataController$$Lambda$2 instance = new CohostInvitationDataController$$Lambda$2();

    private CohostInvitationDataController$$Lambda$2() {
    }

    public static Action1 lambdaFactory$() {
        return instance;
    }

    public void call(Object obj) {
        ((UpdateListener) obj).dataUpdated();
    }
}
