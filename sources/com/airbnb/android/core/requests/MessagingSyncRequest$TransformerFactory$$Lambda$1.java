package com.airbnb.android.core.requests;

import com.airbnb.jitney.event.logging.core.request.p026v1.RequestState;
import p032rx.functions.Action1;

final /* synthetic */ class MessagingSyncRequest$TransformerFactory$$Lambda$1 implements Action1 {
    private final MessagingSyncRequest arg$1;

    private MessagingSyncRequest$TransformerFactory$$Lambda$1(MessagingSyncRequest messagingSyncRequest) {
        this.arg$1 = messagingSyncRequest;
    }

    public static Action1 lambdaFactory$(MessagingSyncRequest messagingSyncRequest) {
        return new MessagingSyncRequest$TransformerFactory$$Lambda$1(messagingSyncRequest);
    }

    public void call(Object obj) {
        this.arg$1.jitneyLogger.logSyncFetch(this.arg$1.inboxType, (RequestState) obj);
    }
}
