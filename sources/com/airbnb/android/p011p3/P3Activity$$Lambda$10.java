package com.airbnb.android.p011p3;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

/* renamed from: com.airbnb.android.p3.P3Activity$$Lambda$10 */
final /* synthetic */ class P3Activity$$Lambda$10 implements Action1 {
    private final P3Activity arg$1;

    private P3Activity$$Lambda$10(P3Activity p3Activity) {
        this.arg$1 = p3Activity;
    }

    public static Action1 lambdaFactory$(P3Activity p3Activity) {
        return new P3Activity$$Lambda$10(p3Activity);
    }

    public void call(Object obj) {
        P3Activity.lambda$new$9(this.arg$1, (AirRequestNetworkException) obj);
    }
}
