package com.airbnb.android.core.analytics;

import com.airbnb.jitney.event.logging.core.request.p026v1.RequestState;
import p032rx.functions.Action1;

final /* synthetic */ class MessagingJitneyLogger$$Lambda$4 implements Action1 {
    private final Action1 arg$1;

    private MessagingJitneyLogger$$Lambda$4(Action1 action1) {
        this.arg$1 = action1;
    }

    public static Action1 lambdaFactory$(Action1 action1) {
        return new MessagingJitneyLogger$$Lambda$4(action1);
    }

    public void call(Object obj) {
        this.arg$1.call(RequestState.Failure);
    }
}
