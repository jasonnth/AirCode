package com.airbnb.android.core.messaging;

import com.airbnb.android.core.events.MessageSyncEvent;
import com.airbnb.android.core.models.InboxType;
import com.airbnb.android.core.models.MessagingSyncs;

final /* synthetic */ class SyncRequestFactory$$Lambda$4 implements Runnable {
    private final SyncRequestFactory arg$1;
    private final InboxType arg$2;
    private final MessagingSyncs arg$3;

    private SyncRequestFactory$$Lambda$4(SyncRequestFactory syncRequestFactory, InboxType inboxType, MessagingSyncs messagingSyncs) {
        this.arg$1 = syncRequestFactory;
        this.arg$2 = inboxType;
        this.arg$3 = messagingSyncs;
    }

    public static Runnable lambdaFactory$(SyncRequestFactory syncRequestFactory, InboxType inboxType, MessagingSyncs messagingSyncs) {
        return new SyncRequestFactory$$Lambda$4(syncRequestFactory, inboxType, messagingSyncs);
    }

    public void run() {
        this.arg$1.bus.post(new MessageSyncEvent(this.arg$2, (long) this.arg$3.getUnreadCount(), this.arg$3.calculateUpdatedThreadIds()));
    }
}
