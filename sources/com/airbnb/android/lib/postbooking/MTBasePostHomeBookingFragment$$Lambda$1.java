package com.airbnb.android.lib.postbooking;

import com.airbnb.android.core.responses.PostHomeBookingResponse;
import p032rx.functions.Action1;

final /* synthetic */ class MTBasePostHomeBookingFragment$$Lambda$1 implements Action1 {
    private final MTBasePostHomeBookingFragment arg$1;

    private MTBasePostHomeBookingFragment$$Lambda$1(MTBasePostHomeBookingFragment mTBasePostHomeBookingFragment) {
        this.arg$1 = mTBasePostHomeBookingFragment;
    }

    public static Action1 lambdaFactory$(MTBasePostHomeBookingFragment mTBasePostHomeBookingFragment) {
        return new MTBasePostHomeBookingFragment$$Lambda$1(mTBasePostHomeBookingFragment);
    }

    public void call(Object obj) {
        this.arg$1.onResponseLoaded(((PostHomeBookingResponse) obj).phb);
    }
}
