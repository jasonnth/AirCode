package com.airbnb.android.core.messaging;

final /* synthetic */ class InboxUnreadCountManager$$Lambda$2 implements Runnable {
    private final InboxUnreadCountManager arg$1;

    private InboxUnreadCountManager$$Lambda$2(InboxUnreadCountManager inboxUnreadCountManager) {
        this.arg$1 = inboxUnreadCountManager;
    }

    public static Runnable lambdaFactory$(InboxUnreadCountManager inboxUnreadCountManager) {
        return new InboxUnreadCountManager$$Lambda$2(inboxUnreadCountManager);
    }

    public void run() {
        InboxUnreadCountManager.lambda$setUnreadCount$1(this.arg$1);
    }
}
