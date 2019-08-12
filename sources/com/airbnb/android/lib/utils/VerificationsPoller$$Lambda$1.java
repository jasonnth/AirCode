package com.airbnb.android.lib.utils;

import com.airbnb.android.core.responses.VerificationsResponse;
import p032rx.functions.Action1;

final /* synthetic */ class VerificationsPoller$$Lambda$1 implements Action1 {
    private final VerificationsPoller arg$1;

    private VerificationsPoller$$Lambda$1(VerificationsPoller verificationsPoller) {
        this.arg$1 = verificationsPoller;
    }

    public static Action1 lambdaFactory$(VerificationsPoller verificationsPoller) {
        return new VerificationsPoller$$Lambda$1(verificationsPoller);
    }

    public void call(Object obj) {
        VerificationsPoller.lambda$new$0(this.arg$1, (VerificationsResponse) obj);
    }
}
