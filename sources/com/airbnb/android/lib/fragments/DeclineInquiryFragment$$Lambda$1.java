package com.airbnb.android.lib.fragments;

import com.airbnb.airrequest.BaseResponse;
import p032rx.functions.Action1;

final /* synthetic */ class DeclineInquiryFragment$$Lambda$1 implements Action1 {
    private final DeclineInquiryFragment arg$1;

    private DeclineInquiryFragment$$Lambda$1(DeclineInquiryFragment declineInquiryFragment) {
        this.arg$1 = declineInquiryFragment;
    }

    public static Action1 lambdaFactory$(DeclineInquiryFragment declineInquiryFragment) {
        return new DeclineInquiryFragment$$Lambda$1(declineInquiryFragment);
    }

    public void call(Object obj) {
        DeclineInquiryFragment.lambda$new$0(this.arg$1, (BaseResponse) obj);
    }
}
