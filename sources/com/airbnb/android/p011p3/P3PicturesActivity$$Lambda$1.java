package com.airbnb.android.p011p3;

import com.airbnb.android.core.responses.ListingResponse;
import p032rx.functions.Action1;

/* renamed from: com.airbnb.android.p3.P3PicturesActivity$$Lambda$1 */
final /* synthetic */ class P3PicturesActivity$$Lambda$1 implements Action1 {
    private final P3PicturesActivity arg$1;

    private P3PicturesActivity$$Lambda$1(P3PicturesActivity p3PicturesActivity) {
        this.arg$1 = p3PicturesActivity;
    }

    public static Action1 lambdaFactory$(P3PicturesActivity p3PicturesActivity) {
        return new P3PicturesActivity$$Lambda$1(p3PicturesActivity);
    }

    public void call(Object obj) {
        P3PicturesActivity.lambda$new$2(this.arg$1, (ListingResponse) obj);
    }
}
