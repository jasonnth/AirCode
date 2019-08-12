package com.airbnb.android.sms;

import p032rx.functions.Action1;

final /* synthetic */ class SMSReceiver$$Lambda$8 implements Action1 {
    private static final SMSReceiver$$Lambda$8 instance = new SMSReceiver$$Lambda$8();

    private SMSReceiver$$Lambda$8() {
    }

    public static Action1 lambdaFactory$() {
        return instance;
    }

    public void call(Object obj) {
        SMSReceiver.lambda$onReceive$4((Throwable) obj);
    }
}
