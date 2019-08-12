package com.airbnb.android.lib.postbooking;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class PostBookingActivity$$Lambda$2 implements Action1 {
    private final PostBookingActivity arg$1;

    private PostBookingActivity$$Lambda$2(PostBookingActivity postBookingActivity) {
        this.arg$1 = postBookingActivity;
    }

    public static Action1 lambdaFactory$(PostBookingActivity postBookingActivity) {
        return new PostBookingActivity$$Lambda$2(postBookingActivity);
    }

    public void call(Object obj) {
        this.arg$1.handleBatchResponseError((AirRequestNetworkException) obj);
    }
}
