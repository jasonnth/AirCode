package com.airbnb.android.sms;

import p032rx.functions.Func1;

final /* synthetic */ class SMSMonitor$$Lambda$4 implements Func1 {
    private static final SMSMonitor$$Lambda$4 instance = new SMSMonitor$$Lambda$4();

    private SMSMonitor$$Lambda$4() {
    }

    public static Func1 lambdaFactory$() {
        return instance;
    }

    public Object call(Object obj) {
        return SMSMonitor.lambda$listen$3((String) obj);
    }
}
