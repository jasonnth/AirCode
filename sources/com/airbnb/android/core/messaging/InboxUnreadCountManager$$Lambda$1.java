package com.airbnb.android.core.messaging;

import com.squareup.otto.Bus;

final /* synthetic */ class InboxUnreadCountManager$$Lambda$1 implements Runnable {
    private final InboxUnreadCountManager arg$1;
    private final Bus arg$2;

    private InboxUnreadCountManager$$Lambda$1(InboxUnreadCountManager inboxUnreadCountManager, Bus bus) {
        this.arg$1 = inboxUnreadCountManager;
        this.arg$2 = bus;
    }

    public static Runnable lambdaFactory$(InboxUnreadCountManager inboxUnreadCountManager, Bus bus) {
        return new InboxUnreadCountManager$$Lambda$1(inboxUnreadCountManager, bus);
    }

    public void run() {
        this.arg$2.register(this.arg$1);
    }
}
