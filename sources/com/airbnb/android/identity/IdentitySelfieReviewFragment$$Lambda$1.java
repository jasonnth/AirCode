package com.airbnb.android.identity;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class IdentitySelfieReviewFragment$$Lambda$1 implements Action1 {
    private final IdentitySelfieReviewFragment arg$1;

    private IdentitySelfieReviewFragment$$Lambda$1(IdentitySelfieReviewFragment identitySelfieReviewFragment) {
        this.arg$1 = identitySelfieReviewFragment;
    }

    public static Action1 lambdaFactory$(IdentitySelfieReviewFragment identitySelfieReviewFragment) {
        return new IdentitySelfieReviewFragment$$Lambda$1(identitySelfieReviewFragment);
    }

    public void call(Object obj) {
        IdentitySelfieReviewFragment.lambda$new$0(this.arg$1, (AirRequestNetworkException) obj);
    }
}
