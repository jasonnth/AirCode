package com.airbnb.android.core.messaging;

import java.util.List;
import p032rx.Observable;
import p032rx.functions.Func1;

final /* synthetic */ class MessageStoreInboxRequest$$Lambda$5 implements Func1 {
    private final MessageStoreInboxRequest arg$1;
    private final Observable arg$2;

    private MessageStoreInboxRequest$$Lambda$5(MessageStoreInboxRequest messageStoreInboxRequest, Observable observable) {
        this.arg$1 = messageStoreInboxRequest;
        this.arg$2 = observable;
    }

    public static Func1 lambdaFactory$(MessageStoreInboxRequest messageStoreInboxRequest, Observable observable) {
        return new MessageStoreInboxRequest$$Lambda$5(messageStoreInboxRequest, observable);
    }

    public Object call(Object obj) {
        return this.arg$1.handleDatabaseResponse(this.arg$2, (List) obj);
    }
}
