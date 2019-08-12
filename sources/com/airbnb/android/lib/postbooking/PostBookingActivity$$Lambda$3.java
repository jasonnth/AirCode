package com.airbnb.android.lib.postbooking;

import p032rx.functions.Action1;

final /* synthetic */ class PostBookingActivity$$Lambda$3 implements Action1 {
    private final PostBookingActivity arg$1;

    private PostBookingActivity$$Lambda$3(PostBookingActivity postBookingActivity) {
        this.arg$1 = postBookingActivity;
    }

    public static Action1 lambdaFactory$(PostBookingActivity postBookingActivity) {
        return new PostBookingActivity$$Lambda$3(postBookingActivity);
    }

    public void call(Object obj) {
        PostBookingActivity.lambda$new$0(this.arg$1, (Boolean) obj);
    }
}
