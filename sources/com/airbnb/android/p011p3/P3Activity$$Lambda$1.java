package com.airbnb.android.p011p3;

import com.airbnb.android.p011p3.requests.ListingDetailsResponse;
import p032rx.functions.Action1;

/* renamed from: com.airbnb.android.p3.P3Activity$$Lambda$1 */
final /* synthetic */ class P3Activity$$Lambda$1 implements Action1 {
    private static final P3Activity$$Lambda$1 instance = new P3Activity$$Lambda$1();

    private P3Activity$$Lambda$1() {
    }

    public static Action1 lambdaFactory$() {
        return instance;
    }

    public void call(Object obj) {
        ((ListingDetailsResponse) obj).pdpListingDetail;
    }
}
