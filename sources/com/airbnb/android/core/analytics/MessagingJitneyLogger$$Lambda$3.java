package com.airbnb.android.core.analytics;

import com.airbnb.airrequest.AirResponse;
import p032rx.functions.Action1;

final /* synthetic */ class MessagingJitneyLogger$$Lambda$3 implements Action1 {
    private final Action1 arg$1;

    private MessagingJitneyLogger$$Lambda$3(Action1 action1) {
        this.arg$1 = action1;
    }

    public static Action1 lambdaFactory$(Action1 action1) {
        return new MessagingJitneyLogger$$Lambda$3(action1);
    }

    public void call(Object obj) {
        MessagingJitneyLogger.lambda$null$1(this.arg$1, (AirResponse) obj);
    }
}
