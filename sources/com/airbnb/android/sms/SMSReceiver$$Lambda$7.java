package com.airbnb.android.sms;

import p032rx.functions.Action1;
import p032rx.subjects.PublishSubject;

final /* synthetic */ class SMSReceiver$$Lambda$7 implements Action1 {
    private final PublishSubject arg$1;

    private SMSReceiver$$Lambda$7(PublishSubject publishSubject) {
        this.arg$1 = publishSubject;
    }

    public static Action1 lambdaFactory$(PublishSubject publishSubject) {
        return new SMSReceiver$$Lambda$7(publishSubject);
    }

    public void call(Object obj) {
        this.arg$1.onNext((String) obj);
    }
}
