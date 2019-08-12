package com.airbnb.android.core.messaging;

import com.airbnb.android.core.models.Thread;
import p032rx.Observable;
import p032rx.functions.Func1;

final /* synthetic */ class MessageStoreThreadRequest$$Lambda$5 implements Func1 {
    private final MessageStoreThreadRequest arg$1;
    private final Observable arg$2;

    private MessageStoreThreadRequest$$Lambda$5(MessageStoreThreadRequest messageStoreThreadRequest, Observable observable) {
        this.arg$1 = messageStoreThreadRequest;
        this.arg$2 = observable;
    }

    public static Func1 lambdaFactory$(MessageStoreThreadRequest messageStoreThreadRequest, Observable observable) {
        return new MessageStoreThreadRequest$$Lambda$5(messageStoreThreadRequest, observable);
    }

    public Object call(Object obj) {
        return this.arg$1.handleDatabaseResponse(this.arg$2, (Thread) obj);
    }
}
