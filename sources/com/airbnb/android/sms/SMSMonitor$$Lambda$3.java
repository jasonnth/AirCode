package com.airbnb.android.sms;

import android.support.p000v4.app.Fragment;
import com.airbnb.android.sms.SMSMonitor.Type;
import p032rx.functions.Func1;

final /* synthetic */ class SMSMonitor$$Lambda$3 implements Func1 {
    private final Fragment arg$1;
    private final Type arg$2;

    private SMSMonitor$$Lambda$3(Fragment fragment, Type type) {
        this.arg$1 = fragment;
        this.arg$2 = type;
    }

    public static Func1 lambdaFactory$(Fragment fragment, Type type) {
        return new SMSMonitor$$Lambda$3(fragment, type);
    }

    public Object call(Object obj) {
        return SMSMonitorAnalytics.trackSMSReceived(this.arg$1.getClass().getSimpleName());
    }
}
