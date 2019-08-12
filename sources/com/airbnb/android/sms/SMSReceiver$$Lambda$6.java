package com.airbnb.android.sms;

import p032rx.functions.Func1;

final /* synthetic */ class SMSReceiver$$Lambda$6 implements Func1 {
    private static final SMSReceiver$$Lambda$6 instance = new SMSReceiver$$Lambda$6();

    private SMSReceiver$$Lambda$6() {
    }

    public static Func1 lambdaFactory$() {
        return instance;
    }

    public Object call(Object obj) {
        return SMSReceiver.lambda$onReceive$3((String) obj);
    }
}
