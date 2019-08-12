package com.airbnb.android.core.messaging;

import p032rx.functions.Func1;

final /* synthetic */ class MessageStoreInboxRequest$$Lambda$1 implements Func1 {
    private final MessageStoreInboxRequest arg$1;

    private MessageStoreInboxRequest$$Lambda$1(MessageStoreInboxRequest messageStoreInboxRequest) {
        this.arg$1 = messageStoreInboxRequest;
    }

    public static Func1 lambdaFactory$(MessageStoreInboxRequest messageStoreInboxRequest) {
        return new MessageStoreInboxRequest$$Lambda$1(messageStoreInboxRequest);
    }

    public Object call(Object obj) {
        return this.arg$1.inboxResponseFromLocal(this.arg$1, this.arg$1.messageStore.getInbox(this.arg$1.inboxType, null, 10));
    }
}
