package com.airbnb.android.core.messaging;

import com.airbnb.airrequest.Transformer;
import p032rx.Observable;
import p032rx.schedulers.Schedulers;

final /* synthetic */ class MessageStoreInboxRequest$$Lambda$3 implements Transformer {
    private final MessageStoreInboxRequest arg$1;

    private MessageStoreInboxRequest$$Lambda$3(MessageStoreInboxRequest messageStoreInboxRequest) {
        this.arg$1 = messageStoreInboxRequest;
    }

    public static Transformer lambdaFactory$(MessageStoreInboxRequest messageStoreInboxRequest) {
        return new MessageStoreInboxRequest$$Lambda$3(messageStoreInboxRequest);
    }

    public Object call(Object obj) {
        return Observable.fromCallable(MessageStoreInboxRequest$$Lambda$4.lambdaFactory$(this.arg$1)).observeOn(Schedulers.m4048io()).flatMap(MessageStoreInboxRequest$$Lambda$5.lambdaFactory$(this.arg$1, (Observable) obj));
    }
}
