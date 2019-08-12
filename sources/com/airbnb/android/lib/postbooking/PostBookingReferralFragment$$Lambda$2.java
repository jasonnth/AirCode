package com.airbnb.android.lib.postbooking;

import com.airbnb.android.core.BugsnagWrapper;
import p032rx.functions.Action1;

final /* synthetic */ class PostBookingReferralFragment$$Lambda$2 implements Action1 {
    private final PostBookingReferralFragment arg$1;

    private PostBookingReferralFragment$$Lambda$2(PostBookingReferralFragment postBookingReferralFragment) {
        this.arg$1 = postBookingReferralFragment;
    }

    public static Action1 lambdaFactory$(PostBookingReferralFragment postBookingReferralFragment) {
        return new PostBookingReferralFragment$$Lambda$2(postBookingReferralFragment);
    }

    public void call(Object obj) {
        BugsnagWrapper.throwOrNotify((RuntimeException) new IllegalStateException("Referral bonus fetching failed from " + this.arg$1.getClass().getSimpleName()));
    }
}
