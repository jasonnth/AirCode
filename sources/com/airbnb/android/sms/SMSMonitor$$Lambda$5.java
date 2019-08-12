package com.airbnb.android.sms;

import android.support.p000v4.app.Fragment;
import p032rx.functions.Func1;

final /* synthetic */ class SMSMonitor$$Lambda$5 implements Func1 {
    private final Fragment arg$1;

    private SMSMonitor$$Lambda$5(Fragment fragment) {
        this.arg$1 = fragment;
    }

    public static Func1 lambdaFactory$(Fragment fragment) {
        return new SMSMonitor$$Lambda$5(fragment);
    }

    public Object call(Object obj) {
        return SMSMonitor.lambda$listen$4(this.arg$1, (String) obj);
    }
}
