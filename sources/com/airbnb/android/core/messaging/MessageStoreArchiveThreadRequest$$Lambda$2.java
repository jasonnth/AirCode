package com.airbnb.android.core.messaging;

import p032rx.functions.Action1;

final /* synthetic */ class MessageStoreArchiveThreadRequest$$Lambda$2 implements Action1 {
    private final MessageStoreArchiveThreadRequest arg$1;

    private MessageStoreArchiveThreadRequest$$Lambda$2(MessageStoreArchiveThreadRequest messageStoreArchiveThreadRequest) {
        this.arg$1 = messageStoreArchiveThreadRequest;
    }

    public static Action1 lambdaFactory$(MessageStoreArchiveThreadRequest messageStoreArchiveThreadRequest) {
        return new MessageStoreArchiveThreadRequest$$Lambda$2(messageStoreArchiveThreadRequest);
    }

    public void call(Object obj) {
        this.arg$1.onSuccess();
    }
}
