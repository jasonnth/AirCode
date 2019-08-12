package com.airbnb.android.core.messaging;

import com.airbnb.airrequest.AirResponse;
import p032rx.functions.Func1;

final /* synthetic */ class SyncRequestFactory$$Lambda$1 implements Func1 {
    private static final SyncRequestFactory$$Lambda$1 instance = new SyncRequestFactory$$Lambda$1();

    private SyncRequestFactory$$Lambda$1() {
    }

    public static Func1 lambdaFactory$() {
        return instance;
    }

    public Object call(Object obj) {
        return ((AirResponse) obj).body();
    }
}
