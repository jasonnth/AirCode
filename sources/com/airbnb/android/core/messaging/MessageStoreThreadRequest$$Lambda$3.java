package com.airbnb.android.core.messaging;

import com.airbnb.airrequest.Transformer;
import p032rx.Observable;
import p032rx.schedulers.Schedulers;

final /* synthetic */ class MessageStoreThreadRequest$$Lambda$3 implements Transformer {
    private final MessageStoreThreadRequest arg$1;

    private MessageStoreThreadRequest$$Lambda$3(MessageStoreThreadRequest messageStoreThreadRequest) {
        this.arg$1 = messageStoreThreadRequest;
    }

    public static Transformer lambdaFactory$(MessageStoreThreadRequest messageStoreThreadRequest) {
        return new MessageStoreThreadRequest$$Lambda$3(messageStoreThreadRequest);
    }

    public Object call(Object obj) {
        return Observable.fromCallable(MessageStoreThreadRequest$$Lambda$4.lambdaFactory$(this.arg$1)).observeOn(Schedulers.m4048io()).flatMap(MessageStoreThreadRequest$$Lambda$5.lambdaFactory$(this.arg$1, (Observable) obj));
    }
}
