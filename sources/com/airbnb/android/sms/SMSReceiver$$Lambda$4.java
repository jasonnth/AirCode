package com.airbnb.android.sms;

import p032rx.functions.Action0;

final /* synthetic */ class SMSReceiver$$Lambda$4 implements Action0 {
    private final SMSReceiver arg$1;

    private SMSReceiver$$Lambda$4(SMSReceiver sMSReceiver) {
        this.arg$1 = sMSReceiver;
    }

    public static Action0 lambdaFactory$(SMSReceiver sMSReceiver) {
        return new SMSReceiver$$Lambda$4(sMSReceiver);
    }

    public void call() {
        this.arg$1.context.unregisterReceiver(this.arg$1);
    }
}
