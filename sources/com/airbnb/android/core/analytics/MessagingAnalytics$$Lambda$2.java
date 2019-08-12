package com.airbnb.android.core.analytics;

import com.airbnb.android.core.analytics.MessagingAnalytics.Action;
import com.airbnb.android.core.analytics.MessagingAnalytics.Status;
import p032rx.functions.Action0;

final /* synthetic */ class MessagingAnalytics$$Lambda$2 implements Action0 {
    private final Action arg$1;

    private MessagingAnalytics$$Lambda$2(Action action) {
        this.arg$1 = action;
    }

    public static Action0 lambdaFactory$(Action action) {
        return new MessagingAnalytics$$Lambda$2(action);
    }

    public void call() {
        MessagingAnalytics.logOperationStatus(this.arg$1, Status.Attempt);
    }
}
