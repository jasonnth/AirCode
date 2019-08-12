package com.airbnb.android.core.messaging;

import com.airbnb.airrequest.AirResponse;
import p032rx.functions.Action1;

final /* synthetic */ class MessageStoreInboxRequest$$Lambda$2 implements Action1 {
    private final MessageStoreInboxRequest arg$1;

    private MessageStoreInboxRequest$$Lambda$2(MessageStoreInboxRequest messageStoreInboxRequest) {
        this.arg$1 = messageStoreInboxRequest;
    }

    public static Action1 lambdaFactory$(MessageStoreInboxRequest messageStoreInboxRequest) {
        return new MessageStoreInboxRequest$$Lambda$2(messageStoreInboxRequest);
    }

    public void call(Object obj) {
        this.arg$1.storeResponse((AirResponse) obj);
    }
}
