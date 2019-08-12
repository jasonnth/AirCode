package com.airbnb.android.core.messaging;

import com.airbnb.android.core.requests.MessagingSyncRequest;
import com.airbnb.android.core.requests.MessagingSyncResponse;
import p032rx.functions.Action1;

final /* synthetic */ class SyncRequestFactory$$Lambda$2 implements Action1 {
    private final SyncRequestFactory arg$1;
    private final MessagingSyncRequest arg$2;

    private SyncRequestFactory$$Lambda$2(SyncRequestFactory syncRequestFactory, MessagingSyncRequest messagingSyncRequest) {
        this.arg$1 = syncRequestFactory;
        this.arg$2 = messagingSyncRequest;
    }

    public static Action1 lambdaFactory$(SyncRequestFactory syncRequestFactory, MessagingSyncRequest messagingSyncRequest) {
        return new SyncRequestFactory$$Lambda$2(syncRequestFactory, messagingSyncRequest);
    }

    public void call(Object obj) {
        this.arg$1.handleSyncResponse(this.arg$2.inboxType, this.arg$2.requestSequenceId, (MessagingSyncResponse) obj);
    }
}
