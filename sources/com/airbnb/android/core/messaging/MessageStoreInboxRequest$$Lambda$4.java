package com.airbnb.android.core.messaging;

import java.util.concurrent.Callable;

final /* synthetic */ class MessageStoreInboxRequest$$Lambda$4 implements Callable {
    private final MessageStoreInboxRequest arg$1;

    private MessageStoreInboxRequest$$Lambda$4(MessageStoreInboxRequest messageStoreInboxRequest) {
        this.arg$1 = messageStoreInboxRequest;
    }

    public static Callable lambdaFactory$(MessageStoreInboxRequest messageStoreInboxRequest) {
        return new MessageStoreInboxRequest$$Lambda$4(messageStoreInboxRequest);
    }

    public Object call() {
        return this.arg$1.messageStore.getInbox(this.arg$1.inboxType, this.arg$1.threadOffset, 10);
    }
}
