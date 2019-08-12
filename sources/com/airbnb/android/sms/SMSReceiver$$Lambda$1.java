package com.airbnb.android.sms;

import android.content.IntentFilter;
import p032rx.functions.Action0;

final /* synthetic */ class SMSReceiver$$Lambda$1 implements Action0 {
    private final SMSReceiver arg$1;

    private SMSReceiver$$Lambda$1(SMSReceiver sMSReceiver) {
        this.arg$1 = sMSReceiver;
    }

    public static Action0 lambdaFactory$(SMSReceiver sMSReceiver) {
        return new SMSReceiver$$Lambda$1(sMSReceiver);
    }

    public void call() {
        this.arg$1.context.registerReceiver(this.arg$1, new IntentFilter(SMSReceiver.SMS_RECEIVED_ACTION));
    }
}
