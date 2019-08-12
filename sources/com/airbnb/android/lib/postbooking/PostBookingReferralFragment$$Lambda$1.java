package com.airbnb.android.lib.postbooking;

import com.airbnb.android.core.responses.ReferralStatusResponse;
import p032rx.functions.Action1;

final /* synthetic */ class PostBookingReferralFragment$$Lambda$1 implements Action1 {
    private final PostBookingReferralFragment arg$1;

    private PostBookingReferralFragment$$Lambda$1(PostBookingReferralFragment postBookingReferralFragment) {
        this.arg$1 = postBookingReferralFragment;
    }

    public static Action1 lambdaFactory$(PostBookingReferralFragment postBookingReferralFragment) {
        return new PostBookingReferralFragment$$Lambda$1(postBookingReferralFragment);
    }

    public void call(Object obj) {
        PostBookingReferralFragment.lambda$new$0(this.arg$1, (ReferralStatusResponse) obj);
    }
}
