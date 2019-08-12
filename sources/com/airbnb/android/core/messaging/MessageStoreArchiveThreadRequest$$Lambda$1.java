package com.airbnb.android.core.messaging;

import com.airbnb.airrequest.Transformer;
import p032rx.Observable;

final /* synthetic */ class MessageStoreArchiveThreadRequest$$Lambda$1 implements Transformer {
    private final MessageStoreArchiveThreadRequest arg$1;

    private MessageStoreArchiveThreadRequest$$Lambda$1(MessageStoreArchiveThreadRequest messageStoreArchiveThreadRequest) {
        this.arg$1 = messageStoreArchiveThreadRequest;
    }

    public static Transformer lambdaFactory$(MessageStoreArchiveThreadRequest messageStoreArchiveThreadRequest) {
        return new MessageStoreArchiveThreadRequest$$Lambda$1(messageStoreArchiveThreadRequest);
    }

    public Object call(Object obj) {
        return ((Observable) obj).doOnNext(MessageStoreArchiveThreadRequest$$Lambda$2.lambdaFactory$(this.arg$1));
    }
}
