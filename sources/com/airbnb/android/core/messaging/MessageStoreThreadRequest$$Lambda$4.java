package com.airbnb.android.core.messaging;

import java.util.concurrent.Callable;

final /* synthetic */ class MessageStoreThreadRequest$$Lambda$4 implements Callable {
    private final MessageStoreThreadRequest arg$1;

    private MessageStoreThreadRequest$$Lambda$4(MessageStoreThreadRequest messageStoreThreadRequest) {
        this.arg$1 = messageStoreThreadRequest;
    }

    public static Callable lambdaFactory$(MessageStoreThreadRequest messageStoreThreadRequest) {
        return new MessageStoreThreadRequest$$Lambda$4(messageStoreThreadRequest);
    }

    public Object call() {
        return this.arg$1.getStoredThread();
    }
}
