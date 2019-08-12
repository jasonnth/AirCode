package com.airbnb.android.core.messaging;

final /* synthetic */ class InboxUnreadCountManager$$Lambda$3 implements Runnable {
    private final InboxUnreadCountManager arg$1;

    private InboxUnreadCountManager$$Lambda$3(InboxUnreadCountManager inboxUnreadCountManager) {
        this.arg$1 = inboxUnreadCountManager;
    }

    public static Runnable lambdaFactory$(InboxUnreadCountManager inboxUnreadCountManager) {
        return new InboxUnreadCountManager$$Lambda$3(inboxUnreadCountManager);
    }

    public void run() {
        InboxUnreadCountManager.lambda$setUnreadCount$2(this.arg$1);
    }
}
