package com.airbnb.android.core.requests;

import com.airbnb.jitney.event.logging.core.request.p026v1.RequestState;
import p032rx.functions.Action1;

final /* synthetic */ class ThreadRequest$$Lambda$1 implements Action1 {
    private final ThreadRequest arg$1;

    private ThreadRequest$$Lambda$1(ThreadRequest threadRequest) {
        this.arg$1 = threadRequest;
    }

    public static Action1 lambdaFactory$(ThreadRequest threadRequest) {
        return new ThreadRequest$$Lambda$1(threadRequest);
    }

    public void call(Object obj) {
        this.arg$1.jitneyLogger.logThreadFetch(this.arg$1.inboxType, this.arg$1.threadId, (RequestState) obj);
    }
}
