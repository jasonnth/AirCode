package com.airbnb.android.core.messaging;

import p032rx.functions.Func1;

final /* synthetic */ class MessageStoreThreadRequest$$Lambda$2 implements Func1 {
    private final MessageStoreThreadRequest arg$1;

    private MessageStoreThreadRequest$$Lambda$2(MessageStoreThreadRequest messageStoreThreadRequest) {
        this.arg$1 = messageStoreThreadRequest;
    }

    public static Func1 lambdaFactory$(MessageStoreThreadRequest messageStoreThreadRequest) {
        return new MessageStoreThreadRequest$$Lambda$2(messageStoreThreadRequest);
    }

    public Object call(Object obj) {
        return MessageStoreThreadRequest.inboxResponseFromLocal(this.arg$1, this.arg$1.getStoredThread());
    }
}
