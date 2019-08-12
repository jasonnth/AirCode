package com.airbnb.android.sms;

import android.support.p000v4.app.Fragment;
import p032rx.functions.Func1;

final /* synthetic */ class SMSMonitor$$Lambda$2 implements Func1 {
    private final SMSMonitor arg$1;
    private final Fragment arg$2;

    private SMSMonitor$$Lambda$2(SMSMonitor sMSMonitor, Fragment fragment) {
        this.arg$1 = sMSMonitor;
        this.arg$2 = fragment;
    }

    public static Func1 lambdaFactory$(SMSMonitor sMSMonitor, Fragment fragment) {
        return new SMSMonitor$$Lambda$2(sMSMonitor, fragment);
    }

    public Object call(Object obj) {
        return SMSMonitorAnalytics.trackStart(this.arg$2.getClass().getSimpleName());
    }
}
