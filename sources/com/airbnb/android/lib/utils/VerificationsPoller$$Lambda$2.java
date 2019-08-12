package com.airbnb.android.lib.utils;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class VerificationsPoller$$Lambda$2 implements Action1 {
    private final VerificationsPoller arg$1;

    private VerificationsPoller$$Lambda$2(VerificationsPoller verificationsPoller) {
        this.arg$1 = verificationsPoller;
    }

    public static Action1 lambdaFactory$(VerificationsPoller verificationsPoller) {
        return new VerificationsPoller$$Lambda$2(verificationsPoller);
    }

    public void call(Object obj) {
        VerificationsPoller.lambda$new$1(this.arg$1, (AirRequestNetworkException) obj);
    }
}
