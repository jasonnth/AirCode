package com.airbnb.android.core.analytics;

import com.airbnb.jitney.event.logging.core.request.p026v1.RequestState;
import p032rx.functions.Action0;
import p032rx.functions.Action1;

final /* synthetic */ class MessagingJitneyLogger$$Lambda$2 implements Action0 {
    private final Action1 arg$1;

    private MessagingJitneyLogger$$Lambda$2(Action1 action1) {
        this.arg$1 = action1;
    }

    public static Action0 lambdaFactory$(Action1 action1) {
        return new MessagingJitneyLogger$$Lambda$2(action1);
    }

    public void call() {
        this.arg$1.call(RequestState.Attempt);
    }
}
