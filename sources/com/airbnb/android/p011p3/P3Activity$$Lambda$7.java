package com.airbnb.android.p011p3;

import com.airbnb.android.core.responses.PricingQuoteResponse;
import p032rx.functions.Action1;

/* renamed from: com.airbnb.android.p3.P3Activity$$Lambda$7 */
final /* synthetic */ class P3Activity$$Lambda$7 implements Action1 {
    private final P3Activity arg$1;

    private P3Activity$$Lambda$7(P3Activity p3Activity) {
        this.arg$1 = p3Activity;
    }

    public static Action1 lambdaFactory$(P3Activity p3Activity) {
        return new P3Activity$$Lambda$7(p3Activity);
    }

    public void call(Object obj) {
        P3Activity.lambda$new$6(this.arg$1, (PricingQuoteResponse) obj);
    }
}