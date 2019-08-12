package com.airbnb.android.core.requests;

import com.airbnb.jitney.event.logging.core.request.p026v1.RequestState;
import p032rx.functions.Action1;

final /* synthetic */ class InboxRequest$$Lambda$1 implements Action1 {
    private final InboxRequest arg$1;

    private InboxRequest$$Lambda$1(InboxRequest inboxRequest) {
        this.arg$1 = inboxRequest;
    }

    public static Action1 lambdaFactory$(InboxRequest inboxRequest) {
        return new InboxRequest$$Lambda$1(inboxRequest);
    }

    public void call(Object obj) {
        this.arg$1.jitneyLogger.logInboxFetch(this.arg$1.inboxType, (RequestState) obj);
    }
}
