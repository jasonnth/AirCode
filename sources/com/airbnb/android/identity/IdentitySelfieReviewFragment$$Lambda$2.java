package com.airbnb.android.identity;

import p032rx.functions.Action1;

final /* synthetic */ class IdentitySelfieReviewFragment$$Lambda$2 implements Action1 {
    private final IdentitySelfieReviewFragment arg$1;

    private IdentitySelfieReviewFragment$$Lambda$2(IdentitySelfieReviewFragment identitySelfieReviewFragment) {
        this.arg$1 = identitySelfieReviewFragment;
    }

    public static Action1 lambdaFactory$(IdentitySelfieReviewFragment identitySelfieReviewFragment) {
        return new IdentitySelfieReviewFragment$$Lambda$2(identitySelfieReviewFragment);
    }

    public void call(Object obj) {
        IdentitySelfieReviewFragment.lambda$new$1(this.arg$1, (Boolean) obj);
    }
}
