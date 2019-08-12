package com.airbnb.android.sms;

import android.content.Intent;
import java.util.concurrent.Callable;

final /* synthetic */ class SMSReceiver$$Lambda$5 implements Callable {
    private final Intent arg$1;

    private SMSReceiver$$Lambda$5(Intent intent) {
        this.arg$1 = intent;
    }

    public static Callable lambdaFactory$(Intent intent) {
        return new SMSReceiver$$Lambda$5(intent);
    }

    public Object call() {
        return SMSReceiver.lambda$onReceive$2(this.arg$1);
    }
}
