package com.airbnb.android.lib.postbooking;

import com.airbnb.android.core.responses.AirBatchResponse;
import p032rx.functions.Action1;

final /* synthetic */ class PostBookingActivity$$Lambda$1 implements Action1 {
    private final PostBookingActivity arg$1;

    private PostBookingActivity$$Lambda$1(PostBookingActivity postBookingActivity) {
        this.arg$1 = postBookingActivity;
    }

    public static Action1 lambdaFactory$(PostBookingActivity postBookingActivity) {
        return new PostBookingActivity$$Lambda$1(postBookingActivity);
    }

    public void call(Object obj) {
        this.arg$1.handleBatchResponse((AirBatchResponse) obj);
    }
}
