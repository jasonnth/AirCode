package com.airbnb.android.core.analytics;

import com.airbnb.airrequest.AirResponse;
import com.airbnb.android.core.analytics.MessagingAnalytics.Action;
import p032rx.functions.Action1;

final /* synthetic */ class MessagingAnalytics$$Lambda$3 implements Action1 {
    private final Action arg$1;

    private MessagingAnalytics$$Lambda$3(Action action) {
        this.arg$1 = action;
    }

    public static Action1 lambdaFactory$(Action action) {
        return new MessagingAnalytics$$Lambda$3(action);
    }

    public void call(Object obj) {
        MessagingAnalytics.lambda$null$1(this.arg$1, (AirResponse) obj);
    }
}
