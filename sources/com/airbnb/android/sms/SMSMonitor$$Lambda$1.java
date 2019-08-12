package com.airbnb.android.sms;

import p032rx.functions.Func1;

final /* synthetic */ class SMSMonitor$$Lambda$1 implements Func1 {
    private static final SMSMonitor$$Lambda$1 instance = new SMSMonitor$$Lambda$1();

    private SMSMonitor$$Lambda$1() {
    }

    public static Func1 lambdaFactory$() {
        return instance;
    }

    public Object call(Object obj) {
        return SMSMonitor.lambda$listen$0((Boolean) obj);
    }
}
