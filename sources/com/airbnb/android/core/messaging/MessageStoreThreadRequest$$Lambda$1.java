package com.airbnb.android.core.messaging;

import com.airbnb.airrequest.AirResponse;
import p032rx.functions.Func1;

final /* synthetic */ class MessageStoreThreadRequest$$Lambda$1 implements Func1 {
    private final MessageStoreThreadRequest arg$1;

    private MessageStoreThreadRequest$$Lambda$1(MessageStoreThreadRequest messageStoreThreadRequest) {
        this.arg$1 = messageStoreThreadRequest;
    }

    public static Func1 lambdaFactory$(MessageStoreThreadRequest messageStoreThreadRequest) {
        return new MessageStoreThreadRequest$$Lambda$1(messageStoreThreadRequest);
    }

    public Object call(Object obj) {
        return this.arg$1.storeResponse((AirResponse) obj);
    }
}
