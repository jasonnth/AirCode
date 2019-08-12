package com.airbnb.android.p011p3;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

/* renamed from: com.airbnb.android.p3.P3ReviewSearchFragment$$Lambda$5 */
final /* synthetic */ class P3ReviewSearchFragment$$Lambda$5 implements Action1 {
    private final P3ReviewSearchFragment arg$1;

    private P3ReviewSearchFragment$$Lambda$5(P3ReviewSearchFragment p3ReviewSearchFragment) {
        this.arg$1 = p3ReviewSearchFragment;
    }

    public static Action1 lambdaFactory$(P3ReviewSearchFragment p3ReviewSearchFragment) {
        return new P3ReviewSearchFragment$$Lambda$5(p3ReviewSearchFragment);
    }

    public void call(Object obj) {
        P3ReviewSearchFragment.lambda$new$4(this.arg$1, (AirRequestNetworkException) obj);
    }
}
