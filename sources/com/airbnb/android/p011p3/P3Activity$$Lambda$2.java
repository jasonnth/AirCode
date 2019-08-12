package com.airbnb.android.p011p3;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

/* renamed from: com.airbnb.android.p3.P3Activity$$Lambda$2 */
final /* synthetic */ class P3Activity$$Lambda$2 implements Action1 {
    private static final P3Activity$$Lambda$2 instance = new P3Activity$$Lambda$2();

    private P3Activity$$Lambda$2() {
    }

    public static Action1 lambdaFactory$() {
        return instance;
    }

    public void call(Object obj) {
        P3Activity.lambda$new$1((AirRequestNetworkException) obj);
    }
}
