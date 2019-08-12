package com.airbnb.android.lib.fragments;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class DeclineInquiryFragment$$Lambda$2 implements Action1 {
    private final DeclineInquiryFragment arg$1;

    private DeclineInquiryFragment$$Lambda$2(DeclineInquiryFragment declineInquiryFragment) {
        this.arg$1 = declineInquiryFragment;
    }

    public static Action1 lambdaFactory$(DeclineInquiryFragment declineInquiryFragment) {
        return new DeclineInquiryFragment$$Lambda$2(declineInquiryFragment);
    }

    public void call(Object obj) {
        DeclineInquiryFragment.lambda$new$1(this.arg$1, (AirRequestNetworkException) obj);
    }
}
